package com.dev.moduledomain.repository;

import com.dev.moduledomain.entity.BlogPopularKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPopularKeywordRepository extends JpaRepository<BlogPopularKeyword, Long> {

    BlogPopularKeyword findByKeyword(String keyword);
}
