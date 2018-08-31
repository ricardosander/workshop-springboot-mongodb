package br.com.ricardosander.workshopspringbootmongodb.services;

import br.com.ricardosander.workshopspringbootmongodb.domain.Post;
import br.com.ricardosander.workshopspringbootmongodb.repository.PostRepository;
import br.com.ricardosander.workshopspringbootmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Post findById(String id) {
    Optional<Post> post = postRepository.findById(id);
    return post.orElseThrow(() -> new ObjectNotFoundException("Post not found"));
  }

}
