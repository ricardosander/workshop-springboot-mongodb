package br.com.ricardosander.workshopspringbootmongodb.dto;

import br.com.ricardosander.workshopspringbootmongodb.domain.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {

  private String id;

  private String name;

  public AuthorDTO() {
  }

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
