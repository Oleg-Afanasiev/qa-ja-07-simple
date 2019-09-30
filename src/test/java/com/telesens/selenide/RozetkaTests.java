package com.telesens.selenide;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byPartialLinkText;
import static com.codeborne.selenide.Selenide.*;

public class RozetkaTests {

    private String menuNBItemCss = "body > app-root > div > div:nth-child(2) > app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(1) > a";
    private String subMenuMonitorCss = "body > app-root > div > div:nth-child(2) > app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(1) > a";
    private String priceCss = "div[name='price']>div.g-price-uah";
    private String popPupCss = "#sort_view > div.sort-popup.popup-dropdown";

    @BeforeClass
    public void setUp() {
        baseUrl = "https://rozetka.com.ua/";
        timeout = 15000;
        startMaximized = true;
    }

    @Test
//    @Ignore
    public void testSortMonitor() {
        open("https://rozetka.com.ua/");
        $(byPartialLinkText("Ноутбуки")).hover();
        $(byPartialLinkText("Мониторы")).click();
        List<String> prices = $$(byCssSelector(priceCss)).texts();
        System.out.println("Prices: " + prices);
        $(byPartialLinkText("по рейтингу")).click();
        $(byPartialLinkText("от дешевых к дороги")).click();
        $(byCssSelector(popPupCss)).should(cssValue("visibility", "hidden"));

        List<String> sortedPrices = $$(byCssSelector(priceCss)).texts();
        System.out.println("Sorted Prices: " + sortedPrices);
    }
}
