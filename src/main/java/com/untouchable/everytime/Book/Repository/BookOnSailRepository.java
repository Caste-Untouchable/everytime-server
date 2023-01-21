package com.untouchable.everytime.Book.Repository;

import com.untouchable.everytime.Book.Entity.BookOnSail;
import com.untouchable.everytime.School.Entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookOnSailRepository extends JpaRepository<BookOnSail, Long> {
    @Query("select b from BookOnSail b where b.school = ?1")
    List<BookOnSail> findBySchool(School school);

    @Query("select b from BookOnSail b where b.book.title like ?1 and b.school = ?2")
    List<BookOnSail> findByBook_TitleLikeAndSchool(String title, School school);


}
