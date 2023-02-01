package com.marcuscezar.workshopmongodb.services;

import com.marcuscezar.workshopmongodb.domain.User;
import com.marcuscezar.workshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }


}
