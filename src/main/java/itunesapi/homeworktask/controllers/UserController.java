package itunesapi.homeworktask.controllers;

import itunesapi.homeworktask.entity.Artist;
import itunesapi.homeworktask.entity.User;
import itunesapi.homeworktask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService personService;

    @GetMapping(value = "/", produces = "application/json")
    public String mainPage(User user, Model model) {
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping(value = "/save", produces = "application/json")
    public String saveUser(@ModelAttribute User user) {
        personService.saveOrUpdate(user);
        return "profile";
    }

    @GetMapping(value = "/profile", produces = "application/json")
    public String toProfile(User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping(value = "/searchArtist", produces = "application/json")
    public String searchPage(User user, Model model, Artist artist) {
        model.addAttribute("user", user);
        model.addAttribute("artist", artist);
        return "search-page";
    }

}
