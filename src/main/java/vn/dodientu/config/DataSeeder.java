package vn.dodientu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.dodientu.model.Role;
import vn.dodientu.model.User;
import vn.dodientu.repository.RoleRepository;
import vn.dodientu.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {

            // Seed roles if they don't exist
            if (roleRepository.findByName("admin").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("admin");
                roleRepository.save(adminRole);
                System.out.println("Admin role seeded");
            }

            if (roleRepository.findByName("customer").isEmpty()) {
                Role customerRole = new Role();
                customerRole.setName("customer");
                roleRepository.save(customerRole);
                System.out.println("Customer role seeded");
            }

            if (roleRepository.findByName("manager").isEmpty()) {
                Role managerRole = new Role();
                managerRole.setName("manager");
                roleRepository.save(managerRole);
                System.out.println("Manager role seeded");
            }

            if (userRepository.findByEmail("enderthelord@gmail.com").isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setFullName("John Smith");
                adminUser.setEmail("enderthelord@gmail.com");
                adminUser.setPassword(passwordEncoder.encode("12345"));  // Encode password for security
                // Fetch the 'ADMIN' role from the RoleRepository
                Role adminRole = roleRepository.findByName("Admin")
                        .orElseThrow(() -> new RuntimeException("Admin role not found"));

                // Set the role for the user
                adminUser.setRole(adminRole);

                userRepository.save(adminUser);
                System.out.println("Admin user seeded");
            } else {
                System.out.println("Admin user already exists");
            }
        };
    }
}
