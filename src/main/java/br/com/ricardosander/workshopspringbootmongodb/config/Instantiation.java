package br.com.ricardosander.workshopspringbootmongodb.config;

import br.com.ricardosander.workshopspringbootmongodb.domain.Post;
import br.com.ricardosander.workshopspringbootmongodb.domain.User;
import br.com.ricardosander.workshopspringbootmongodb.dto.AuthorDTO;
import br.com.ricardosander.workshopspringbootmongodb.dto.CommentDTO;
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

    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    Post post1 = new Post(null, dateFormat.parse("21/03/2018"), "Partiu Viagem",
        "Vou viajar para São Paulo. Abraços.", new AuthorDTO(maria));
    Post post2 = new Post(null, dateFormat.parse("23/03/2018"), "Bom dia",
        "Acordei feliz hoje!", new AuthorDTO(maria));

    CommentDTO comment1 =
        new CommentDTO(new AuthorDTO(alex), "Boa viagem!!!", dateFormat.parse("22/03/2018"));
    CommentDTO comment2 =
        new CommentDTO(new AuthorDTO(bob), "Aproveita mano.", dateFormat.parse("12/04/2018"));
   post1.getComments().addAll(Arrays.asList(comment1, comment2));

    CommentDTO comment3 =
        new CommentDTO(new AuthorDTO(alex), "Fico feliz por ti.", dateFormat.parse("24/03/2018"));
    post2.getComments().add(comment3);

    postRepository.saveAll(Arrays.asList(post1, post2));

     maria.getPosts().addAll(Arrays.asList(post1, post2));
     userRepository.save(maria);
  }

}
