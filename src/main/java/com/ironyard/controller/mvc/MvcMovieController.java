package com.ironyard.controller.mvc;

import com.ironyard.data.IronUser;
import com.ironyard.data.Movie;
import com.ironyard.repo.IronUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Created by jasonskipper on 11/1/16.
 */
@Controller
@RequestMapping(path = "/mvc/movie")
public class MvcMovieController {

    @Autowired
    IronUserRepository userRepository = null;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request){
        // get current logged in user, need to case (IronUser) to proper type
        IronUser user = (IronUser)request.getSession().getAttribute("user");

        Long usrId = user.getId();

        // get users favorites
        Set<Movie> favs = userRepository.findOne(usrId).getFavs();


        // put them in a model
        model.addAttribute("favs", favs);

        // send them to the dam
        return "home";
    }

}
