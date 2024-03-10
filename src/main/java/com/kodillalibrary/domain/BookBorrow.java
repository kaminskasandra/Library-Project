package com.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BORROWS")
public class BookBorrow {

    @Id
    @GeneratedValue
    @Column(name = "borrow_id", unique=true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "date_of_borrow")
    private LocalDate dateOfBorrow;

    @Column(name = "date_of_return")
    private LocalDate dateOfBookReturn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public BookBorrow(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.dateOfBorrow = LocalDate.now();
        this.dateOfBookReturn = LocalDate.now().plusDays(30);
    }
}