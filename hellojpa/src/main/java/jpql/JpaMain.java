package jpql;

import javax.persistence.*;
    import java.util.Iterator;
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

/*
            List<Member> result = em.createQuery("Member.findByUsername", Member.class)
//            List<Member> result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "회원1")
                    .getResultList();
            //String query = "select m from Member m join fetch m.team";
*/
//            String query = "select t from Team t join t.members"; // join t.members m

        //    String query = "select t from Team t join fetch t.members";

//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//            System.out.println("==============================");
            int resultCount = em.createQuery("update Member m set m.age = 20").executeUpdate(); //벌크 연산

            em.clear(); // 벌크연산 후 영속성 컨텍스트 초기화
            Member findMember = em.find(Member.class, member1.getId());

            System.out.println(findMember.getAge());


//            for(Memb  er member : result){
//                System.out.println(member.getUsername() + " , " + member.getTeam().getName());
//            }


            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}