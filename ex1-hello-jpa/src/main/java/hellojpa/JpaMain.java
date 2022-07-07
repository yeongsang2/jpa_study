package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.management.MemoryManagerMXBean;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String args[]){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("username");
            member.setHomeAddress(new Address("homeCity","street", "10000" ));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address("old1","street", "10000" ));
            member.getAddressHistory().add(new Address("old2","street", "10000" ));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("============= START ==========");
            Member findMember = em.find(Member.class, member.getId());

            List<Address> addressHistory = findMember.getAddressHistory();
            for(Address address :addressHistory){
                System.out.println("address = " + address.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for(String favoriteFood : favoriteFoods){
                System.out.println("favoriteFood = " + favoriteFood);
            }

            //findMember.getHomeAddress().setCity("newcity"); 수정하지 말고 새로 instance 삽입(완전 교체)
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newcity", a.getStreet(), a.getZipcode()));

            findMember.getAddressHistory().remove(new Address("old2","street", "10000"));
            findMember.getAddressHistory().add(new Address("newcity1","street", "10000"));


            tx.commit(); //transaction.commit() 하 는 시점에 database 에 query 날림
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
