package com.kodillalibrary.Dto;

import com.kodillalibrary.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowDto {
    private ReaderDto reader;
    private LocalDate dateOfBorrow;
    private LocalDate dateOfBookReturn;
    private BookDto book;
}
