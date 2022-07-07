package hellojpa;

import org.hibernate.cache.spi.support.AbstractDomainDataRegion;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Entity //jpa를 사용하는 애구나~내가 관리해야지
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Embedded
    //기간 period
    private Period wordPeriod;
    //주소
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name="FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS",
            joinColumns = @JoinColumn(name="MEMBER_ID") //member_id를 외래키로 설정
    )
    private List<Address> addressHistory = new ArrayList<>();

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column = @Column(name= "WORK_CITY")),
            @AttributeOverride(name="street",
                    column = @Column(name="WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column = @Column(name ="WORK_ZIPCODE"))
    })
    private Address workAddress;

    public Member() {
    }

    public Long getId() {

        return id;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
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

    public Period getWordPeriod() {
        return wordPeriod;
    }

    public void setWordPeriod(Period wordPeriod) {
        this.wordPeriod = wordPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }


}
