package com.kodillalibrary.repository;

import com.kodillalibrary.domain.BookTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookTitleRepository extends CrudRepository<BookTitle, Long> {
    @Override
    List<BookTitle> findAll();

    Optional<BookTitle> findByTitle(String title);

}
