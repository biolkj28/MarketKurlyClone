package com.nineteam.marketkurlycloneproject.web.service;

import com.nineteam.marketkurlycloneproject.domain.model.Category;
import com.nineteam.marketkurlycloneproject.domain.model.Products;
import com.nineteam.marketkurlycloneproject.domain.repository.CategoryRepository;
import com.nineteam.marketkurlycloneproject.domain.repository.ProductRepository;
import com.nineteam.marketkurlycloneproject.web.dto.ProductResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.TopCategoryMap;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
@RequiredArgsConstructor
public class MarketKurlyCrawling {

    private final NaverShoppingApi api;
    private final CategoryRepository repository;
    private static final String WEBDRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:/Users/biolk/Downloads/chromedriver.exe";
    private static final String BASIC = "마켓컬리 ";
    private final ProductRepository productRepository;

    public TopCategoryMap storedCategory() throws IOException {

        File input = new File("C:\\Users\\biolk\\Desktop\\마켓컬리 __ 내일의 장보기, 마켓컬리.html");
        Document doc = Jsoup.parse(input, "euc-kr");
        Elements elements = doc.select("#gnbMenu > div > div.gnb_sub > div > ul > li");
        HashMap<String, List<String>> categoryMap = new HashMap<>();
        HashMap<String, Long> topCategoryMap = new HashMap<>();
        // 카테고리 분류
        int cnt = 0;
        for (Element element : elements) {
            if (cnt == 4) break;

            String title = element.selectFirst("a > span.tit > span").text();
            String sub = element.getElementsByClass("name").text();

            System.out.println(title);

            String[] subCategory = sub.split("\\s|·");
            List<String> list = new LinkedList<>();
            if(title.equals("정육·계란")){
                list = (Arrays.asList("국내산 소고기","수입산 소고기","돼지고기", "계란류", "닭", "오리고기", "양념육", "돈까스", "양고기"));
                categoryMap.put(title, list);
                list.forEach(System.out::println);
            }else{
                list = new LinkedList<>(Arrays.asList(subCategory)).subList(0,5);
                if (list.contains("제철과일")) {

                    list.remove("제철과일");

                } else if (list.contains("제철수산")) {
                    list.remove("제철수산");

                }else if(list.contains("생선류")){
                    int idx = list.indexOf("생선류");
                    list.set(idx, "생선");
                }

                categoryMap.put(title, list);
            }


            Category category = Category.builder()
                    .name(title)
                    .topCategory(null)
                    .subCategory(null)
                    .build();

            Category save = repository.save(category);
            topCategoryMap.put(title, save.getId());

            List<Category> subs = new LinkedList<>();
            for (String s : list) {
                Category subCate = Category.builder()
                        .name(s)
                        .topCategory(save)
                        .subCategory(null)
                        .build();
                subs.add(subCate);
            }
            save.subUpdate(subs);
            repository.save(save);
            cnt++;
        }
        TopCategoryMap map = new TopCategoryMap();
        map.setCategoryMap(categoryMap);
        map.setTopCategoryMap(topCategoryMap);
        return map;
    }


    public void storedMarketData() throws IOException, InterruptedException {

        System.setProperty(WEBDRIVER_ID, WEB_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings", true);

        TopCategoryMap map = storedCategory();
        HashMap<String, List<String>> categoryMap = map.getCategoryMap();
        HashMap<String, Long> topCategoryIdMap = map.getTopCategoryMap();
        // 검색 용어로 분류
        int searchCnt = 0;

        for (String s : categoryMap.keySet()) {
            List<String> subs = categoryMap.get(s);
            String search = null;
            Category category = null;
            System.out.println(s);
            for (String sub : subs) {
                category = repository.findCategory(sub,topCategoryIdMap.get(s)).orElse(null);


                if (sub.equals("친환경")) {
                    if ("과일·견과·쌀".equals(s)) {
                        search = BASIC + sub + " 과일";
                    } else {
                        search = BASIC + sub + " " + s;
                    }
                } else if (sub.contains("제철")) {
                    continue;
                } else if (sub.contains("생선류")) {
                    search = BASIC + "생선";
                } else if (sub.contains("조개류")) {
                    search = BASIC + "조개";
                } else {
                    search = BASIC + sub;
                }
                searchCnt++;

                List<ProductResponseDto> productResponseDtoList = api.getShoppingData(search);

                for (ProductResponseDto itemDto : productResponseDtoList) {
                    WebDriver driver = null;
                    try {
                        String mall = itemDto.getMallName();
                        if (!mall.trim().equals("마켓컬리"))continue;

                        driver = new ChromeDriver(options);

                        System.out.println(itemDto.getLink());
                        driver.get(itemDto.getLink());
                        WebElement btn = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/a[2]"));
                        btn.click();

                        Thread.sleep(6000);
                        WebElement element = driver.findElement(By.cssSelector("#content > div.section_view > #sectionView > div"));
                        //driver.findElement(By.cssSelector("#content > div.section_view > #sectionView > div"));

                        String title = element.findElement(By.cssSelector("p.goods_name > strong")).getText();
                        if(productRepository.existsByTitle(title))continue;
                        String desc = element.findElement(By.cssSelector("p.goods_name > span.short_desc")).getText();
                        List<WebElement> list = element.findElements(By.cssSelector("#sectionView > div > div.goods_info > dl"));

                        String unitForDB = null;
                        String shipping = null;
                        String weight = null;
                        String origin = null;
                        String packing = null;

                        for (WebElement webElement : list) {
                            String units = webElement.getText();
                            String[] unit = units.split("\n");

                            switch (unit[0]) {
                                case "판매단위":
                                    unitForDB = unit[1].isEmpty() ? "" : unit[1];
                                    break;
                                case "배송구분":
                                    shipping = unit[1].isEmpty() ? "" : unit[1];
                                    break;

                                case "중량/용량":
                                    weight = unit[1].isEmpty() ? "" : unit[1];
                                    break;

                                case "원산지":
                                    origin = unit[1].isEmpty() ? "" : unit[1];
                                    break;

                                case "포장타입":
                                    packing = unit[1].isEmpty() ? "" : unit[1];
                                    break;

                            }


                        }
                        Products product = Products.builder()
                                .title(title)
                                .desc(desc)
                                .shipping(shipping)
                                .packing(packing)
                                .lprice(itemDto.getLprice())
                                .image(itemDto.getImage())
                                .unit(unitForDB)
                                .origin(origin)
                                .weight(weight)
                                .category(category)
                                .build();

                        productRepository.save(product);
                        System.out.println(product.toString());
                        driver.close();
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(driver!=null)driver.quit();
                    }
                }

            }

        }

    }
}
