package com.himanshu.voguetrendz.Helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Component
public class SessionHelper {

    public void removeSessionAttribute(){
        try{
            HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
            session.removeAttribute("message");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
