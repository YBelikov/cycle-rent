package com.belikov.valteris.cycle.util;

import com.belikov.valteris.cycle.exception.UnauthorizedException;
import com.belikov.valteris.cycle.user.UserService;
import com.belikov.valteris.cycle.user.model.UserDTO;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class ControllerUtil {
    public static UserDTO getUserFromSecurityContext(UserService service) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("unauthorized.request"));
    }

//    public static String parsePageNumber(String page, int totalPages, String url) {
//        if(page == null) {
//            return REDIRECT + url;
//        }
//        try {
//            int pageValue = Integer.parseInt(page) - 1;
//            if(pageValue < 0 || pageValue > totalPages) {
//                return REDIRECT + url;
//            }
//            return String.valueOf(pageValue);
//        }
//        catch (NumberFormatException ex) {
//            return REDIRECT + url;
//        }
//    }
}
