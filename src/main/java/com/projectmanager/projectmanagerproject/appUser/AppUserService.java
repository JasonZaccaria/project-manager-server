package com.projectmanager.projectmanagerproject.appUser;

import java.util.List;

public interface AppUserService {

    AppUser saveUser(AppUser appUser);

    AppUser getUser(String email);

    List<AppUser> getAllUsers();
    
}
