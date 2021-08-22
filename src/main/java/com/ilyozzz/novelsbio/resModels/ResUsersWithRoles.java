package com.ilyozzz.novelsbio.resModels;

import com.ilyozzz.novelsbio.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResUsersWithRoles {
    private UUID id;
    private String userName;
    private List<Role> roles;

}
