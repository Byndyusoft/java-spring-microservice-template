package com.byndyusoft.java.spring.microservice.template.repository;


import com.byndyusoft.java.spring.microservice.template.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {

}
