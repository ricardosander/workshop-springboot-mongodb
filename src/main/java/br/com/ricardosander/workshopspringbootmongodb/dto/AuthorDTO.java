package br.com.ricardosander.workshopspringbootmongodb.dto;

import br.com.ricardosander.workshopspringbootmongodb.domain.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {

  private final String id;

  private final String name;

  public AuthorDTO(User user) {
    id = user.getId();
    name = user.getName();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
