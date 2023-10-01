package firegruppen.artbid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import firegruppen.artbid.dto.MemberResponse;
import firegruppen.artbid.entity.Member;
import firegruppen.artbid.repository.MemberRepository;



@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(){
        List<Member> members = memberRepository.findAll();

        return members.stream().map((member -> new MemberResponse(member))).toList();
    }
    
}