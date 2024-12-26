package org.makar.t1_tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.makar.t1_tasks.model.role.RoleEnum;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String login;
    private String password;
    private List<RoleEnum> roleEnums;
}
