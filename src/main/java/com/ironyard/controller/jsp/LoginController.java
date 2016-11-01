package com.ironyard.controller.jsp;

import com.ironyard.data.IronUser;
import com.ironyard.repo.IronUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by jasonskipper on 11/1/16.
 */
@Controller
public class LoginController {

    @Autowired
    private IronUserRepository userRepository;

    @RequestMapping(value = "/mvc/login", method = RequestMethod.POST)
    public String list(@RequestParam(value = "password", required = false) String password,
                       @RequestParam(value = "username", required = false) String username,
                       HttpServletRequest request) {
        String destination = "login";
        IronUser found = userRepository.findByUsernameAndPassword(username, password);
        if(found != null){
            request.getSession().setAttribute("user",found);
        }else{
            destination = "home";
        }
        return destination;
    }



}
