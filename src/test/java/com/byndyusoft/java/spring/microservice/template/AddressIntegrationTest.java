package com.byndyusoft.java.spring.microservice.template;

import com.byndyusoft.java.spring.microservice.template.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class AddressIntegrationTest extends BaseIntegrationTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void getByIdSuccessful() throws JsonProcessingException {
    //given
    final String addressId = "addressId";
    final Address address = new Address(addressId, "description");
    final String addressAsString = objectMapper.writeValueAsString(address);
    createAddress(addressAsString, HttpStatus.CREATED, addressAsString);

    //when
    getAddress(addressId, HttpStatus.OK, addressAsString);
  }
}
