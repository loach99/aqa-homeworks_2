import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    public void Test1() {

        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOur = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateOur);
        $("[data-test-id=name] [type=text]").setValue("Павел Павлов");
        $("[data-test-id=phone] [type=tel]").setValue("+79182134123");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateOur));
    }
}