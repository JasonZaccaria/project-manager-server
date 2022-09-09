package com.projectmanager.projectmanagerproject.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long>{
    AppUser findByEmail(String email);
}
