package com.kodillalibrary.service;

import com.kodillalibrary.domain.*;
import com.kodillalibrary.repository.BookTitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookTitleService {

    private final BookTitleRepository bookTitleRepository;

    public BookTitle addTitle(BookTitle title) {
        return bookTitleRepository.save(title);
    }

    public void deleteById(Long id) {
        bookTitleRepository.deleteById(id);
    }

    public List<BookTitle> findAll() {
        List<BookTitle> titles = bookTitleRepository.findAll();
        new ArrayList<>(titles);
        return titles;
    }

    public Optional<BookTitle> findById(Long id) {
        return bookTitleRepository.findById(id);
    }

    public Optional<BookTitle> findByTitle(String bookTitle) {
        return bookTitleRepository.findByTitle(bookTitle);
    }


}
