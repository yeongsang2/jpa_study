package hellojpa;

public class ValueMain {

    public static void main(String[] args){

        int a = 10;
        int b = 10;


        Address address1 = new Address("city", "street", "zipcode");
        Address address2 = new Address("city", "street", "zipcode");

        System.out.println("address1 == address2: " + (address1 == address2));
        System.out.println("address1 equals address2: " + (address1.equals(address2)));
    }
}
