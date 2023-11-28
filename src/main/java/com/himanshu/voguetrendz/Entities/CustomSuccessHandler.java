package com.himanshu.voguetrendz.Entities;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if(role.equals("ROLE_CUSTOMER")){
            getRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
        }else if(role.equals("ROLE_ADMIN")){
            getRedirectStrategy().sendRedirect(request, response, "/admin/dashboard");
        }else{
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
