package firegruppen.artbid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import firegruppen.artbid.dto.MemberRequest;
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

        return members.stream().map((member -> new MemberResponse(member, false, false))).toList();
    }

    public MemberResponse findById(String username){
        Member member = memberRepository.findById(username).orElseThrow();
        return new MemberResponse(member, true, true);
    }

    public MemberResponse addMember(MemberRequest body){
        if (memberRepository.existsById(body.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        Member newMember = Member.getMemberEntity(body); // Call the getMemberEntity method on the Member class
        newMember = memberRepository.save(newMember);
        return new MemberResponse(newMember, false, false);
    }

    public void editMember(MemberRequest body, String username){
        Member member = getMemberByUsername(username);
        
        if(!username.equals(body.getUsername())){
            throw new RuntimeException("Username cannot be changed");
        }

        member.setFirstName(body.getFirstName());
        member.setLastName(body.getLastName());
        member.setStreet(body.getStreet());
        member.setCity(body.getCity());
        member.setZipCode(body.getZipCode());
        member.setEmail(body.getEmail());
        member.setPassword(body.getPassword());
        memberRepository.save(member);
    }

    public void deleteMember(String username){
        Member member = getMemberByUsername(username);
        memberRepository.delete(member);
    }

    private Member getMemberByUsername(String username){
        return memberRepository.findById(username).orElseThrow();
    }
    
}