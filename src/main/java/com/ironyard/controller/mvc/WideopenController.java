package com.ironyard.controller.mvc;

import com.ironyard.repo.IronUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by reevamerchant on 11/1/16.
 */
@Controller
public class WideopenController {

    @RequestMapping(value = "/mvc/wideopen", method = RequestMethod.GET)
    public String abc(){
        return "wideopen";
    }

}
