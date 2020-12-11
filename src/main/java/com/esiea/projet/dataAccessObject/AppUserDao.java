package com.esiea.projet.dataAccessObject;

import com.esiea.projet.formbean.AppUserForm;
import com.esiea.projet.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AppUserDao {

    // Config in WebSecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Map<Long, UserModel> USERS_MAP = new HashMap<>();

    static {
        initDATA();
    }

    private static void initDATA() {
        String encrytedPassword = "";

        UserModel tom = new UserModel(1L, "tom", "Tom", "Tom", //
                true, Gender.MALE, "tom@waltdisney.com", encrytedPassword, "US");

        UserModel jerry = new UserModel(2L, "jerry", "Jerry", "Jerry", //
                true, Gender.MALE, "jerry@waltdisney.com", encrytedPassword, "US");

        USERS_MAP.put(tom.getUserId(), tom);
        USERS_MAP.put(jerry.getUserId(), jerry);
    }

    public Long getMaxUserId() {
        long max = 0;
        for (Long id : USERS_MAP.keySet()) {
            if (id > max) {
                max = id;
            }
        }
        return max;
    }

    //

    public UserModel findAppUserByUserName(String userName) {
        Collection<UserModel> appUsers = USERS_MAP.values();
        for (UserModel u : appUsers) {
            if (u.getUserName().equals(userName)) {
                return u;
            }
        }
        return null;
    }

    public UserModel findAppUserByEmail(String email) {
        Collection<UserModel> appUsers = USERS_MAP.values();
        for (UserModel u : appUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public List<UserModel> getAppUsers() {
        List<UserModel> list = new ArrayList<>();

        list.addAll(USERS_MAP.values());
        return list;
    }

    public UserModel createAppUser(AppUserForm form) {
        Long userId = this.getMaxUserId() + 1;
        String encrytedPassword = this.passwordEncoder.encode(form.getPassword());

        UserModel user = new UserModel(userId, form.getUserName(), //
                form.getFirstName(), form.getLastName(), false, //
                form.getGender(), form.getEmail(), form.getCountryCode(), //
                encrytedPassword);

        USERS_MAP.put(userId, user);
        return user;
    }
}