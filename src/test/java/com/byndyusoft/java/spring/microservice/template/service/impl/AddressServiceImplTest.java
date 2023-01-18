package com.byndyusoft.java.spring.microservice.template.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.byndyusoft.java.spring.microservice.template.exception.BaseException;
import com.byndyusoft.java.spring.microservice.template.exception.ErrorCode;
import com.byndyusoft.java.spring.microservice.template.model.Address;
import com.byndyusoft.java.spring.microservice.template.repository.AddressRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

  @InjectMocks
  private AddressServiceImpl addressService;

  @Mock
  private AddressRepository addressRepository;

  @Test
  void getSuccessful() {
    //given
    final String addressId = "addressId";
    final Address expectedAddress = new Address(addressId, "description");
    when(addressRepository.findById(addressId)).thenReturn(Optional.of(expectedAddress));

    //when
    final Address actualAddress = addressService.get(addressId);

    //then
    verify(addressRepository).findById(addressId);
    assertEquals(expectedAddress, actualAddress);
  }

  @Test
  void getThrowExIfNotFound() {
    //given
    final String nonExistAddressId = "addressId";
    when(addressRepository.findById(nonExistAddressId)).thenReturn(Optional.empty());

    //when
    final BaseException actualException = assertThrows(BaseException.class,
        () -> addressService.get(nonExistAddressId));

    //then
    verify(addressRepository).findById(nonExistAddressId);
    assertEquals(ErrorCode.ADDRESS_NOT_FOUND, actualException.getErrorCode());
  }
}