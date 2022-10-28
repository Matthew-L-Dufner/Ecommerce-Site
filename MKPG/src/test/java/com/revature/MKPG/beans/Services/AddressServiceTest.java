package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.AddressRepository;
import com.revature.MKPG.entities.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address;

    @BeforeEach
    public void setup(){
        address = Address.builder()
        .addressId(1)
        .street("2428 Central ave")
        .city("San Francisco")
        .state("CA")
        .zipCode("94402")
        .type("Shipping")
                .build();
    }

    @DisplayName("JUnit test for createAddress method")
    @Test
    void testForCreateAddress() {
        given(addressRepository.save(address)).willReturn(address);

        Address savedAddress = addressService.createAddress(address);

        assertThat(savedAddress).isNotNull();
        assertThat(savedAddress.getAddressId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for getAllAddresses method")
    @Test
    void testForGetAddresses() {
     Address address1 = Address.builder()
                .addressId(2)
                .street("456 Fulton St.")
                .city("Alameda")
                .state("CA")
                .zipCode("94456")
                .type("Billing")
                .build();

     given(addressRepository.findAll()).willReturn(List.of(address1, address));
     List<Address> addressList = addressService.getAddresses();

     assertThat(addressList).isNotNull();
     assertThat(addressList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getAddressById method")
    @Test
    void testForGetAddressById() {
        given(addressRepository.findById(1)).willReturn(Optional.of(address));
        Address savedAddress = addressService.getAddressById(address.getAddressId()).get();

        assertThat(savedAddress).isNotNull();
    }

    @DisplayName("JUnit test for updateAddress method")
    @Test
    void testForUpdateAddress() {
        given(addressRepository.save(address)).willReturn(address);
        address.setStreet("48 Central st.");
        address.setCity("Asheville");
        address.setState("NC");
        address.setZipCode("28801");

        Address updateAddress = addressService.updateAddress(address);
        assertThat(updateAddress.getStreet()).isEqualTo("48 Central st.");
        assertThat(updateAddress.getCity()).isEqualTo("Asheville");
        assertThat(updateAddress.getState()).isEqualTo("NC");
        assertThat(updateAddress.getZipCode()).isEqualTo("28801");
    }

    @DisplayName("JUnit test for deleteAddressById method")
    @Test
    void testForDeleteById() {
        int addressId = 1;
        willDoNothing().given(addressRepository).deleteById(addressId);

        addressService.deleteById(addressId);
        verify(addressRepository, times(1)).deleteById(addressId);
    }
}