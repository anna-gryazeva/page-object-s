package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement amountImput = $("[data-test-id='amount'] input");
    private final SelenideElement fromImput = $("[data-test-id='from'] input");
    private final SelenideElement transferHead = $ (byText ("Пополнение карты"));
    private final SelenideElement errorMassage= $("[data-test-id='error-notification']");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }
    public DashboardPage makeValidTransfer (String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }
    public void makeTransfer (String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountImput.setValue(amountToTransfer);
        fromImput.setValue(cardInfo.getNumber());
        transferButton.click();
    }

    public void findErrorMassage (String expectedText) {
        errorMassage.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
