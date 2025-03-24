package com.bijay.userservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "users")
public class User extends BaseModel {
    @Column(unique = true)
    private String email;

    private String password;
}
