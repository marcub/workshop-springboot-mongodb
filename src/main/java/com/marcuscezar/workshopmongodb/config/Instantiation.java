package com.marcuscezar.workshopmongodb.config;

import com.marcuscezar.workshopmongodb.domain.Post;
import com.marcuscezar.workshopmongodb.domain.User;
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

        Post post1 = new Post(null, format.parse("12/01/2022"), "Partiu trilha!", "Trilha muito interessante em Maceió", maria);
        Post post2 = new Post(null, format.parse("02/06/2021"), "São João chegando!", "Gosto demais dessa época", maria);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1,post2));

    }
}
