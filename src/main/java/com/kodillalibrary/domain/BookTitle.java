package com.kodillalibrary.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TITLES")

public class BookTitle {
    @Id
    @GeneratedValue
    @Column(name = "title_id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "publication_year")
    private Integer publicationYear;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "bookTitle", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
}