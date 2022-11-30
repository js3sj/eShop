package lt.codeacademy.handlers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lt.codeacademy.controllers.LoggingController;
import lt.codeacademy.entities.SessionUser;
import lt.codeacademy.services.ItemService;
import lt.codeacademy.services.SessionUserService;
import lt.codeacademy.services.UserService;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	SessionUserService sessionService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
			Authentication authentication) throws IOException, ServletException {

		boolean hasUserRole = false;
		boolean hasAdminRole = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("USER")) {
				hasUserRole = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ADMIN")) {
				hasAdminRole = true;
				break;
			}
		}

		if (hasUserRole) {
			redirectStrategy.sendRedirect(arg0, arg1, "/items/index");
		} else if (hasAdminRole) {
			redirectStrategy.sendRedirect(arg0, arg1, "/items/index");
		} else {
			throw new IllegalStateException();
		}
		
		String activeUserName = authentication.getName();
		long activeUserId = userService.findIdByUserName(activeUserName);
		
		sessionService.save(new SessionUser(1, activeUserId));
		logger.info("LOGIN with username: [" + activeUserName + "]");
	}

}