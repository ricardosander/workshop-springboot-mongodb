package br.com.ricardosander.workshopspringbootmongodb.config;

import br.com.ricardosander.workshopspringbootmongodb.domain.Post;
import br.com.ricardosander.workshopspringbootmongodb.domain.User;
import br.com.ricardosander.workshopspringbootmongodb.repository.PostRepository;
import br.com.ricardosander.workshopspringbootmongodb.repository.UserRepository;
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

    userRepository.deleteAll();
    postRepository.deleteAll();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    Post post1 = new Post(null, dateFormat.parse("21/03/2018"), "Partiu Viagem",
        "Vou viajar para São Paulo. Abraços.", maria);
    Post post2 = new Post(null, dateFormat.parse("23/03/2018"), "Bom dia",
        "Acordei feliz hoje!", maria);

    userRepository.saveAll(Arrays.asList(maria, alex, bob));
    postRepository.saveAll(Arrays.asList(post1, post2));
  }

}
