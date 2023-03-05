package com.optionbattleapp.DTOs;

import com.optionbattleapp.Entities.BattleOption;
import lombok.Data;

import java.util.List;

@Data
public class TournamentDTO {
    private long user_id;
    private String name;
    private List<BattleOption> battleOptions;
}
