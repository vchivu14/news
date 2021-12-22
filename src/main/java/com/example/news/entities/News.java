package com.example.news.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "author", columnDefinition = "varchar(90)")
    private String author;
    @Basic
    @Column(name = "title", columnDefinition = "longtext")
    private String title;
    @Basic
    @Column(name = "description", columnDefinition = "longtext")
    private String description;
    @Basic
    @Column(name = "url", columnDefinition = "longtext")
    private String url;
    @Basic
    @Column(name = "source", columnDefinition = "varchar(90)")
    private String source;
    @Basic
    @Column(name = "image", columnDefinition = "longtext")
    private String image;
    @Basic
    @Column(name = "category", columnDefinition = "varchar(45)")
    private String category;
    @Basic
    @Column(name = "language", columnDefinition = "varchar(45)")
    private String language;
    @Basic
    @Column(name = "country", columnDefinition = "varchar(45)")
    private String country;
    @Basic
    @CreationTimestamp
    @Column(name = "published", columnDefinition = "timestamp")
    private Timestamp published;

}
