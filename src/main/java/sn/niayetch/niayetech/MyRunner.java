package sn.niayetch.niayetech;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.niayetch.niayetech.entity.Role;
import sn.niayetch.niayetech.repository.RoleRepository;

import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

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
    }
}
