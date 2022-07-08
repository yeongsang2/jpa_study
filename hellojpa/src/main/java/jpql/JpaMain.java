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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.USER);
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();


            //Member member= new Member();
            //member.setUsername("member1");
            //member.setAge(10);
            //em.persist(member);

//            for(int i=0; i< 100;i++){
//                Member member = new Member();
//                member.setUsername("member" + i);
//                member.setAge(i);
//                em.persist(member);
//            }
//            //Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)//타입정보 정확하게 며잇
              //      .setParameter("username", "member1")
                //    .getSingleResult();
            //System.out.println(result.getUsername());

            //Query query1 = em.createQuery("select m.username, m.age from Member m");//타입정보 정확하게 며잇
            //TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);

            //임베디드타입List<Address> resultList = em.createQuery("select o.address from Order o", Address.class).getResultList();

            //쿼리타입 조회
            //List<Object[]> resultList = em.createQuery("select m.username, m.age").getResultList();
            //object[] 타입으로 조회
           // Object o = resultList.get(0);
            // Object[] result = (Object[])o
            //Object[] result = resultList.get((0)); //type캐스팅?
            //System.out.println("---------------");
            //new명령어로 조회
            //List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member as m", MemberDTO.class).getResultList();

            //MemberDTO memberDTO = result.get(0);
            //System.out.println(memberDTO.getUsername());
            //System.out.println(memberDTO.getAge());

//            String query = "select m from Member m inner join m.team t where t"; //inner join query
//            String query = "select m from Member m left join m.team t where t"; //
//            String query = "select m from Member m, Team t where m.username = t.name"; //세타조인 join query
            //String query = "select m from Member m left join Team t on m.username = t.name"; //연관관계 없는 엔티티 외부 조인?
            //String query = "select m.username, 'HEL LO', TRUE from Member m" + "where m.type = jpql.MemberType.USER";
//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                            "     when m.age >= 60 then '경로요금' "+
//                            "     else '일반요금' " +
//         2                   "end "+
//                            "from Member m";
//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
            String query = "select nullif(m.username, '관리자') as username from Member m";

            List<String> result = em.createQuery(query, String.class).getResultList();
//          List<Member> result = em.createQuery("select m from Member as m order by m.age desc", Member.class)
//            List<Member> result = em.createQuery(query, Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
            System.out.println("-------------");
//            List<Object[]> result = em.createQuery(query).getResultList();
            System.out.println("-------------");

            for(String s: result){
                System.out.println("s = " + s);
            }

//            for(Object[] objects: result) {
//                System.out.println(objects[0]);
//                System.out.println(objects[1]);
//                System.out.println(objects[2]);
//
//            }
//            System.out.println(result.size());
//            for(Member m : result){
//                System.out.println(m);
//            }


            //System.out.println(result[0]);
            //System.out.println(result[1]);


            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}