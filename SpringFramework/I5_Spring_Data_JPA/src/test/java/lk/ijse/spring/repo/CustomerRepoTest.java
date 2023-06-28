package lk.ijse.spring.repo;

import lk.ijse.spring.config.WebAppConfig;
import lk.ijse.spring.config.WebRootConfig;
import lk.ijse.spring.controller.CustomerController;
import lk.ijse.spring.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ContextConfiguration(classes = {WebRootConfig.class}) // for test context configurations
@ExtendWith(SpringExtension.class)
@Transactional // stop submitting actual data to the database
class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;

    @Test
    public void addCustomer(){
        Customer customer = new Customer("C001","Dasun Perera","Galle",new BigDecimal(100));
        customerRepo.save(customer);
    }


    @Test
    public void testOne() {
        List<Customer> dasunPerera = customerRepo.findByName("Dasun Perera");
        for (Customer customer : dasunPerera) {
            System.out.println(customer.toString());
        }
    }

    @Test
    public void testTwo() {
        Customer data = customerRepo.findByNameAndAddress("Thamalsha","Mathara");
        System.out.println(data.toString());
    }


    @Test
    public void testThree() {
//        Customer data1 = customerRepo.findByName("Thamalsha");
        Customer data2 = customerRepo.readByName("Thamalsha");
        Customer data3 = customerRepo.getByName("Thamalsha");
        Customer data4 = customerRepo.queryByName("Thamalsha");
        Customer data5 = customerRepo.searchByName("Thamalsha");
        Customer data6 = customerRepo.streamByName("dThamalsha");
        assertNull(data6);
//        System.out.println(data1.toString());
        System.out.println(data2.toString());
        System.out.println(data3.toString());
        System.out.println(data4.toString());
        System.out.println(data5.toString());
//        System.out.println(data6.toString());
    }

    @Test
    public void testFour() {
        Long count = customerRepo.countByName("Dasun Perera");
        System.out.println(count);

    }

    @Test
    public void testFive() {

        Boolean response1 = customerRepo.existsByAddress("Galle");
        assertTrue(response1);// this expects a true value, otherwise test case is false


        Boolean response2 = customerRepo.existsByAddress("Colombo");
        assertFalse(response2);// this expects a false value, otherwise test case is false

    }


    @Test
    public void testSix() {
        customerRepo.removeByName("Dasun Perera");
        customerRepo.deleteByName("Dasun Perera");
    }

    @Test
    public void testSeven(){
        List<Customer> customers = customerRepo.testOneNativeQuery();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
        System.out.println("============");
        Customer customer = customerRepo.testOneNativeQueryWithName();
        System.out.println(customer.toString());

    }

    @Test
    public void testEight(){
//        Customer customer = customerRepo.testOneNativeQueryWithNameP1("Thamalsha","Mathara");
//        System.out.println(customer.toString());

        Customer customer = customerRepo.testOneNativeQueryWithNameP2("Thamalsha","Mathara");
        System.out.println(customer.toString());

    }

    @Test
    public void testNine(){
        Customer thamalsha = customerRepo.jpqlTestQuery("Thamalsha");
        System.out.println(thamalsha.toString());

        Customer thamalsha1 = customerRepo.hqlTestQuery("Thamalsha");
        System.out.println(thamalsha1);

    }

}