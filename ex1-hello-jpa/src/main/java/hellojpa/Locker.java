package hellojpa;

import javax.persistence.*;
import java.sql.PreparedStatement;


public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;  //읽기전용 locker.team

}
