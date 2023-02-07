package com.marcuscezar.workshopmongodb.services;

import com.marcuscezar.workshopmongodb.domain.User;
import com.marcuscezar.workshopmongodb.dto.UserDTO;
import com.marcuscezar.workshopmongodb.repository.UserRepository;
import com.marcuscezar.workshopmongodb.services.exception.ObjectNotFoundException;
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

    public User findById(String id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User obj) {
        User newObj = repository.findById(obj.getId()).orElse(null);
        if (newObj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        updateData(obj, newObj);

        return repository.save(newObj);
    }

    public User fromDTO(UserDTO obj) {
        return new User(obj.getId(), obj.getName(), obj.getEmail());
    }

    public void updateData(User obj, User newObj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }




}
