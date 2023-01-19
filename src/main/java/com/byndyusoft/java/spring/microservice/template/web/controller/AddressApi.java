package com.byndyusoft.java.spring.microservice.template.web.controller;

import com.byndyusoft.java.spring.microservice.template.model.Address;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AddressApi {

  @GetMapping("/addresses/{addressId}")
  ResponseEntity<Address> get(@PathVariable final String addressId);

  @PostMapping("/addresses")
  ResponseEntity<Address> create(@RequestBody @Valid final Address requestBody);
}
