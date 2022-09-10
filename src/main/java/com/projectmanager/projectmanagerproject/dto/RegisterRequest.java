package com.projectmanager.projectmanagerproject.dto;

import java.util.ArrayList;
import java.util.Collection;

import com.projectmanager.projectmanagerproject.role.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private Long id = null;
    private String email;
    private String password;
    private Collection<Role> roles = new ArrayList<>();
    
}
