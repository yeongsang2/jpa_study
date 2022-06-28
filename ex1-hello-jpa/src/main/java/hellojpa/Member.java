package hellojpa;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity //jpa를 사용하는 애구나~내가 관리해야지
@TableGenerator(
        name = "member_seq_generator",
        table = "my_sequences",
        pkColumnName = "member_seq",
        initialValue = 1,
        allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_generator")
    private Long id;
    @Column(name="name" ,nullable = false)
    private String userName;

    public Member(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
