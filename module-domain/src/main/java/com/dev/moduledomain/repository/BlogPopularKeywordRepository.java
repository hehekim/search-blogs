package com.dev.moduledomain.repository;

import com.dev.moduledomain.entity.BlogPopularKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface BlogPopularKeywordRepository extends JpaRepository<BlogPopularKeyword, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="3000")})
    @Query("select b from BlogPopularKeyword b where b.keyword = :keyword")
    Optional<BlogPopularKeyword> findByKeywordWithLock(@Param("keyword") String keyword);

    List<BlogPopularKeyword> findTop10ByOrderBySearchCountDesc();
}
