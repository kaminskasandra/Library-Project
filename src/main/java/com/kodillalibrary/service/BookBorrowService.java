package com.kodillalibrary.service;

import com.kodillalibrary.controller.ErrorHandler;
import com.kodillalibrary.domain.Book;
import com.kodillalibrary.domain.BookBorrow;
import com.kodillalibrary.domain.BookStatus;
import com.kodillalibrary.domain.Reader;
import com.kodillalibrary.repository.BookBorrowRepository;
import com.kodillalibrary.repository.BookRepository;
import com.kodillalibrary.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookBorrowService {
    private final BookBorrowRepository bookBorrowRepository;
    private final BookService bookService;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public Optional<BookBorrow> findById(Long id) {
        return bookBorrowRepository.findById(id);
    }

    public List<BookBorrow> findAll() {
        List<BookBorrow> bookBorrows = bookBorrowRepository.findAll();
        return new ArrayList<>(bookBorrows);
    }

    public void deleteById(Long id) {
        bookBorrowRepository.deleteById(id);
    }


    public void borrow(Long bookId, Long readerId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new ErrorHandler("Book with given ID was not found"));
        if (book.getStatus().equals(BookStatus.BORROWED)) {
            throw new ErrorHandler("This book is not available");
        }
        Reader reader = readerRepository
                .findById(readerId)
                .orElseThrow(() -> new ErrorHandler("Reader with given ID was not found"));
        bookService.changeStatus(book.getBookId(), BookStatus.BORROWED);
        BookBorrow bookBorrow = new BookBorrow(reader, book);
        bookBorrowRepository.save(bookBorrow);
    }

    public void returnBook(long id) {
        bookBorrowRepository
                .findById(id)
                .map(bookBorrow -> {
                    bookBorrow.setDateOfBookReturn(LocalDate.now());
                    bookBorrow.getBook().setStatus(BookStatus.AVAILABLE);
                    return bookBorrow;
                })
                .ifPresent(bookBorrowRepository::save);
    }
}
