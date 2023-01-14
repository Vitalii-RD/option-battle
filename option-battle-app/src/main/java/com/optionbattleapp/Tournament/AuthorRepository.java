package com.optionbattleapp.Tournament;

import com.optionbattleapp.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
