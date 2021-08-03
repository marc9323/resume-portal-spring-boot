package io.javabrains.resumeportal.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.format.FormatStyle;

@Entity
@Table
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

   // @NotEmpty
    private String company;

    // @NotEmpty
    private String designation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private boolean isCurrentJob;

    @ElementCollection(targetClass=String.class)
    private List<String> responsibilities = new ArrayList();

    public List<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public void addResponsibility(String responsibility){
        responsibilities.add(responsibility);
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

//    public boolean getCurrentJob() {
//        return isCurrentJob;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

//    public LocalDate getFormattedStartDate() {
//        // return startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
//        System.out.println("getFormattedStartDate()");
//        System.out.println("of: " + startDate);
//        System.out.println("To: " + startDate.format(DateTimeFormatter.ofPattern("MMM yyyy")));
//       // return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
//        return startDate;
//    }

    public String getFormattedStartDate() {
      //  return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(startDate);
    }

    public String getFormattedEndDate() {
       // return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(startDate);
    }

//    public LocalDate getFormattedEndDate() {
//        System.out.println("getFormattedEndDate()");
//        System.out.println("of: " + endDate);
//        System.out.println("To: " + endDate.format(DateTimeFormatter.ofPattern("MMM yyyy")));
//        return endDate;
//       // return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
//    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", designation='" + designation + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isCurrentJob=" + isCurrentJob +
                '}';
    }
}


//TODO:  foregin key reference in UserProfile