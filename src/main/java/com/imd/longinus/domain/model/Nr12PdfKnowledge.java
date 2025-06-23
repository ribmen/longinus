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
@Getter
@Setter
@Table(name = "embedding_data")
public class Nr12PdfKnowledge {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

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

}
