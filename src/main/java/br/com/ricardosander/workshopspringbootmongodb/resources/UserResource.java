package br.com.ricardosander.workshopspringbootmongodb.resources;

import br.com.ricardosander.workshopspringbootmongodb.domain.Post;
import br.com.ricardosander.workshopspringbootmongodb.services.UserService;
import br.com.ricardosander.workshopspringbootmongodb.domain.User;
import br.com.ricardosander.workshopspringbootmongodb.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  private final UserService userService;

  @Autowired
  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<UserDTO>> findAll() {

    List<User> users = userService.findAll();
    List<UserDTO> usersDTO =
        users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());

    return ResponseEntity.ok().body(usersDTO);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    return ResponseEntity.ok().body(new UserDTO(userService.findById(id)));
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Object> insert(@RequestBody UserDTO userDTO) {
    User user = userService.insert(userDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(user.getId())
        .toUri();
    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Object> delete(@PathVariable String id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UserDTO updateUser) {
    User user = User.from(updateUser);
    user.setId(id);
    userService.update(user);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "{id}/posts", method = RequestMethod.GET)
  public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
    User user = userService.findById(id);
    return ResponseEntity.ok().body(user.getPosts());
  }

}
