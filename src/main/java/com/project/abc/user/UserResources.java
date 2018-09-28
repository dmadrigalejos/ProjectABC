package com.project.abc.user;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.abc.utility.HeaderUtil;
import com.project.abc.utility.Messages;

@RestController
@RequestMapping("/user")
public class UserResources {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getusers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getUsers(
            @RequestParam(required = true) String searchQuery, 
            @RequestParam(required = true) Integer pageNumber, 
            @RequestParam(required = true) String sortBy, 
            @RequestParam(required = true) String order, 
            @RequestParam(required = true) Integer limit ) throws Exception {
        return userService.getUsers(searchQuery, pageNumber, sortBy, order, limit);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addNewUser(@Valid @RequestBody User user) throws Exception {
        if (!userService.addNewUser(user)) {
            return ResponseEntity.badRequest().headers(HeaderUtil.error(Messages.User.FAIL_NEW_ENTRY)).build();
        }
        return ResponseEntity.ok().headers(HeaderUtil.success(Messages.User.SUCCESS_NEW_ENTRY)).build();
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<Void> updateUserInfo(@Valid @RequestBody User user) throws Exception {
        if (!userService.updateUserInfo(user)) {
            return ResponseEntity.badRequest().headers(HeaderUtil.error(Messages.User.FAIL_UPDATE_ENTRY)).build();
        }
        return ResponseEntity.ok().headers(HeaderUtil.success(Messages.User.SUCCESS_UPDATE_ENTRY)).build();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer userId) throws Exception {
        if (!userService.deleteUser(userId)) {
            return ResponseEntity.badRequest().headers(HeaderUtil.error(Messages.User.FAIL_UPDATE_ENTRY)).build();
        }
        return ResponseEntity.ok().headers(HeaderUtil.success(Messages.User.SUCCESS_DELETE_ENTRY)).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable Integer id) throws Exception {
        User user = userService.getUser(id);
        if (user.getUserName() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.error(Messages.User.FAIL_USER_NOT_EXIST)).build();
        } else {
            return ResponseEntity.ok().body(user);
        }
    }
}
