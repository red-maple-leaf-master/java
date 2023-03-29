package top.oneyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oneyi.pojo.Book;


public interface BookRepository extends JpaRepository<Book,Long> {
}
