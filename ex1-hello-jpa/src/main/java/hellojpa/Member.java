package hellojpa;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity //jpa를 사용하는 애구나~내가 관리해야지
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
   // @Column(name = "TEAM_ID")
   // private Long teamId;

    @ManyToOne //member입장에서 many team 은 one
    @JoinColumn(name ="TEAM_ID") //join 하는 z
    private Team team;

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
    }
}
