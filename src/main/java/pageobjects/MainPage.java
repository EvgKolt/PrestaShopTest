package pageobjects;


import com.codeborne.selenide.Selenide;
import entities.Product;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public void open() {
        Selenide.open("/");
    }

    public void openCart() {
        $x("//div[@id = '_desktop_cart']").shouldBe(exist, visible).click();
    }

    public void addToCartProductById(int id) {
        String productHref = $x("//*[@data-id-product = '" + id + "']/descendant::a[last()]").getAttribute("href");
        $x("//*[@data-id-product = '" + id + "']").shouldBe(exist, visible).click();
        $x("//button[contains(@class,'add-to-cart')]").shouldBe(exist, visible).click();
        $x("//*[contains(text(), 'Product successfully added to your shopping cart')]").shouldBe(visible, exist);
        $x("//button[text()='Continue shopping']").shouldBe(exist, visible).click();
        $x("//img[contains(@alt, 'PrestaShop')]").shouldBe(exist, visible).click();
        CartPage.productsInCart.get().merge(new Product(id, productHref), 1, Integer::sum);
    }

    public void verifyNumberOfProductsOnCartImg() {
        String expectedNumberOfProductsInCart = String.valueOf(CartPage.productsInCart.get().values().stream().mapToInt(Integer::intValue).sum());
        String realNumberOfProductsInCart = $x("//span[contains(@class, 'cart-products-count')]").getText().replaceAll("^.|.$", "");
        Assertions.assertEquals(expectedNumberOfProductsInCart, realNumberOfProductsInCart);
    }


}
