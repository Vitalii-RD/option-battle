package com.optionbattleapp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.optionbattleapp.DTOs.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonSerialize
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String username;
    @JsonIgnore
    private String password;
    @Column(name = "registered_on")
    private Timestamp registeredOn;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tournament> tournaments;

    public User(long id) {
        this.id = id;
    }

    public User(UserDTO userDTO) {
        this.username = userDTO.getName();
        this.password = userDTO.getPassword();
    }
}
