package RestServer.service;

import RestServer.dataAccessObject.RoleRepository;
import RestServer.dataAccessObject.UserRepository;
import RestServer.exception.BusinessResourceException;
import RestServer.model.RoleModel;
import RestServer.model.UserModel;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl() {
        super();
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Optional<UserModel> findByLogin(String login) {

        Optional<UserModel> userFound = userRepository.findByLogin(login);
        if (Boolean.FALSE.equals(userFound.isPresent())) {
            throw new BusinessResourceException("User Not Found", "L'utilisateur avec ce login n'existe pas :" + login);
        }
        return userFound;
    }

    @Override
    public Collection<UserModel> getAllUsers() {
        return IteratorUtils.toList(userRepository.findAll().iterator());
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserModel> findUserById(Long id){

        Optional<UserModel> userFound = userRepository.findById(id);
        if (Boolean.FALSE.equals(userFound.isPresent())){
            throw new BusinessResourceException("User Not Found", "Aucun utilisateur avec l'identifiant :" + id);
        }
        return userFound;
    }

    @Override
    @Transactional(readOnly=false)
    public UserModel saveOrUpdateUser(UserModel user){
        try{
            if(null ==user.getId()) {//pas d'Id --> création d'un user
                addUserRole(user);//Ajout d'un rôle par défaut
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            } else {//sinon, mise à jour d'un user

                Optional<UserModel> userFromDB = findUserById(user.getId());
                if(! bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.get().getPassword())) {
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));//MAJ du mot de passe s'il a été modifié
                } else {

                    user.setPassword(userFromDB.get().getPassword());//Sinon, on remet le password déjà haché
                }
                updateUserRole(user);//On extrait le rôle en cas de mise à jour
            }
            UserModel result = userRepository.save(user);
            return  result;
        } catch(DataIntegrityViolationException ex){
            logger.error("Utilisateur non existant", ex);
            throw new BusinessResourceException("DuplicateValueError", "Un utilisateur existe déjà avec le compte : "+user.getLogin(), HttpStatus.CONFLICT);
        } catch (BusinessResourceException e) {
            logger.error("Utilisateur non existant", e);
            throw new BusinessResourceException("UserNotFound", "Aucun utilisateur avec l'identifiant: "+user.getId(), HttpStatus.NOT_FOUND);
        } catch(Exception ex){
            logger.error("Erreur technique de création ou de mise à jour de l'utilisateur", ex);
            throw new BusinessResourceException("SaveOrUpdateUserError", "Erreur technique de création ou de mise à jour de l'utilisateur: "+user.getLogin(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteUser(Long id) throws BusinessResourceException {
        try{
            userRepository.deleteById(id);
        }catch(EmptyResultDataAccessException ex){
            logger.error(String.format("Aucun utilisateur n'existe avec l'identifiant: "+id, ex));
            throw new BusinessResourceException("DeleteUserError", "Erreur de suppression de l'utilisateur avec l'identifiant: "+id, HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            throw new BusinessResourceException("DeleteUserError", "Erreur de suppression de l'utilisateur avec l'identifiant: "+id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<UserModel> findByLoginAndPassword(String login, String password) {
        try {
            Optional<UserModel> userFound = this.findByLogin(login);
            if(bCryptPasswordEncoder.matches(password, userFound.get().getPassword())) {
                return userFound;
            } else {
                throw new BusinessResourceException("UserNotFound", "Mot de passe incorrect", HttpStatus.NOT_FOUND);
            }
        } catch (BusinessResourceException ex) {
            logger.error("Login ou mot de passe incorrect", ex);
            throw new BusinessResourceException("UserNotFound", "Login ou mot de passe incorrect", HttpStatus.NOT_FOUND);
        }catch (Exception ex) {
            logger.error("Une erreur technique est survenue", ex);
            throw new BusinessResourceException("TechnicalError", "Une erreur technique est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addUserRole(UserModel user) {
        Set<RoleModel> roles= new HashSet<>();
        RoleModel roleUser = new RoleModel("ROLE_USER");//initialisation du rôle ROLE_USER
        roles.add(roleUser);
        user.setActive(0);

        Set<RoleModel> roleFromDB = extractRole_Java8(roles, roleRepository.getAllRolesStream());
        user.setRoles(roleFromDB);
    }

    private void updateUserRole(UserModel user) {

        Set<RoleModel> roleFromDB = extractRole_Java8(user.getRoles(), roleRepository.getAllRolesStream());
        user.setRoles(roleFromDB);
    }

    private Set<RoleModel> extractRole_Java8(Set<Role> rolesSetFromUser, Stream<RoleModel> roleStreamFromDB) {
        // Collect UI role names
        Set<String> uiRoleNames = rolesSetFromUser.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toCollection(HashSet::new));
        // Filter DB roles
        return roleStreamFromDB
                .filter(role -> uiRoleNames.contains(role.getRoleName()))
                .collect(Collectors.toSet());
    }

    @SuppressWarnings("unused")
    private Set<Role> extractRoleUsingCompareTo_Java8(Set<Role> rolesSetFromUser, Stream<Role> roleStreamFromDB) {
        return roleStreamFromDB
                .filter(roleFromDB -> rolesSetFromUser.stream()
                        .anyMatch( roleFromUser -> roleFromUser.compareTo(roleFromDB) == 0))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @SuppressWarnings("unused")
    private Set<Role>  extractRole_BeforeJava8(Set<Role> rolesSetFromUser, Collection<Role> rolesFromDB) {
        Set<Role> rolesToAdd = new HashSet<>();
        for(Role roleFromUser:rolesSetFromUser){
            for(Role roleFromDB:rolesFromDB){
                if(roleFromDB.compareTo(roleFromUser)==0){
                    rolesToAdd.add(roleFromDB);
                    break;
                }
            }
        }
        return rolesToAdd;
    }
}
