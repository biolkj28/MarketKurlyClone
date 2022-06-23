//package com.nineteam.marketkurlycloneproject;
//
//import com.nineteam.marketkurlycloneproject.domain.model.Category;
//import com.nineteam.marketkurlycloneproject.domain.model.Products;
//import com.nineteam.marketkurlycloneproject.domain.model.QCategory;
//import com.nineteam.marketkurlycloneproject.domain.model.QProducts;
//import com.nineteam.marketkurlycloneproject.web.dto.MainForProductResponseDto;
//import com.nineteam.marketkurlycloneproject.web.dto.QMainForProductResponseDto;
//import com.querydsl.core.types.dsl.NumberExpression;
//import com.querydsl.jpa.impl.JPAQuery;
//import org.aspectj.lang.annotation.Before;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.system.OutputCaptureExtension;
//import org.springframework.test.context.ActiveProfiles;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import java.util.List;
//
//import static com.nineteam.marketkurlycloneproject.domain.model.QCategory.category;
//import static com.nineteam.marketkurlycloneproject.domain.model.QProducts.products;
//
////@DataJpaTest
//@SpringBootTest
//@ExtendWith(OutputCaptureExtension.class)
//public class ProductsSelectTest {
//
////    @Autowired
////    TestEntityManager testEntityManager;
//
//    @Autowired
//    EntityManager em;
//
////    @BeforeEach
////    void init() {
////        em = testEntityManager.getEntityManager();
////    }
//
//    @Test
//    @DisplayName("카테고리 조회")
//    @Transactional
//    void 카테고리조회() {
//
//
//        JPAQuery<Category> query = new JPAQuery<>(em);
//        QCategory qCategory = new QCategory("a");
//        QCategory qCategory1 = new QCategory("b");
//        List<Category> category = query
//                .from(qCategory)
//                .where(qCategory.topCategory.isNull())
//                .innerJoin(qCategory.subCategory)
//                .fetchJoin()
//                .distinct()
//                .fetch();
//
//
//        Assertions.assertThat(category.size()).isEqualTo(4);
//        for (Category category1 : category) {
//            if (category1.getSubCategory().size() == 5) {
//                Assertions.assertThat(category1.getSubCategory().size()).isEqualTo(5);
//            }
//
//        }
//    }
//
//    @Test
//    @DisplayName("메인 페이지 상품 조회")
//    @Transactional
//    void 메인페이지상품조회() {
//        // 각 카테고리별 1개
//
//        JPAQuery<Products> query = new JPAQuery<>(em);
//
//        List<MainForProductResponseDto> products = query
//                .select(new QMainForProductResponseDto(
//                        QProducts.products.id,
//                        QProducts.products.image,
//                        QProducts.products.title,
//                        QProducts.products.lprice
//                ))
//                .from(QProducts.products)
//                .orderBy(NumberExpression.random().asc())
//                .limit(20)
//                .fetch();
//
//        Assertions.assertThat(products.size()).isEqualTo(20);
//    }
//
//
//};
