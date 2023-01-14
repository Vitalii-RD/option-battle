package com.optionbattleapp.Tournament;

import com.optionbattleapp.DTOs.TournamentDTO;
import com.optionbattleapp.Entities.Tournament;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService, ModelMapper modelMapper) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<Tournament> getTests() {
        return tournamentService.getAll();
    }

    @GetMapping(path = "/{tournamentId}")
    public Tournament getTest(@PathVariable Long tournamentId) {
        return tournamentService.getTournamentById(tournamentId);
    }

    @PostMapping
    @Transactional
    public Tournament createTournament(@RequestBody TournamentDTO tournamentDTO) {
        return tournamentService.createTournament(tournamentDTO);
    }

    @PutMapping("/{tournamentId}")
    @Transactional
    public Tournament updateTournament(@PathVariable Long tournamentId, @RequestBody TournamentDTO tournamentDTO) {
        return tournamentService.updateTournament(tournamentId, tournamentDTO);
    }

    @DeleteMapping(path = "/{tournamentId}")
    @Transactional
    public void deleteTournament(@PathVariable Long tournamentId) {
        tournamentService.deleteTournament(tournamentId);
    }
}
