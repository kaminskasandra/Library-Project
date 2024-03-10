package com.kodillalibrary.repository;

import com.kodillalibrary.domain.Book;
import com.kodillalibrary.domain.BookTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    List<Book> findAll();

    List<Book> findAllByBookTitle(BookTitle bookTitle);
}
