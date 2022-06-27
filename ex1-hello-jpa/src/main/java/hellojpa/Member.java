package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //jpa를 사용하는 애구나~내가 관리해야지
public class Member {

    @Id //pk 가 뭔지 알려줌
    private Long id;
    private String name;

    public Member(){

    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
