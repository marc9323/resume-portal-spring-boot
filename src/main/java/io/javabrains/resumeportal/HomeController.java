package io.javabrains.resumeportal;


import io.javabrains.resumeportal.models.Education;
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
