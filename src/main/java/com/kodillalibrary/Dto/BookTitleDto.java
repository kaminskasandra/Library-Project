package com.kodillalibrary.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTitleDto {
    private String title;
    private String author;
    private Integer publicationYear;
}
