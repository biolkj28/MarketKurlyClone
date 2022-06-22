package com.nineteam.marketkurlycloneproject;

import com.nineteam.marketkurlycloneproject.domain.model.Category;
import com.nineteam.marketkurlycloneproject.domain.model.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static com.nineteam.marketkurlycloneproject.domain.model.QCategory.category;

@Component
@AllArgsConstructor
public class DBTestRunner implements ApplicationRunner {

//    private final MarketKurlyCrawling crawling;
//    private final CategoryRepository repository;

    private final EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //DB 테스트 데이터 로딩
        //crawling.storedMarketData();


    }

}
