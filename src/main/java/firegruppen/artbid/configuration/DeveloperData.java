package firegruppen.artbid.configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import firegruppen.artbid.entity.Auction;
import firegruppen.artbid.repository.AuctionRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import firegruppen.artbid.entity.Member;
import firegruppen.artbid.repository.MemberRepository;
import firegruppen.security.entity.Role;
import firegruppen.security.entity.UserWithRoles;
import firegruppen.security.repository.UserWithRolesRepository;

@Configuration
public class DeveloperData implements ApplicationRunner {
    
    MemberRepository memberRepository;
    AuctionRepository auctionRepository;

    public DeveloperData(MemberRepository memberRepository, AuctionRepository auctionRepository) {
        this.memberRepository = memberRepository;
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("Hello from DeveloperData");

        setupUserWithRoles();

        List<Auction> auctions = new ArrayList<>();
        auctions.add(new Auction(1, LocalDate.now(),LocalDate.now().plusDays(30), 1000, 3000, 5 ));
        auctions.add(new Auction(2, LocalDate.now(),LocalDate.now().plusDays(3), 5000, 63000, 2000 ));
        auctionRepository.saveAll(auctions);
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

        UserWithRoles user1 = new UserWithRoles("user1", "user1@a.com", "passwordUsedByAll");
        UserWithRoles user2 = new UserWithRoles("user2", "user2@a.com", "passwordUsedByAll");
        UserWithRoles user3 = new UserWithRoles("user3", "user3@a.com", "passwordUsedByAll");
        UserWithRoles user4 = new UserWithRoles("user4", "user4@a.com", "passwordUsedByAll");
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