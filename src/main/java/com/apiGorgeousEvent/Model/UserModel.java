package com.apiGorgeousEvent.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// L'annotation @Entity permet d'indiquer à l'ORM Hibernate que cette classe sera une table de la base de données et
// l'annotation @Table(name = "UTILISATEUR") permet de donner le nom UTILISATEUR à la table. Grâce à ces annotations, on n'a plus besoin du fichier de configuration persistence.xml
@Entity
@Table(name = "UTILISATEUR")
public class UserModel implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "LOGIN", unique=true, insertable=true, updatable=true, nullable=false)
    private String login;

    @Column(name = "USER_PASSWORD", insertable=true, updatable=true, nullable=false)
    private String password;

    @Column(name = "USER_MAIL", insertable=true, updatable = true, nullable=false)
    private String mail;

    // ROLE PERMET DE DEFINIR SI ADMIN OU UTILISATEUR SIMPLE
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleModel> roles= new HashSet<RoleModel>();

    public UserModel() {
        super();
    }

    public UserModel(String login, String password, String mail) {
        this.login = login;
        this.password = password;
        this.mail = mail;
    }

    public UserModel(Long id, String login, String password, String mail) {
        this.id= id;
        this.login=login;
        this.password = password;
        this.mail=mail;
    }

    public UserModel(String login, String password) {
        this.login=login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }
    public void setActive(Integer active) {
        this.mail = mail;
    }

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=XXXXXXX, active=" + mail + ", roles="
                + roles + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mail == null) ? 0 : mail.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserModel other = (UserModel) obj;
        if (mail == null) {
            if (other.mail != null)
                return false;
        } else if (!mail.equals(other.mail))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (roles == null) {
            if (other.roles != null)
                return false;
        } else if (!roles.equals(other.roles))
            return false;
        return true;
    }
}