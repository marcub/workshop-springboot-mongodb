package com.marcuscezar.workshopmongodb.resources;

import com.marcuscezar.workshopmongodb.domain.Post;
import com.marcuscezar.workshopmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }
}
