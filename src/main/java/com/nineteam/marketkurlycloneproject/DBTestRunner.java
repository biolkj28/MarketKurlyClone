package com.nineteam.marketkurlycloneproject;


import com.nineteam.marketkurlycloneproject.domain.repository.CategoryRepository;
import com.nineteam.marketkurlycloneproject.web.service.crawling.MarketKurlyCrawling;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DBTestRunner implements ApplicationRunner {

    private final MarketKurlyCrawling crawling;
    private final CategoryRepository repository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //DB 테스트 데이터 로딩
        crawling.storedMarketData();


    }

}
