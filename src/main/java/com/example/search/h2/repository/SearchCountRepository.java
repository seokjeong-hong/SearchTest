package com.example.search.h2.repository;

import com.example.search.h2.domain.SearchCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchCountRepository extends JpaRepository<SearchCount, Long> {
    SearchCount save(SearchCount searchCount);
    Optional<SearchCount> findByKeyword(String keyword);
    List<SearchCount> findTop10ByOrderByCountDesc();
}
