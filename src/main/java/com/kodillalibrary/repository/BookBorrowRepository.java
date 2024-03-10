package com.kodillalibrary.repository;

import com.kodillalibrary.domain.BookBorrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookBorrowRepository extends CrudRepository<BookBorrow, Long> {
    @Override
    List<BookBorrow> findAll();
}
