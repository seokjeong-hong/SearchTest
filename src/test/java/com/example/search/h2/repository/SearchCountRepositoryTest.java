package com.example.search.h2.repository;

import com.example.search.h2.domain.SearchCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class SearchCountRepositoryTest {

    @Autowired
    SearchCountRepository searchCountRepository;

    @Test
    void crud() {
        var test1 = SearchCount.builder()
                .keyword("곱창")
                .count(1L)
                .build();

        searchCountRepository.save(test1);

        var searchCount = searchCountRepository.findByKeyword(test1.getKeyword()).get();
        assertEquals(searchCount.getCount(), 1L);
    }

    @Test
    void update() {
        var test1 = SearchCount.builder()
                .keyword("곱창")
                .count(1L)
                .build();
        searchCountRepository.save(test1);

        var searchCount = searchCountRepository.findByKeyword("곱창").orElseThrow(RuntimeException::new);
        searchCount.setCount(searchCount.getCount() + 1L);

        searchCountRepository.save(searchCount);
        System.out.println(searchCount);
        assertEquals(searchCount.getCount(), 2L);
    }

    @Test
    void findTop10ByOrderByCountTest() {
        var test1 = SearchCount.builder().keyword("곱창").count(1L).build();
        var test2 = SearchCount.builder().keyword("곱창2").count(15L).build();
        var test3 = SearchCount.builder().keyword("곱창3").count(13L).build();
        var test4 = SearchCount.builder().keyword("곱창4").count(1222L).build();
        var test5 = SearchCount.builder().keyword("곱창5").count(11L).build();
        var test6 = SearchCount.builder().keyword("곱창6").count(1L).build();
        var test7 = SearchCount.builder().keyword("곱창7").count(15L).build();
        var test8 = SearchCount.builder().keyword("곱창8").count(1111L).build();
        var test9 = SearchCount.builder().keyword("곱창9").count(111L).build();
        var test10 = SearchCount.builder().keyword("곱창10").count(4441L).build();
        var test11 = SearchCount.builder().keyword("곱창11").count(13L).build();
        var test12 = SearchCount.builder().keyword("곱창12").count(19L).build();
        searchCountRepository.save(test1);
        searchCountRepository.save(test2);
        searchCountRepository.save(test3);
        searchCountRepository.save(test4);
        searchCountRepository.save(test5);
        searchCountRepository.save(test6);
        searchCountRepository.save(test7);
        searchCountRepository.save(test8);
        searchCountRepository.save(test9);
        searchCountRepository.save(test10);
        searchCountRepository.save(test11);
        searchCountRepository.save(test12);

        var top10 = searchCountRepository.findTop10ByOrderByCountDesc();
        assertEquals(top10.get(0).getKeyword(), "곱창10");
        assertEquals(top10.size(), 10);
    }
}