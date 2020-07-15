package com.example.demorest02.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "employee")
@Entity
@Data
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    private String email;
}
