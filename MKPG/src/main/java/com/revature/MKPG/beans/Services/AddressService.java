package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.AddressRepository;
import com.revature.MKPG.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Integer id){
        return addressRepository.findById(id);
    }

    public Address createAddress(Address address){
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address){
        return addressRepository.save(address);
    }

    public void deleteById(Integer id){ addressRepository.deleteById(id);}
}
