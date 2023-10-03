package firegruppen.artbid.configuration;

import firegruppen.artbid.entity.Artwork;
import firegruppen.artbid.entity.Member;
import firegruppen.artbid.entity.Review;
import firegruppen.artbid.repository.ArtworkRepository;
import firegruppen.artbid.repository.MemberRepository;
import firegruppen.artbid.repository.ReviewRepository;
import firegruppen.security.entity.Role;
import firegruppen.security.entity.UserWithRoles;
import firegruppen.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class DeveloperData implements ApplicationRunner {
    
    MemberRepository memberRepository;
    ReviewRepository reviewRepository;
    ArtworkRepository artworkRepository;
    public DeveloperData(MemberRepository memberRepository, ArtworkRepository artworkRepository, ReviewRepository reviewRepository) {
        this.memberRepository = memberRepository;
        this.artworkRepository = artworkRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("Hello from DeveloperData");

        Member m2 = new Member("Fisk","fisk","fisk@fisk.com","Fisk","Fisken","Fiskevej","Fiskeby","0002");
        Artwork art2 = new Artwork("title","art","this is art",10.0, LocalDate.now(),true);
        Review r2 = new Review("god",5,LocalDate.now(),art2,m2);
        memberRepository.save(m2);



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

        Member m1 = new Member("username1", "Ole", "Olsen", "Olsensvej", "Olsenstad", "0001", "test@m.com", "test123", LocalDateTime.now(), LocalDateTime.now(), true, true, true, new ArrayList<>(Arrays.asList(Role.USER, Role.ADMIN)));

        //Reviews for internal tests
        Artwork art1 = new Artwork("title","art","this is art",10.0, LocalDate.now(),true);
        Review r1 = new Review("Good art", 5, LocalDate.now(), art1, m1);
        artworkRepository.save(art1);
        memberRepository.save(m1);
        reviewRepository.save(r1);
    }



}