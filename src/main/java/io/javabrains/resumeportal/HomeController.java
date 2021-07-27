package io.javabrains.resumeportal;


import io.javabrains.resumeportal.models.Education;
import io.javabrains.resumeportal.models.Job;
import io.javabrains.resumeportal.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home() {
        Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("einstein");
        profileOptional.orElseThrow(() -> new RuntimeException("Not Found: "));
        //System.out.println("PROFILE: " + profileOptional.get());
        UserProfile profile1 = profileOptional.get();


        Job job1 = new Job();
        Job job2 = new Job();

        job1.setCompany("Acme Academy 1");
        job1.setDesignation("Designated Acme 1");
        job1.setId(1);
        job1.setStartDate(LocalDate.of(2020, 1, 1));
        // job1.setEndDate(LocalDate.of(2021, 2, 2));
        job1.setCurrentJob(true);
        job1.getResponsibilities().add("Come up with the theory of relativity");
        job1.getResponsibilities().add("Outsmart the outdoor cat.");
        job1.getResponsibilities().add("Track the status for three of Schrodinger's Cats");

        job2.setCompany("Acme Academy TWO");
        job2.setDesignation("Designated Acme TWO");
        job2.setId(2);
        job2.setStartDate(LocalDate.of(2019, 1, 1));
        job2.setEndDate(LocalDate.of(2021, 9, 21));

        job2.getResponsibilities().add("Devise newer tastier generation of Cat food.");
        job2.getResponsibilities().add("Outsmart Einstein.");
        job2.getResponsibilities().add("Track the status for three of Schrodinger's Cats");

//        List<Job> jobs = new ArrayList<Job>();  ??? add again?

        profile1.setPhone("312-666-7793");

        profile1.getJobs().clear();
        profile1.getJobs().add(job1);
        profile1.getJobs().add(job2);
      //  profile1.setJobs(jobs);

        Education e1 = new Education();
        Education e2 = new Education();

        //e1.setId(1);
        e1.setCollege("College of DuPage");
        e1.setQualification("Bachelors, Computer Science");
        e1.setSummary("Studied much and did suchness a muchness of good deeds");
        e1.setStartDate(LocalDate.of(2012, 1, 1));
        e1.setEndDate(LocalDate.of(2016, 9, 21));

        e2.setCollege("University of Wisconsin - Madison");
        e2.setQualification("Bachelors of Science, Philosophy/Psychology");
        e2.setSummary("Political activism - bent the university to my evil plot for world domination");
        e2.setStartDate(LocalDate.of(2016, 1, 1));
        e2.setEndDate(LocalDate.of(2018, 9, 21));

        profile1.getEducations().clear();
        profile1.getEducations().add(e1);
        profile1.getEducations().add(e2);

        profile1.getSkills().clear();
        profile1.getSkills().add("IntelliJ");
        profile1.getSkills().add("Microsoft Office");
        profile1.getSkills().add("Java Spring");
        profile1.getSkills().add("Python Django");
        profile1.getSkills().add("Technical Writing");
        profile1.getSkills().add("Casual Quantum Computing");

        profile1.setTheme(1);

        userProfileRepository.save(profile1);

        System.out.println("/ Theme: " + profile1.getTheme());

        return "profile";
    }

    // edit only currently logged in user - only your own profile
    // Spring gives you the Principal - the currently logged in user
    @GetMapping("/edit") // get the form so you can fill it out
    public String edit(Principal principal, Model model, @RequestParam(required=false) String add) {
        // get userid and add as model attribute
        String userId = principal.getName();
        model.addAttribute("userId", userId);

        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not Found: " + userId));

        UserProfile userProfile = userProfileOptional.get();

//        TODO:  when something is being added do a post so that it saves

        if("job".equals(add)) {
            userProfile.getJobs().add(new Job());
        } else if("education".equals(add)) {
            userProfile.getEducations().add(new Education());
        } else if("skill".equals(add)) {
            userProfile.getSkills().add(""); // empty String
        }

        model.addAttribute("userProfile", userProfile);

        System.out.println("/edit GET THEME: " + userProfile.getTheme());

        userProfile.getJobs().add(new Job());
        return "profile-edit";

//        TODO:  REFACTOR SOME OF THIS REPETITIVE CODE INTO A SERVICE METHOD OR CLASS
    }

    @PostMapping("/edit") // form post request to this same url as above and we get the model object
    public String postEdit(Model model, Principal principal, @ModelAttribute UserProfile userProfile) {
//        model.addAttribute("userId", principal.getName());
       // return "profile-edit";
        String userName = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));
        UserProfile savedUserProfile = userProfileOptional.get();
        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(userName);
        userProfileRepository.save(userProfile);

        System.out.println("/edit POST THEME: " + userProfile.getTheme());
        return "redirect:/view/" + userName;

        // save the updated values from the form
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
//        userProfileOptional.orElseThrow(() -> new RuntimeException("Not Found: " + userId));
//        UserProfile savedUserProfile = userProfileOptional.get();
//        userProfile.setId(savedUserProfile.getId());
//        userProfile.setUserName(userId); // could also: savedUserProfile.getName...
//
//        userProfileRepository.save(userProfile);
//        // redirect to user profile
//        System.out.println("USERID: "  + userId);
//
//        return "redirect:/view/" + userId;
    }


    @GetMapping("/view/{userId}") // better name would be 'userName' as opposed to id - we expect a string!
    public String view(@PathVariable String userId, Model model) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
//        System.out.println("USER PROFILE: " + userProfileOptional.toString());
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));


        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);

        // print to console for
        System.out.println(userProfile.toString());

//        return "profile-templates/" + userProfile.getT
//        return "profile-templates/" + userProfile.get().getTheme() + "/index";
        System.out.println("VIEW/ : " + userProfile.getTheme());
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
