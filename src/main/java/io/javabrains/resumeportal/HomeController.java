package io.javabrains.resumeportal;


import io.javabrains.resumeportal.models.Job;
import io.javabrains.resumeportal.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home() {
        UserProfile profile1 = new UserProfile();
        profile1.setId(1);
        profile1.setUserName("einstein");
        profile1.setDesignation("Designation");
        profile1.setFirstName("Albert");
        profile1.setLastName("Einstein");
        profile1.setTheme(1);

        Job job1 = new Job();
        Job job2 = new Job();

        job1.setCompany("Acme Academy 1");
        job1.setDesignation("Designated Acme 1");
        job1.setId(1);
        job1.setStartDate(LocalDate.of(2020, 1, 1));
        job1.setEndDate(LocalDate.of(2021, 2, 2));

        job2.setCompany("Acme Academy TWO");
        job2.setDesignation("Designated Acme TWO");
        job1.setId(2);
        job1.setStartDate(LocalDate.of(2019, 1, 1));
        job1.setEndDate(LocalDate.of(2021, 9, 21));

        profile1.setJobs(Arrays.asList(job1, job2));

        userProfileRepository.save(profile1);

        return "profile";
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

        // print to console for
        System.out.println(userProfile.toString());

//        return "profile-templates/" + userProfile.getT
//        return "profile-templates/" + userProfile.get().getTheme() + "/index";
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
