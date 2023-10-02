package firegruppen.artbid.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;

import firegruppen.artbid.entity.Member;
import firegruppen.artbid.repository.MemberRepository;
import firegruppen.security.entity.Role;
import firegruppen.security.entity.UserWithRoles;
import firegruppen.security.repository.UserWithRolesRepository;

@Configuration
public class DeveloperData implements ApplicationRunner {
    
    MemberRepository memberRepository;

    public DeveloperData(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("Hello from DeveloperData");

        List<Member> members = new ArrayList<>();
        members.add(new Member("bruger1", "Sten", "En", "Gade 1", "By 1", "1234"));
        members.add(new Member("bruger2", "Birger", "To", "Gade 2", "By 2", "1234"));
        members.add(new Member("bruger3", "Paprika", "Tre", "Gade 3", "By 3", "1234"));
        memberRepository.saveAll(members);


        setupUserWithRoles();
    }

    @Autowired
    UserWithRolesRepository userWithRolesRepository;

    final String passwordUsedByAll = "test12";

    private void setupUserWithRoles() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");

        UserWithRoles user1 = new UserWithRoles("user1", "passwordUsedByAll", "user1@a.com");
        UserWithRoles user2 = new UserWithRoles("user2", "passwordUsedByAll", "user2@a.com");
        UserWithRoles user3 = new UserWithRoles("user3", "passwordUsedByAll", "user3@a.com");
        UserWithRoles user4 = new UserWithRoles("user4", "passwordUsedByAll", "user4@a.com");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);

    }



}