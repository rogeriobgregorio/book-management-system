package com.rogeriogregorio.bookmanagementsystem.repositories;

import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM tb_books WHERE tb_books.title LIKE :titleOrAuthor OR tb_books.author LIKE :titleOrAuthor")
    List<BookEntity> findByTitleOrAuthor(@Param("titleOrAuthor") String titleOrAuthor);
}
