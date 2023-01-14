package com.optionbattleapp.Tournament;

import com.optionbattleapp.DTOs.TournamentDTO;
import com.optionbattleapp.Entities.Author;
import com.optionbattleapp.Entities.Tournament;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TournamentService {

    private final ModelMapper modelMapper;
    private TournamentRepository tournamentRepository;
    private BattleOptionRepository battleOptionRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public TournamentService(ModelMapper modelMapper,
                             TournamentRepository tournamentRepository,
                             BattleOptionRepository battleOptionRepository,
                             AuthorRepository authorRepository) {
        this.modelMapper = modelMapper;
        this.tournamentRepository = tournamentRepository;
        this.battleOptionRepository = battleOptionRepository;
        this.authorRepository = authorRepository;
    }

    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    public Tournament createTournament(TournamentDTO tournamentDTO) {
        Tournament newTournament = modelMapper.map(tournamentDTO, Tournament.class);
        Timestamp currentTime = Timestamp.from(new Date().toInstant());
        newTournament.setCreatedOn(currentTime);
        newTournament.setUpdatedOn(currentTime);
        newTournament.setAuthor(getAuthorById(tournamentDTO.getAuthor_id()));
        return tournamentRepository.save(newTournament);
    }

    public Tournament updateTournament(Long tournamentId, TournamentDTO updatedTournamentDTO) {
        Tournament updatedTournament = modelMapper.map(updatedTournamentDTO, Tournament.class);
        Tournament existingTournament = getTournamentById(tournamentId);

        updatedTournament.setId(tournamentId);
        updatedTournament.setUpdatedOn(Timestamp.from(new Date().toInstant()));
        updatedTournament.setAuthor(getAuthorById(updatedTournamentDTO.getAuthor_id()));
        updatedTournament.setCreatedOn(existingTournament.getCreatedOn());

        return tournamentRepository.save(updatedTournament);
    }

    public Tournament getTournamentById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));
    }

    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
    }

    public void deleteTournament(Long tournamentId) {
        battleOptionRepository.deleteTournamentBattleOptions(tournamentId);
        tournamentRepository.deleteById(tournamentId);
    }
}
