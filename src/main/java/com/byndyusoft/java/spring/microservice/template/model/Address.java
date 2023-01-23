package com.byndyusoft.java.spring.microservice.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Address {

  @Id
  @Column(name = "address_id")
  private String addressId;
  @Column(name = "description")
  private String description;

  public Address() {
  }
}
