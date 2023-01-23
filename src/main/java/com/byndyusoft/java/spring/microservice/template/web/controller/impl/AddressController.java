package com.byndyusoft.java.spring.microservice.template.web.controller.impl;

import com.byndyusoft.java.spring.microservice.template.model.Address;
import com.byndyusoft.java.spring.microservice.template.service.AddressService;
import com.byndyusoft.java.spring.microservice.template.web.controller.AddressApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressController implements AddressApi {

  private final AddressService addressService;

  public ResponseEntity<Address> get(final String addressId) {
    return ResponseEntity.ok(addressService.get(addressId));
  }

  public ResponseEntity<Address> create(final Address requestBody) {
    final Address address = addressService.create(requestBody);
    return ResponseEntity.status(HttpStatus.CREATED).body(address);
  }
}
