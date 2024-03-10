package com.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id", unique=true)
    private Long bookId;

    @Column(name = "book_status")
    private BookStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TITLE_ID")
    private BookTitle bookTitle;

    @OneToMany(
            targetEntity = BookBorrow.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BookBorrow> borrows = new ArrayList<>();

    public Book(BookStatus status, BookTitle title, List<BookBorrow> borrows) {
        this.status = status;
        this.bookTitle = title;
        this.borrows = new ArrayList<>();
    }
}
