package com.project.abc.utility;

public class Messages {

    public class Login {
        public static final String FAIL_LOGIN = "Username and password does not match.";
        public static final String FAIL_UNABLE_TO_LOGIN = "Unable to logged in.";
    }

    public class User {
        public static final String SUCCESS_NEW_ENTRY = "User was successfully created";
        public static final String FAIL_NEW_ENTRY = "Unable to create user";
        public static final String FAIL_UPDATE_ENTRY = "Unable to update user.";
        public static final String SUCCESS_UPDATE_ENTRY = "User was successfully updated.";
        public static final String SUCCESS_DELETE_ENTRY = "User was successfully deleted.";
        public static final String FAIL_DUPLICATE_USERNAME = "Username already exists.";
        public static final String FAIL_USERNAME_REQUIRED = "Username is required.";
        public static final String FAIL_USERNAME_SIZE = "Username should have a minimum of 8 characters and maximum of 50.";
        public static final String FAIL_FIRSTNAME_REQUIRED = "Fistname is required.";
        public static final String FAIL_FIRSTNAME_SIZE = "Firstname should have a minimum of 1 characters and maximum of 127.";
        public static final String FAIL_LASTNAME_REQUIRED = "Lastname is required.";
        public static final String FAIL_LASTNAME_SIZE = "Lastname should have a minimum of 1 characters and maximum of 127.";
        public static final String FAIL_PASSWORD_SIZE = "Password should be atleast 10 characters.";
        public static final String FAIL_USER_NOT_EXIST = "User does not exist";
    }
}
