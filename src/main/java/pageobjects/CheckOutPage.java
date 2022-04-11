package pageobjects;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CheckOutPage {
    public void checkOutAsUnknownUser() {
        $x("//a[text() = 'Proceed to checkout']").shouldBe(exist, visible).click();
        $x("//input[@id = 'field-id_gender-1']").shouldBe(exist).click();
        $x("//input[@id = 'field-firstname']").shouldBe(exist, visible).sendKeys("TestUser");
        $x("//input[@id = 'field-lastname']").shouldBe(exist, visible).sendKeys("TestUser");
        $x("//input[@id = 'field-email']").shouldBe(exist, visible).sendKeys("TestUser@test.com");
        $x("//input[@name = 'customer_privacy']").shouldBe(exist).click();
        $x("//button[@name = 'continue']").shouldBe(exist).click();
        $x("//input[@id = 'field-address1']").shouldBe(exist).sendKeys("Test Address, my address");
        $x("//input[@id = 'field-postcode']").shouldBe(exist).sendKeys("11111");
        $x("//input[@id = 'field-city']").shouldBe(exist).sendKeys("TestCity");
        $x("//button[@name = 'confirm-addresses']").shouldBe(exist).click();
        $x("//button[@name = 'confirmDeliveryOption']").shouldBe(exist).click();
        $x("//input[@name = 'payment-option']").shouldBe(exist).click();
        $x("//input[@id = 'conditions_to_approve[terms-and-conditions]']").shouldBe(exist).click();
        $x("//*[contains(text(), 'Place order')]").shouldBe(exist).click();
        $x("//section[@id = 'content-hook_order_confirmation']").shouldBe(exist, visible);
    }
}
