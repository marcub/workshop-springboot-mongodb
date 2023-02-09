package com.marcuscezar.workshopmongodb.config;

import com.marcuscezar.workshopmongodb.domain.Post;
import com.marcuscezar.workshopmongodb.domain.User;
import com.marcuscezar.workshopmongodb.dto.AuthorDTO;
import com.marcuscezar.workshopmongodb.dto.CommentDTO;
import com.marcuscezar.workshopmongodb.repository.PostRepository;
import com.marcuscezar.workshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();


        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, format.parse("12/01/2022"), "Partiu trilha!", "Trilha muito interessante em Maceió", new AuthorDTO(maria));
        Post post2 = new Post(null, format.parse("02/06/2021"), "São João chegando!", "Gosto demais dessa época", new AuthorDTO(maria));

        CommentDTO comment1 = new CommentDTO("Top demais a trilha!", format.parse("21/03/2022"), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveitou?!", format.parse("22/03/2022"), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Muito boa mesmo!", format.parse("02/06/2021"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().add(comment3);

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(maria);
    }
}
