package com.byndyusoft.java.spring.microservice.template.web.controller;

import com.byndyusoft.java.spring.microservice.template.model.Address;
import com.byndyusoft.java.spring.microservice.template.service.AddressService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressController {

  private final AddressService addressService;

  @GetMapping("/addresses/{addressId}")
  public ResponseEntity<Address> get(@PathVariable final String addressId) {
    return ResponseEntity.ok(addressService.get(addressId));
  }

  @PostMapping("/addresses")
  public ResponseEntity<Address> create(@RequestBody @Valid final Address requestBody) {
    final Address address = addressService.create(requestBody);
    return ResponseEntity.status(HttpStatus.CREATED).body(address);
  }
}
