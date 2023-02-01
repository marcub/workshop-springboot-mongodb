package com.marcuscezar.workshopmongodb.resources;

import com.marcuscezar.workshopmongodb.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        List<User> list = new ArrayList<>();
        User marcus = new User("2004", "Marcus Cezar", "marcusferraz98@gmail.com");
        User luiza = new User("1250", "Luiza Marques", "luiza@gmail.com");
        list.addAll(Arrays.asList(marcus, luiza));
        return ResponseEntity.ok().body(list);
    }
}
