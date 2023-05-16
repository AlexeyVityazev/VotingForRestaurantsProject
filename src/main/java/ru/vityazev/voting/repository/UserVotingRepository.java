package ru.vityazev.voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.vityazev.voting.model.UserVoting;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserVotingRepository extends BaseRepository<UserVoting> {
    @Query("select u from UserVoting u where u.user.id = ?1 and u.today = ?2")
    Optional<UserVoting> findByUserAndDate(int userId, LocalDate date);
}
