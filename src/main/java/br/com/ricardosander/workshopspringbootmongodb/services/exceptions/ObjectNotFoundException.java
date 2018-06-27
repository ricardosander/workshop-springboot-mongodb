package br.com.ricardosander.workshopspringbootmongodb.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

  public ObjectNotFoundException(String message) {
    super(message);
  }

}
