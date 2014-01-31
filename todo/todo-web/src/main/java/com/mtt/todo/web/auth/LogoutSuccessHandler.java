
package com.mtt.todo.web.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component("logoutSuccessHandler")
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        if (authentication != null) {
          new SecurityContextLogoutHandler().logout(request,response,authentication);
          SecurityContextHolder.getContext().setAuthentication(null);
        }
        setDefaultTargetUrl("/login.html");
        super.onLogoutSuccess(request, response, authentication);       
    }

}