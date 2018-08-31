package br.com.ricardosander.workshopspringbootmongodb.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {

  private AuthorDTO author;

  private String text;

  private Date date;

  public CommentDTO() {

  }

  public CommentDTO(AuthorDTO author, String text, Date date) {
    this.author = author;
    this.text = text;
    this.date = date;
  }

  public AuthorDTO getAuthor() {
    return author;
  }

  public void setAuthor(AuthorDTO author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
