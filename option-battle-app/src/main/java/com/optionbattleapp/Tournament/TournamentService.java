package com.optionbattleapp.Tournament;

import com.optionbattleapp.DTOs.TournamentDTO;
import com.optionbattleapp.Entities.User;
import com.optionbattleapp.Entities.Tournament;
import com.optionbattleapp.User.UserRepository;
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
    private final TournamentRepository tournamentRepository;
    private final BattleOptionRepository battleOptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TournamentService(ModelMapper modelMapper,
                             TournamentRepository tournamentRepository,
                             BattleOptionRepository battleOptionRepository,
                             UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.tournamentRepository = tournamentRepository;
        this.battleOptionRepository = battleOptionRepository;
        this.userRepository = userRepository;
    }

    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    public Tournament createTournament(TournamentDTO tournamentDTO) {
        Tournament newTournament = modelMapper.map(tournamentDTO, Tournament.class);
        Timestamp currentTime = Timestamp.from(new Date().toInstant());
        newTournament.setCreatedOn(currentTime);
        newTournament.setUpdatedOn(currentTime);
        newTournament.setUser(getUserById(tournamentDTO.getUser_id()));
        return tournamentRepository.save(newTournament);
    }

    public Tournament updateTournament(Long tournamentId, TournamentDTO updatedTournamentDTO) {
        Tournament updatedTournament = modelMapper.map(updatedTournamentDTO, Tournament.class);
        Tournament existingTournament = getTournamentById(tournamentId);

        updatedTournament.setId(tournamentId);
        updatedTournament.setUpdatedOn(Timestamp.from(new Date().toInstant()));
        updatedTournament.setUser(getUserById(updatedTournamentDTO.getUser_id()));
        updatedTournament.setCreatedOn(existingTournament.getCreatedOn());

        return tournamentRepository.save(updatedTournament);
    }

    public Tournament getTournamentById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public void deleteTournament(Long tournamentId) {
        battleOptionRepository.deleteTournamentBattleOptions(tournamentId);
        tournamentRepository.deleteById(tournamentId);
    }
}
