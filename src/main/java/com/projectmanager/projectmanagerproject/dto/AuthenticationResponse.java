package com.projectmanager.projectmanagerproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//cant add no args constructor here for some reason
@Data
@AllArgsConstructor
public class AuthenticationResponse {
    
    private final String jwt;

}
