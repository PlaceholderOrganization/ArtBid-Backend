package firegruppen.artbid.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import firegruppen.artbid.dto.MemberResponse;
import firegruppen.artbid.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Security ???
    @GetMapping
    List<MemberResponse> getMembers(){
        return memberService.getMembers();
    }

}