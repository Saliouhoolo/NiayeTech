package sn.niayetch.niayetech;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sn.niayetch.niayetech.entity.Role;
import sn.niayetch.niayetech.entity.User;
import sn.niayetch.niayetech.repository.RoleRepository;
import sn.niayetch.niayetech.repository.UserRepository;

import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role("ROLE_ADMIN");
        Role acheteur = new Role("ROLE_ACHETEUR");
        Role expert = new Role("ROLE_EXPERT");
        Role vendeur = new Role("ROLE_VENDEUR");

        Role roleFind = roleRepository.findByName(admin.getName());
        if(roleFind!=null) {
            return;
        }else{
            roleRepository.saveAll(List.of(admin, acheteur, expert,vendeur));
        }
        List<Role> roles = roleRepository.findAll();

        roles.forEach((role) ->
        {
            System.out.println(role.getName());
        });
        User user = new User();
        user.setNom("admin");
        user.setPrenom("admin");
        user.setEmail("admin@admin.com");
        user.setPassword("admin");
        user.setRole("admin");
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.addRole(role);
        user.setPassword (encoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println(user.getPrenom());

    }
}
