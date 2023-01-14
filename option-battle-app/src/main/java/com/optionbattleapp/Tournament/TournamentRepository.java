package com.optionbattleapp.Tournament;

import com.optionbattleapp.Entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    @Modifying
    @Query("delete from Tournament t where t.id = :id")
    void deleteById(Long id);
}
