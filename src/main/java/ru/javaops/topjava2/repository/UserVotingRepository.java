package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.UserVoting;

@Transactional(readOnly = true)
public interface UserVotingRepository extends JpaRepository<UserVoting, Integer> {
}
