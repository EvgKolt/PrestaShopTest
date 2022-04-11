package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import entities.Product;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {
    public static ThreadLocal<Map<Product, Integer>> productsInCart = ThreadLocal.withInitial(HashMap::new);

    public void verifyProductsInCart() {
        ElementsCollection hrefs = $$x("//div[contains(@class, 'product-line-info')]/a");
        Map<Product, Integer> productsInCartUI = new HashMap<>();
        for (SelenideElement href : hrefs) {
            String productHref = href.getAttribute("href");
            List<Product> productList = productsInCart.get().keySet().stream()
                    .filter(product -> product.getHref().equals(productHref)).toList();
            Integer productId = productList.get(0).getProductId();
            Integer productCount = Integer.valueOf(Objects.requireNonNull($x("//input[@data-product-id = '" + productId + "']").getAttribute("value")));
            productsInCartUI.put(new Product(productId, productHref), productCount);
        }
        Assertions.assertEquals(productsInCartUI, productsInCart.get());
        Assertions.assertEquals(String.valueOf(CartPage.productsInCart.get().values().stream().mapToInt(Integer::intValue).sum()),
                (StringUtils.substringBetween($x("//span[contains(@class, 'label js-subtotal')]").getText(), "", " items")));
    }
}
