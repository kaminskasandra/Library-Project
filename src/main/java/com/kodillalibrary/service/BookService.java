package com.kodillalibrary.service;

import com.kodillalibrary.Dto.BookDto;
import com.kodillalibrary.controller.ErrorHandler;
import com.kodillalibrary.domain.Book;
import com.kodillalibrary.domain.BookStatus;
import com.kodillalibrary.domain.BookTitle;
import com.kodillalibrary.repository.BookRepository;
import com.kodillalibrary.repository.BookTitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookTitleService bookTitleService;

    public BookDto addBook(BookDto bookDto) {
        Optional<BookTitle> bookTitle = bookTitleService.findByTitle(bookDto.getBookTitle());

        Book book = new Book();

        bookTitle.ifPresent(title -> {
            book.setBookTitle(bookTitle.get());
            book.setStatus(bookDto.getStatus());
            bookTitle.get().getBooks().add(book);
            bookTitleService.addTitle(bookTitle.get());
        });
        return bookDto;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public void changeStatus(Long bookId, BookStatus status) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setStatus(status);
            bookRepository.save(book);
        } else {
            throw new ErrorHandler("Book with given id not found");
        }
    }

    public int countBookCopies(Long id) {
        Optional<BookTitle> bookTitle = bookTitleService.findById(id);

        return bookTitle.map(title -> bookRepository
                .findAllByBookTitle(title).size()).orElse(0);
    }
}