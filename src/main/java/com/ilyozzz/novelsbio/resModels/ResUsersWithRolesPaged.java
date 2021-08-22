package com.ilyozzz.novelsbio.resModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResUsersWithRolesPaged {

    private Long totalElements;
    private Integer totalPages;
    private List<ResUsersWithRoles> usersWithRolesList;

}
