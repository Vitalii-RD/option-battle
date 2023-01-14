package com.optionbattleapp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@JsonSerialize
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "AUTHORS")
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String username;
    @JsonIgnore
    private String password;
    @Column(name = "registered_on")
    private Timestamp registeredOn;
    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tournament> tournaments;

    public Author (long id) {
        this.id = id;
    }
}
