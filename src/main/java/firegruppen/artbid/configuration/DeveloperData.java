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
import java.util.Collections;
import java.util.List;

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
      
        List<Artwork> artworks = new ArrayList<>();

        String defaultImage = "base64_encoded_image_string_here";

        artworks.add(new Artwork("Title", "Category", "Description", 50, true, Collections.singletonList(defaultImage)));
        artworks.add(new Artwork("Title1", "Category1", "Description1", 50, true, Collections.singletonList(defaultImage)));
        artworks.add(new Artwork("Title2", "Category2", "Description2", 50, false, Collections.singletonList(defaultImage)));
        artworkRepository.saveAll(artworks);

        Member m1 = new Member("username1", "Ole", "Olsen", "Olsensvej", "Olsenstad", "0001", "test@m.com", "test123", LocalDateTime.now(), LocalDateTime.now(), true, true, true, new ArrayList<>(Arrays.asList(Role.USER, Role.ADMIN)));
        memberRepository.save(m1);
        Review r1 = new Review("description",5, LocalDate.now(),artworks.get(0),m1);
        reviewRepository.save(r1);



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
        //Reviews for internal tests
    }
}
