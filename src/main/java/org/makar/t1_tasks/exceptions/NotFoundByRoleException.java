package org.makar.t1_tasks.exceptions;


import org.makar.t1_tasks.model.role.RoleEnum;

public class NotFoundByRoleException extends RuntimeException {
    public <T> NotFoundByRoleException(Class<T> clazz, RoleEnum role){
        super("Role with name = " + role.toString() + " does not exist");
    }
}