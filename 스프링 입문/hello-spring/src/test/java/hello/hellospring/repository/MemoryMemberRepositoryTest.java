package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){
        //테스트가 끝날때마다 메모리를 초기화 해줘야 한다.
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("member = result is " + (member == result)); // 방법 1
        org.junit.jupiter.api.Assertions.assertEquals(member, result); // 방법2
        assertThat(member).isEqualTo(result); // 방법 3
    }

    @Test
    public  void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public  void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save((member1));

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save((member2));

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //왜 이거만들고 테스트하면 에러? > 테스트 순서는 보장되지 않는다
        //테스트가 끝날때마다 메모리를 초기화 해줘야 한다.
    }
}
