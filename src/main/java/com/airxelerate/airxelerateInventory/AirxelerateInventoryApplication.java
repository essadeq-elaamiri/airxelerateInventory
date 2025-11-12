package com.airxelerate.airxelerateInventory;

import com.airxelerate.airxelerateInventory.entity.AppUser;
import com.airxelerate.airxelerateInventory.enums.UserRole;
import com.airxelerate.airxelerateInventory.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AirxelerateInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirxelerateInventoryApplication.class, args);
	}


    @Bean
    CommandLineRunner initUsers(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder){
        return (args)->{
            if (appUserRepository.count() == 0) {
                AppUser user = AppUser
                        .builder()
                        .fullName("user")
                        .email("user@test.com")
                        .password(passwordEncoder.encode("1234"))
                        .userRole(UserRole.USER)
                        .build();

                AppUser admin = AppUser
                        .builder()
                        .fullName("admin")
                        .email("admin@test.com")
                        .password(passwordEncoder.encode("1234"))
                        .userRole(UserRole.ADMIN)
                        .build();

                appUserRepository.save(user);
                appUserRepository.save(admin);

            }
        };
    }
}
