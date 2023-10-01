package firegruppen.artbid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import firegruppen.artbid.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
    


}
