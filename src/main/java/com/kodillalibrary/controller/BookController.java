package com.kodillalibrary.controller;

import com.kodillalibrary.Dto.BookDto;
import com.kodillalibrary.domain.Book;
import com.kodillalibrary.domain.BookStatus;
import com.kodillalibrary.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/book")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @PostMapping()
    public BookDto addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PutMapping()
    public void changeStatus(@RequestParam Long bookId, @RequestParam BookStatus bookStatus) {
        bookService.changeStatus(bookId, bookStatus);
    }

    @DeleteMapping()
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteById(id);
    }

    @GetMapping(value = "{id}")
    public BookDto getBook(@PathVariable Long id) {
        return modelMapper.map(bookService.findById(id).orElseThrow(() -> new ErrorHandler("Book with given ID not found")), BookDto.class);
    }


    @GetMapping(value = "countBookCopies")
    public int countBookCopies(@RequestParam Long bookId) {
        return bookService.countBookCopies(bookId);
    }

    @GetMapping()
    public List<BookDto> findAll() {
        List<Book> books = bookService.findAll();

        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }
}



