package com.dilaraalk.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // 'user' PostgreSQL'de reserved keyword olabilir
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role") 
    private List<String> roles;
}