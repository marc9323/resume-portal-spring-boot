package io.javabrains.resumeportal;

import io.javabrains.resumeportal.models.User;
import io.javabrains.resumeportal.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserName(String userName); // UserProfile - jb error

   // Optional<UserProfile> findByUserId(String userId); // check to cut
}
