package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.CustomerRepo;
import com.revature.MKPG.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTests {
    @Mock
    private CustomerRepo customerRepo;
    @InjectMocks
    private CustomerService customerService;

    private Customer  customer;

    @BeforeEach
            public void setup(){
//            List<Customer> customers = Arrays.asList(
//                    new Customer("sajedul", "kidu@bishaw.com", "karim", "01737186095", new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2000"), new SimpleDateFormat("dd/MM/yyyy").parse("16/05/2022")),
//                    new Customer( "nafis", "nafis@khan.com", "khan", "01737186096", new SimpleDateFormat("dd/MM/yyyy").parse("15/05/1990"), new SimpleDateFormat("dd/MM/yyyy").parse("16/08/2021")),
//                    new Customer( "aayan", "aayan@karim.com", "karim", "01737186097",new SimpleDateFormat("dd/MM/yyyy").parse("25/05/1980"), new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2020"))
//            );
        try {
            customer = Customer.builder()
                    .customerId(1)
                    .firstName("Kidist")
                    .lastName("Bishaw")
                    .email("kidu@bishaw.com")
                    .password("pass123")
                    .phone("1233455678")
                    .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("05/05/1980"))
                    .created(new SimpleDateFormat("dd/MM/yyyy").parse("16/05/2022"))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("JUnit test for createCustomer method")
    @Test
    public void testForCreateCustomer(){
            given(customerRepo.findByEmail(customer.getEmail()))
                    .willReturn(Optional.empty());

            given(customerRepo.save(customer)).willReturn(customer);

            Customer savedCustomer = customerService.createCustomer(customer);

            assertThat(savedCustomer).isNotNull();
    }

    @DisplayName("JUnit test for getAllCustomers method")
    @Test
    public void testForGetAllCustomer(){
        try {
            Customer customer1 = Customer.builder()
                    .customerId(2)
                    .firstName("Dayna")
                    .lastName("Jones")
                    .email("dayna@jones.com")
                    .password("pass")
                    .phone("4354356789")
                    .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("05/05/1998"))
                    .created(new SimpleDateFormat("dd/MM/yyyy").parse("12/09/2022"))
                    .build();

            given(customerRepo.findAll()).willReturn(List.of(customer,customer1));
            List<Customer> customerList = customerService.getAllCustomers();

            assertThat(customerList).isNotNull();
            assertThat(customerList.size()).isEqualTo(2);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("JUnit test for getCustomerById method")
    @Test
    public void testForGetCustomerById(){
        given(customerRepo.findById(1)).willReturn(Optional.of(customer));

        Customer savedCustomer = customerService.getCustomerById(customer.getCustomerId()).get();

        assertThat(savedCustomer).isNotNull();
    }

    @DisplayName("JUnit test for updateCustomer method")
    @Test
    public void testForUpdateCustomer(){
        given(customerRepo.save(customer)).willReturn(customer);
        customer.setEmail("kidu@gmial.com");
        customer.setLastName("Berhe");

        Customer updatedCustomer = customerService.updateCustomer(customer);

        assertThat(updatedCustomer.getEmail()).isEqualTo("kidu@gmial.com");
        assertThat(updatedCustomer.getLastName()).isEqualTo("Berhe");
    }

    @DisplayName("JUnit test for deleteCustomer method")
    @Test
    public void testForDeleteCustomer(){
        int customerId = 1;

        willDoNothing().given(customerRepo).deleteById(customerId);

        customerService.deleteById(customerId);

        verify(customerRepo, times(1)).deleteById(customerId);
    }

}
