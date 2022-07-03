package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String args[]){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            //team.getMembers().add(member);

            em.persist(team); //영속상태

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);


            em.flush();// 영속성 컨텍스트에 있는내용 db에 query 날리고
            em.clear(); // 영속성 컨텍스트 초기화

            Team findTeam = em.find(Team.class, member.getTeam());
            List<Member> members = findTeam.getMembers();

            for(Member m : members){
                System.out.println(m.getUsername());
            }
            tx.commit(); //transaction.commit() 하는 시점에 database 에 query 날림
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
