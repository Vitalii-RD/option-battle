package com.optionbattleapp.Tournament;

import com.optionbattleapp.Entities.BattleOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BattleOptionRepository extends JpaRepository<BattleOption, Long> {
    @Modifying
    @Query(value = "DELETE FROM BATTLE_OPTIONS WHERE BATTLE_OPTIONS.ID IN " +
            "(SELECT JOIN_TABLE.BATTLE_OPTION_ID FROM TOURNAMENTS_TO_BATTLE_OPTIONS JOIN_TABLE " +
            "WHERE JOIN_TABLE.TOURNAMENT_ID = :tournamentId)", nativeQuery = true)
    void deleteTournamentBattleOptions(Long tournamentId);
}
