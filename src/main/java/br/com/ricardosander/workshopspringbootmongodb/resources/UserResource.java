package br.com.ricardosander.workshopspringbootmongodb.resources;

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

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<UserDTO>> findAll() {

    List<User> users = userService.findAll();
    List<UserDTO> usersDTO =
        users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());

    return ResponseEntity.ok().body(usersDTO);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public UserDTO findById(@PathVariable String id) {
    return new UserDTO(userService.findById(id));
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

}
