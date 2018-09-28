package com.project.abc.user;

public class UserSQL {

    public static final String RETRIEVE_FILTERED_USER =
            "SELECT * " +
            "  FROM `user` " + 
            " WHERE concat(`username`,`userFirstName`,`userLastName`) LIKE ? " +
            " ORDER BY %s %s " +
            " LIMIT ?, ?";
    
    public static final String RETRIEVE_FILTERED_USER_COUNT =
            "SELECT count(userId) " +
            "  FROM `user` " +
            " WHERE concat(`userName`,`userFirstName`,`userLastName`) LIKE ? ";
    
    public static final String RETRIEVE_USER_INFO =
            "SELECT * " +
            "  FROM `user` " +
            " WHERE `userName` = ?";
    
    public static final String RETRIEVE_USER_INFO_BY_ID = 
            "SELECT * " +
            "  FROM `user` " +
            " WHERE `userId` = ?";
    
    public static final String ADD_NEW_USER = 
            "INSERT INTO `user` ( " +
            "            `userName` " +
            "            ,`userLastName` " +
            "            ,`userFirstName` " +
            "            ,`userPassword` " +
            "            ) " +
            "VALUES (?,?,?,?)";
    
    public static final String IS_USERNAME_EXISTS =
            "SELECT count(userName) " +
            "  FROM `user` " + 
            " WHERE `username` = ? " +
            "   AND `userId` != ?";
    
    public static final String DELETE_USER =
            "DELETE FROM `user` WHERE `userId` = ?";
    
    public static final String UPDATE_USER_INFO =
            "UPDATE `user` " +
            "   SET `username` = ? " +
            "        ,`userLastname` = ? " +
            "        ,`userFirstname` = ? " +
            " WHERE `userId` = ?";
}
