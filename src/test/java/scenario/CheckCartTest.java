package scenario;

import com.codeborne.selenide.Selenide;
import common.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pageobjects.CartPage;
import pageobjects.CheckOutPage;
import pageobjects.MainPage;

@Epic("EpicName")
@Feature("FeatureName")
@Severity(SeverityLevel.NORMAL)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckCartTest extends BaseTest {
    @Test
    @Order(1)
    @Story("Реализовать функционал корзины для Интернет-магазина.")
    @Description("Добавить несколько позиций товара в корзину и оформить заказ из корзины как неизвестный пользователь")
    void passedTestAsUnknownUser() {
        MainPage mainPage = new MainPage();
        mainPage.open();
        Selenide.switchTo().frame("framelive");
        //если id продуктов на главной странице все время разные, можно получить их через elementscollection/get data-id-product-attribute и тд
        mainPage.addToCartProductById(1);
        mainPage.addToCartProductById(1);
        mainPage.addToCartProductById(3);
        mainPage.addToCartProductById(4);
        mainPage.verifyNumberOfProductsOnCartImg();
        mainPage.openCart();

        CartPage cartPage = new CartPage();
        cartPage.verifyProductsInCart();

        CheckOutPage checkOutPage = new CheckOutPage();
        checkOutPage.checkOutAsUnknownUser();
        //todo check email sending, check database
    }

    @Test
    @Order(2)
    @Story("Реализовать функционал корзины для Интернет-магазина.")
    @Description("Добавить несколько позиций товара в корзину и оформить заказ из корзины как известный пользователь")
    void passedTestAsUser() {
        //todo
    }

    @Test
    @Order(3)
    @Story("Реализовать функционал корзины для Интернет-магазина.")
    @Description("Добавить в корзину столько продуктов чтобы она сломалась(появилось предупреждение о лимите) за 2 вида пользователей")
    void failedTest() {
        //todo
    }

    @Test
    @Order(4)
    @Story("Реализовать функционал корзины для Интернет-магазина.")
    @Description("Добавить в корзину 1 продукт за 2 вида пользователей и провести удачные и не удачные кейсы")
    void regressTest() {
        //todo если данный тест кейс еще не реализован, то стоит его добавить для регресса
    }
}
