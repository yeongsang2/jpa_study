package jpapractice.practice.domain;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            User user = new User();
            user.setUserName("A");

            UserInfo userInfo = new UserInfo();
            userInfo.setNickName("nickname1");
            userInfo.setPosition("kakao");
            userInfo.setSchool("seoul");
            em.persist(userInfo);

            UserInterest userInterest = new UserInterest();
            userInterest.setInterest("java");
            em.persist(userInterest);

            userInfo.setUserInterest(userInterest);
            user.setUserInfo(userInfo);
            em.persist(user);

            Post post1 = new Post();
            post1.setUser(user);
            post1.setTitle("jpa책팜");
            post1.setContent("jpa책팔아요");
            post1.setPrice("10000");

            Book book1 = new Book();
            book1.setName("jpa 표준");
            book1.setAuthor("김영한");

            Book book2 = new Book();
            book2.setName("spring boot");
            book2.setAuthor("호돌맨");

            em.persist(book1);
            em.persist(book2);
//
            post1.setBook(book1);
            em.persist(post1);
//
            Post post2 = new Post();
            post2.setTitle("spring책팜");
            post2.setContent("spring책 팔아요");
//
            post2.setUser(user);
            post2.setBook(book2);
            post2.setPrice("20000");
            em.persist(post2);
//
//            em.clear();
//            em.flush();

            User user1 = em.find(User.class, user.getId());
            List<Post> postList = user1.getPostList();
            System.out.println(user1.getUserName() + " 의  책 판매 목록");

            for(Post post : postList){
                System.out.println(" 책이름 " + post.getBook().getName());
                System.out.println(" 저자 " + post.getBook().getAuthor());
                System.out.println(" 가격 " + post.getPrice());
            }

            tx.commit(); //transaction.commit() 하 는 시점에 database 에 query 날림
            System.out.println("_-------------------------");
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
