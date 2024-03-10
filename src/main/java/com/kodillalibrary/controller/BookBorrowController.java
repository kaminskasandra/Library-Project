package com.kodillalibrary.controller;

import com.kodillalibrary.Dto.BookBorrowDto;
import com.kodillalibrary.domain.BookBorrow;
import com.kodillalibrary.service.BookBorrowService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/book-borrow")
public class BookBorrowController {

    private final BookBorrowService bookBorrowService;
    private final ModelMapper modelMapper;

    @DeleteMapping()
    public void deleteById(@RequestParam Long id) {
        bookBorrowService.deleteById(id);
    }

    @GetMapping()
    public List<BookBorrowDto> findAll() {
        List<BookBorrow> borrows = bookBorrowService.findAll();

        return borrows.stream()
                .map(borrow -> modelMapper.map(borrow, BookBorrowDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookBorrowDto findById(@PathVariable Long id) {
        Optional<BookBorrow> bookBorrowOptional = bookBorrowService.findById(id);

        if (bookBorrowOptional.isPresent()) {
            BookBorrow bookBorrow = bookBorrowOptional.get();
            return modelMapper.map(bookBorrow, BookBorrowDto.class);
        } else {
            throw new ErrorHandler("Book borrow not found with id: " + id);
        }
    }

    @PostMapping(value = "borrow")
    public void borrow(@RequestParam Long bookId, @RequestParam Long readerId) {
        bookBorrowService.borrow(bookId, readerId);
    }

    @PutMapping(value = "return")
    public void returnBook(@RequestParam Long id) {
        bookBorrowService.returnBook(id);
    }
}
