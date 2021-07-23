package io.javabrains.resumeportal;


import io.javabrains.resumeportal.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit page";
    }


    @GetMapping("/view/{userId}") // better name would be 'userName' as opposed to id - we expect a string!
    public String view(@PathVariable String userId, Model model) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        System.out.println("USER PROFILE: " + userProfileOptional.toString());
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));


        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);
//        return "profile-templates/" + userProfile.getT
//        return "profile-templates/" + userProfile.get().getTheme() + "/index";
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
