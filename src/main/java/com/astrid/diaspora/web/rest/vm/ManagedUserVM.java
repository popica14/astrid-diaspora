package com.astrid.diaspora.web.rest.vm;

import com.astrid.diaspora.service.dto.UserDTO;
import javax.validation.constraints.Size;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    private String phoneNumber;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phone){
        this.phoneNumber = phone;
    }
    // prettier-ignore
    @Override
    public String toString() {
        return "ManagedUserVM{" + super.toString() + "} ";
    }
}
