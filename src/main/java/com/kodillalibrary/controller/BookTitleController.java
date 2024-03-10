package com.kodillalibrary.controller;

import com.kodillalibrary.Dto.BookTitleDto;
import com.kodillalibrary.domain.BookTitle;
import com.kodillalibrary.service.BookTitleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/book-title")
@CrossOrigin("*")
public class BookTitleController {

    private final BookTitleService bookTitleService;
    private final ModelMapper modelMapper;

    @PostMapping
    public BookTitle addTitle(@RequestBody BookTitleDto bookTitleDto) {
        return bookTitleService.addTitle(modelMapper.map(bookTitleDto, BookTitle.class));
    }

    @DeleteMapping
    public void deleteBookTitle(@RequestParam Long id) {
        bookTitleService.deleteById(id);
    }

    @GetMapping
    public List<BookTitleDto> findAll() {
        List<BookTitle> titles = bookTitleService.findAll();

        return titles.stream()
                .map(title -> modelMapper.map(title, BookTitleDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookTitleDto findById(@PathVariable Long id) {
        Optional<BookTitle> bookTitleOptional = bookTitleService.findById(id);

        if (bookTitleOptional.isPresent()) {
            BookTitle bookTitle = bookTitleOptional.get();
            return modelMapper.map(bookTitle, BookTitleDto.class);
        } else {
            throw new ErrorHandler("Book title not found with id: " + id);
        }
    }
}

