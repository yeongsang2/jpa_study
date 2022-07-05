package hellojpa;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity //jpa를 사용하는 애구나~내가 관리해야지
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
   // @Column(name = "TEAM_ID")
   // private Long teamId;



    @ManyToOne(fetch = FetchType.LAZY) //member입장에서 many team 은 one // fetch.lazy 로 가져옴
    @JoinColumn(name ="TEAM_ID") //join 하는 z
    private Team team;


    @OneToOne
    @JoinColumn(name ="LOCKER_ID")
    private Locker locker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }


    public void setTeam(Team team) {

        this.team = team;
        team.getMembers().add(this); // 연관관계 편의 메소드
    }
}
