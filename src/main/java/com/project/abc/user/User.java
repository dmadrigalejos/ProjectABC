package com.project.abc.user;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.abc.utility.Messages;
import com.project.abc.validator.annotation.ValidUser;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ValidUser
public class User {
    private int userID;
    
    @NotBlank(message = Messages.User.FAIL_USERNAME_REQUIRED)
    @Size(min = 4, max = 50, message = Messages.User.FAIL_USERNAME_SIZE)
    private String userName;
    
    @NotBlank(message = Messages.User.FAIL_LASTNAME_REQUIRED)
    @Size(min = 1, max = 127, message = Messages.User.FAIL_LASTNAME_SIZE)
    private String userLastName;
    
    @NotBlank(message = Messages.User.FAIL_FIRSTNAME_REQUIRED)
    @Size(min = 1, max = 127, message = Messages.User.FAIL_FIRSTNAME_SIZE)
    private String userFirstName;
    
    @Getter(onMethod = @__(@JsonIgnore))
    @Setter(onMethod = @__(@JsonProperty))
    @Size(min = 10, max = 127, message = Messages.User.FAIL_PASSWORD_SIZE)
    private String userPassword; 

	private boolean isSuccessful;
	private boolean userAdmin;
}
