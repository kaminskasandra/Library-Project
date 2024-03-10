package com.kodillalibrary.Dto;

import com.kodillalibrary.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private BookStatus status;
    private String bookTitle;
}
