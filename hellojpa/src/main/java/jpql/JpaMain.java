package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

            Team teamA = new Team();
            teamA.setName("팀A");


            Team teamB = new Team();
            teamB.setName("팀B");

            em.persist(teamA);
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.changeTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.changeTeam(teamB);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.changeTeam(teamB);
            em.persist(member3);
            System.out.println("==============================");

            em.flush();
            em.clear();

            String query = "select m from Member m";
            System.out.println("==============================");
            List<Member> result = em.createQuery(query, Member.class).getResultList();
            System.out.println("==============================");

            for(Member member : result){
                System.out.println(member.getUsername() + " , " + member.getTeam().getName());
            }


            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}