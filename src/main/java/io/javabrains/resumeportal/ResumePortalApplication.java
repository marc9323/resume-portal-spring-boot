package io.javabrains.resumeportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses=UserRepository.class)
@EnableJpaRepositories
public class ResumePortalApplication {

//	@Autowired
//	private JdbcTemplate jdbcTemplate; //

	public static void main(String[] args) {

		SpringApplication.run(ResumePortalApplication.class, args);
	}

//	public void run(String... args) throws Exception {
//		String sql = "insert into users ('foo', 'foo', 'true', 'USER')";
//
//		jdbcTemplate.update(sql);
//
//	}

}
