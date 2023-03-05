package com.optionbattleapp.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TOURNAMENTS")
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    @JoinTable(name = "TOURNAMENTS_TO_BATTLE_OPTIONS",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "battle_option_id"))
    private List<BattleOption> battleOptions;

    public Tournament(User user, String name, Date createdOn, Date updatedOn, List<BattleOption> battleOptions) {
        this.user = user;
        this.name = name;
        this.createdOn = Timestamp.from(createdOn.toInstant());
        this.createdOn = Timestamp.from(updatedOn.toInstant());
        this.battleOptions = battleOptions;
    }
}