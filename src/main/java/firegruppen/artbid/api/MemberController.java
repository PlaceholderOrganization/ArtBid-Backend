package firegruppen.artbid.api;

import firegruppen.artbid.dto.MemberRequest;
import firegruppen.artbid.dto.MemberResponse;
import firegruppen.artbid.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Security ???
    @GetMapping("/{username}")
    MemberResponse getMemberById(String username){
        return memberService.findById(username);
    }

    // Security ???
    @PostMapping
    MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }
    
    // Security ???
    @PutMapping("/{username}")
    void editMember(@RequestBody MemberRequest body, @PathVariable String username){
        memberService.editMember(body, username);
    }

    // Security ???
    @DeleteMapping("/{username}")
    void deleteMember(@PathVariable String username){
        memberService.deleteMember(username);
    }
}