package com.cesar.projeto.sr2.aurum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cesar.projeto.sr2.aurum.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{

    Optional<UserModel> findByLoginAndPassword(String login, String password);
    Optional<UserModel> findFirstByLogin(String login);
    
}
