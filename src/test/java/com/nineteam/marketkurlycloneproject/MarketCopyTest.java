package com.nineteam.marketkurlycloneproject;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MarketCopyTest {
    private static String MarketKurly_URL = "https://www.kurly.com/shop/goods/goods_list.php?category=907";
    private static String WEBDRIVER_ID = "webdriver.chrome.driver";
    private static String WEB_DRIVER_PATH = "C:/Users/biolk/Downloads/chromedriver.exe";
    private WebDriver driver;

    @Test
    @DisplayName("마켓 컬리 데이터 크롤링")
    void 복사() throws IOException, InterruptedException {

          //      File input = new File("C:\\Users\\biolk\\Desktop\\마켓컬리 __ 내일의 장보기, 마켓컬리.html");


//        Elements elements = doc.select("#gnbMenu > div > div.gnb_sub > div > ul > li");
//
//        // index 0: 상위 카테고리, 나머지 하위 카테고리
//        for (Element element : elements) {
//            element.select("a > span.tit > span");
//            System.out.println("------------------------------");
//        }


        System.setProperty(WEBDRIVER_ID, WEB_DRIVER_PATH);

        //webdriver 설정
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings", true);

        driver = new ChromeDriver(options);
        driver.get(MarketKurly_URL);

        //상위 카테고리
       WebElement menu = driver.findElement(By.cssSelector("#lnbMenu > div.inner_lnb > ul"));
        //js.executeScript(("arguments[0].click();"), menu);

        Thread.sleep(1000);
//        menu.get(4).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(menu.findElements(By.tagName("li")).get(3)).build().perform();
        Thread.sleep(1000);
        actions.click();
        Thread.sleep(1000);
        //*[@id="goodsList"]/div[2]/div/ul/li[1]/div/div/div/button/span
        WebElement products = driver.findElement(By.cssSelector("#goodsList > div.list_goods > div > ul"));
        System.out.println(products.getText());


        //        products.click();
//
//        WebElement element23 = driver.findElement(By.cssSelector("#sectionView"));
//        System.out.println(element23.getText());
//
//        driver.navigate().back();
//
//        WebElement page = driver.findElement(By.xpath("//*[@id=\"goodsList\"]/div[3]/div/a[2]"));
//        page.click();
//        WebElement product2 = driver.findElement(By.cssSelector("#goodsList > div.list_goods > div > ul > li:nth-child(1) > div > a > span.name"));
//        System.out.println(product2.getText());
//        products.click();
//        js.executeScript("arguments[0].click();", products);
//        WebElement product1 = driver.findElement(By.cssSelector("#sectionView > div > p.goods_name"));
//        System.out.println("ans: "+product1.getText());

//        WebElement product2 = driver.findElement(By.cssSelector("#sectionView > div > p.goods_price"));
//        System.out.println(product2);
//        List<WebElement> element = driver.findElements(By.cssSelector("#gnbMenu > div > div.gnb_sub > div > ul > li > a > span.tit > span"));
//        List<String> menus = new LinkedList<>();
//        int i=0;
//        for (WebElement webElement : element) {
//            if(i==13)break;
//            menus.add(webElement.getText());
//            i++;
//        }
//        menus.forEach(System.out::println);
//
//        //하위 카테고리
//
//        WebElement submenu = driver.findElement(By.xpath("//*[@id=\"gnbMenu\"]/div/div[2]/div/ul/li[1]"));
//        action.moveToElement(menu).moveToElement(submenu).build().perform();
//        List<String> subList = new LinkedList<>();
//
//       WebElement sub = driver.findElement(By.cssSelector("#gnbMenu > div > div.gnb_sub > div > ul > li:nth-child(1) > ul > li:nth-child(1) > a > span"));
//
//        sub.forEach(System.out::println);
//        //System.out.println(sub.getText());
        driver.quit();

    }

}
