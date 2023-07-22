package com.rogeriogregorio.bookmanagementsystem.repositories;

import com.rogeriogregorio.bookmanagementsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
