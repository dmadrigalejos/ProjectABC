package com.project.abc.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.abc.user.User;
import com.project.abc.user.UserService;
import com.project.abc.utility.Messages;

@RestController
@RequestMapping("/login")
public class LoginResource {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody(required = false) User user, HttpSession httpSession) throws Exception {
		try {
			User userCred = userService.retrieveUserInfo(user.getUserName(), user.getUserPassword());
			
			if (userCred == null) {
			    User usr = new User();
			    usr.setUserName(Messages.Login.FAIL_LOGIN);
				return ResponseEntity.badRequest().body(usr);
			} else if (!userCred.isSuccessful()) {
			    User usr = new User();
                usr.setUserName(Messages.Login.FAIL_LOGIN);
                return ResponseEntity.badRequest().body(usr);
			}

			Authentication authentication = new UsernamePasswordAuthenticationToken(userCred, null, null);
			SecurityContext c = SecurityContextHolder.getContext();
			c.setAuthentication(authentication);
			httpSession.setAttribute("USERINFO", getCurrentUserLogin());

			httpSession.setMaxInactiveInterval(60 * 15);
			User loggedInUser = getCurrentUserLogin();
			
			return ResponseEntity.ok().body(loggedInUser);
		} catch (Exception e) {
		    User usr = new User();
            usr.setUserName(Messages.Login.FAIL_UNABLE_TO_LOGIN);
            return ResponseEntity.badRequest().body(usr);
		}
	}

	@RequestMapping(value = "/invalidate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> logout(HttpSession httpSession) {
		httpSession.invalidate();
		User usr = new User();
        usr.setUserName(Messages.Login.FAIL_UNABLE_TO_LOGIN);
        return ResponseEntity.ok().body(usr);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> authenticate(HttpSession httpSession) {
        try {
            if (httpSession.getAttribute("USERINFO") != null) {
                User user = (User) httpSession.getAttribute("USERINFO");
                return ResponseEntity.ok().body(user);
            } else {
                User usr = new User();
                usr.setUserName("");
                return ResponseEntity.ok().body(usr);
            }
        } catch (Exception e) {
            User usr = new User();
            usr.setUserName("");
            return ResponseEntity.ok().body(usr);
        }
    }

	public static User getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();

		if (authentication != null && authentication.getPrincipal() instanceof User) {
			return (User) authentication.getPrincipal();
		} else {
			User u = new User();
			u.setUserName("");
			return u;
		}
	}
}
