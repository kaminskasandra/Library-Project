package com.kodillalibrary.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "READERS")
public class Reader {

    @Id
    @GeneratedValue
    @Column(name = "reader_id", unique=true)
    private Long readerId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "date_of_account_creation")
    private LocalDate dateOfAccountCreation = LocalDate.now();

    @OneToMany(
            targetEntity = BookBorrow.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BookBorrow> bookBorrows = new ArrayList<>();

    public void setDateOfAccountCreation(LocalDate localDate) {
        this.dateOfAccountCreation = LocalDate.now();
    }
}
