package org.lss.eye.model.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "hello")
@AllArgsConstructor
@NoArgsConstructor
public class Hello {

  @Id
  // @GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hello_seq")
  @SequenceGenerator(name = "hello_seq", sequenceName = "hello_seq", allocationSize = 100)
  private Long id;

  @Column(name = "name")
  private String name;

  public Hello(String name) {
    this.name = name;
  }
}
