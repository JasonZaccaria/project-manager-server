package com.projectmanager.projectmanagerproject.appUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser findUser = appUserRepo.findByEmail(username);
        if (findUser == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        findUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(username, findUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        if (appUserRepo.findByEmail(appUser.getEmail()) != null) {
            return null;
        }
        String password = appUser.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        appUser.setPassword(hashedPassword);
        return appUserRepo.save(appUser);
    }

    @Override
    public AppUser getUser(String email) {
        return appUserRepo.findByEmail(email);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepo.findAll();
    }
    
}
