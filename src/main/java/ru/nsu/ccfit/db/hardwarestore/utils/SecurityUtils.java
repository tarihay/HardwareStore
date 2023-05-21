package ru.nsu.ccfit.db.hardwarestore.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

public class SecurityUtils {
    public static String getSessionUser() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
