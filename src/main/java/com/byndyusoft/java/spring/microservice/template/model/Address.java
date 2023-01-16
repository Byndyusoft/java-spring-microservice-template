package com.byndyusoft.java.spring.microservice.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "address")
@Data
@Accessors(chain = true)
public class Address {

  @Id
  @Column(name = "address_id")
  private String addressId;
  @Column(name = "description")
  private String description;
}
