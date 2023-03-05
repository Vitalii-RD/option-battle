package com.optionbattleapp.Tournament;

import com.optionbattleapp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
