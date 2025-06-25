package com.imd.longinus.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "embedding_data")
public class Nr12PdfKnowledge {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "embedding", columnDefinition = "vector(1536)")
  @JdbcTypeCode(SqlTypes.VECTOR)
  @Array(length = 1536)
  private List<Double> embedding;

  @Column(name = "section_number", nullable = false)
  private String sectionNumber;

  @Column(name = "section_title", nullable = false)
  private String sectionTitle;

  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "page_number", nullable = false)
  private int pageNumber;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public List<Double> getEmbedding() {
    return embedding;
  }
  public void setEmbedding(List<Double> embedding) {
    this.embedding = embedding;
  }
  public String getSectionNumber() {
    return sectionNumber;
  }
  public void setSectionNumber(String sectionNumber) {
    this.sectionNumber = sectionNumber;
  }
  public String getSectionTitle() {
    return sectionTitle;
  }
  public void setSectionTitle(String sectionTitle) {
    this.sectionTitle = sectionTitle;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getPageNumber() {
    return pageNumber;
  }
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }


}
