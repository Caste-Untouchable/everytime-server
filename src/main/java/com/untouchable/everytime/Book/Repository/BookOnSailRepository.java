package com.untouchable.everytime.Book.Repository;

import com.untouchable.everytime.Book.Entity.BookOnSail;
import com.untouchable.everytime.School.Entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookOnSailRepository extends JpaRepository<BookOnSail, Long> {
    @Query("select b from BookOnSail b where b.school = ?1 order by b.createdAT DESC")
    List<BookOnSail> findBySchoolOrderByCreatedATDesc(School school);

    @Query("""
            select b from BookOnSail b
            where b.school = ?1 and (b.book.isbn like concat('%', ?2, '%') or b.book.title like concat('%', ?3, '%'))""")
    List<BookOnSail> findBySchoolAndBook_IsbnContainsOrBook_TitleContains(School school, String isbn, String title);



}
