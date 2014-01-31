
package com.mtt.todo.web.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    
  @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
        HttpServletResponse response, 
        Authentication authentication) throws ServletException, IOException {
      request.getSession().setAttribute("username", 
              ((UserDetails)authentication.getPrincipal()).getUsername()
              );
      
      setDefaultTargetUrl("/index.html");
      super.onAuthenticationSuccess(request, response, authentication);
      
   }
}
