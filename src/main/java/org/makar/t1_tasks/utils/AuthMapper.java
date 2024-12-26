package org.makar.t1_tasks.utils;


import org.makar.t1_tasks.dto.LoginRequest;
import org.makar.t1_tasks.dto.SignupRequest;
import org.makar.t1_tasks.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    User convertFromSignupRequestToUser(SignupRequest signupRequest);

    User convertFromLoginRequestToUser(LoginRequest loginRequest);

}
