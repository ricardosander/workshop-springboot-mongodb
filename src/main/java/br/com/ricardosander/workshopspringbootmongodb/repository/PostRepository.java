package br.com.ricardosander.workshopspringbootmongodb.repository;

import br.com.ricardosander.workshopspringbootmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
