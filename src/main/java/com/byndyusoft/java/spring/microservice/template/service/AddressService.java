package com.byndyusoft.java.spring.microservice.template.service;

import com.byndyusoft.java.spring.microservice.template.model.Address;

public interface AddressService {

  Address getById(String addressId);

  Address create(Address address);
}
