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
            //비영속 상태

            //Member member1 = new Member(150L, "A");
            //Member member2 = new Member(160L, "Bs");
            Member findMember = em.find(Member.class, 150L);
            findMember.setName("xxxx");
            //em.detach(findMember); 영속상태의 entity가 영속성 컨텍스트에서 분리 detach <특저 entity만 준영속 상태로 전환 >
            //em.clear(); //영속성 컨텍스트 완전히 초기화

            //em.persist(member1);
            //em.persist(member2); //영속성 컨텍스트에 쌓임
            System.out.println("++++");
            tx.commit(); //transaction.commit() 하는 시점에 database 에 query 날림
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
