package org.gin.extractor.model;

import javax.persistence.*;


@Entity
@Table(name = "extractor")
public class Contrast {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @Column
  private String contrast;
  @Column
  private String contype;
  @Column
  private String atlas;
  @Column
  private String file;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContrast() {
    return contrast;
  }

  public void setContrast(String contrast) {
    this.contrast = contrast;
  }
  
  public String getContype() {
	  return contype;
  }

  public void setContype(String contype) {
	  this.contype = contype;
  }
  
  public String getAtlas() {
	  return atlas;
  }

  public void setAtlas(String atlas) {
	  this.atlas = atlas;
  }

  public String getFile() {
	  return file;
  }

  public void setFile(String file) {
	  this.file = file;
  } 
}
