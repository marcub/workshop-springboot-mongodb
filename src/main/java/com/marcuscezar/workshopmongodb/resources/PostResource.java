package com.marcuscezar.workshopmongodb.resources;

import com.marcuscezar.workshopmongodb.domain.Post;
import com.marcuscezar.workshopmongodb.resources.util.URL;
import com.marcuscezar.workshopmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @RequestMapping(method=RequestMethod.GET, value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitleContaining(@RequestParam(value="text", defaultValue="") String text) {
        String param = URL.decodeParam(text);
        List<Post> postList = service.findByTitleContaining(param);
        return ResponseEntity.ok().body(postList);
    }

    @RequestMapping(method=RequestMethod.GET, value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value="text", defaultValue="") String text, @RequestParam(value="minDate", defaultValue="") String minDate, @RequestParam(value="maxDate", defaultValue="") String maxDate) {
        String param = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> postList = service.fullSearch(param, min, max);
        return ResponseEntity.ok().body(postList);
    }
}
