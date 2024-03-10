package com.kodillalibrary.Dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDto {
    private String name;
    private String lastName;
    private LocalDate dateOfAccountCreation;
}
