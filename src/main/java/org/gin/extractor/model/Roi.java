package org.gin.extractor.model;

import javax.persistence.*;


@Entity
@Table(name = "extractorroi")
public class Roi {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @Column
  private String roi;
  @Column
  private String atlas;
  @Column
  private Integer laterality;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRoi() {
    return roi;
  }

  public void setRoi(String roi) {
    this.roi = roi;
  }
  
  public String getAtlas() {
	  return atlas;
  }

  public void setAtlas(String atlas) {
	  this.atlas = atlas;
  }
  
  public Integer getLaterality() {
	  return laterality;
  }

  public void setLaterality(Integer laterality) {
	  this.laterality = laterality;
  }  
}
