package com.cesar.projeto.sr2.aurum.service;

import org.springframework.stereotype.Service;

import com.cesar.projeto.sr2.aurum.model.UserModel;
import com.cesar.projeto.sr2.aurum.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String login, String password, String email){
        if(login == null || password == null){
            return null;
        }else{
            if(userRepository.findFirstByLogin(login).isPresent()){
                System.out.println("duplicated login");
                return null;
            }
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return userRepository.save(userModel);
        }
    }

    public UserModel authenticate(String login, String password){
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
