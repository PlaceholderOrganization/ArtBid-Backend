package firegruppen.artbid.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import firegruppen.artbid.entity.Member;
import firegruppen.artbid.repository.MemberRepository;

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
        members.add(new Member("un1", "pw1", "fn1", "ln1", "em1"));
        members.add(new Member("un2", "pw2", "fn2", "ln2", "em2"));
        members.add(new Member("un3", "pw3", "fn3", "ln3", "em3"));

        memberRepository.saveAll(members);

    }
}