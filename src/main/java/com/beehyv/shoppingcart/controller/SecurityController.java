package com.beehyv.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController {
    @RequestMapping(value = "/user-email", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserEmail(Principal principal) {
        return principal.getName();
    }
}
