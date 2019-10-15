package com.beehyv.shoppingcart.controller;

import com.beehyv.shoppingcart.repo.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController {
    @Autowired
    UserProfileRepo userProfileRepo;
    @RequestMapping(value = "/user-email", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userProfileRepo.findByEmail(currentUserName).getName();
        }
        return null;
    }
    @RequestMapping(value = "/user-id", method = RequestMethod.GET)
    @ResponseBody
    public Long currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userProfileRepo.findByEmail(currentUserName).getUserId();
        }
        return null;
    }

}
