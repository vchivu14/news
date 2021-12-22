package com.example.news.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "sources")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(90)")
    private String name;

    public Source(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return Objects.equals(getName(), source.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
