package br.com.ricardosander.workshopspringbootmongodb.services;

import br.com.ricardosander.workshopspringbootmongodb.domain.User;
import br.com.ricardosander.workshopspringbootmongodb.dto.UserDTO;
import br.com.ricardosander.workshopspringbootmongodb.repository.UserRepository;
import br.com.ricardosander.workshopspringbootmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(String id) {

    Optional<User> user = userRepository.findById(id);

    return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
  }

  public User insert(UserDTO userDTO) {
    return userRepository.insert(User.from(userDTO));
  }

  public void deleteById(String id) {
    findById(id);
    userRepository.deleteById(id);
  }
}
