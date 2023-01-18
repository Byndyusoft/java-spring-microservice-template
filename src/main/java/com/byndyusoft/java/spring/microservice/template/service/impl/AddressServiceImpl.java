package com.byndyusoft.java.spring.microservice.template.service.impl;

import com.byndyusoft.java.spring.microservice.template.exception.BaseException;
import com.byndyusoft.java.spring.microservice.template.exception.ErrorCode;
import com.byndyusoft.java.spring.microservice.template.model.Address;
import com.byndyusoft.java.spring.microservice.template.repository.AddressRepository;
import com.byndyusoft.java.spring.microservice.template.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;

  @Override
  public Address get(final String addressId) {
    return addressRepository.findById(addressId)
        .orElseThrow(() -> new BaseException(ErrorCode.ADDRESS_NOT_FOUND, addressId));
  }

  @Override
  @Transactional
  public Address create(final Address address) {
    addressRepository.findById(address.getAddressId()).ifPresent(a -> {
      throw new BaseException(ErrorCode.DUPLICATE_ADDRESS, a.getAddressId());
    });
    return addressRepository.save(address);
  }
}
