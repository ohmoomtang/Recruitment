package com.oot.Recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RecruitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentApplication.class, args);
	}

}
