import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class IssueTabTest {
    private static final String REPOSITORY = "qa_guru_14_10";

    @BeforeAll
    static void beforeTests() {
        Configuration.baseUrl = "https://github.com";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение Issues")
    @Owner("Прохорова Юлия")
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Проверка на чистом Selenide")
    void selenideIssueTest() {

        open(baseUrl);
        $("[aria-label='Search or jump to…']").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $("[href='/qa-guru/qa_guru_14_10']").click();
        $("[data-content='Issues']").shouldHave(exactText("Issues"));
    }

    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение Issues")
    @Owner("Прохорова Юлия")
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Проверка c лямбда шагами через step")
    void lambdaIssueTest() {
        step("Открываем главную страницу", () -> {
            open(baseUrl);
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $("[aria-label='Search or jump to…']").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Переходим по ссылке репозитория", () -> {
            $("[href='/qa-guru/qa_guru_14_10']").click();
        });
        step("Проверяем, что таб Issues имеет название Issues", () -> {
            $("[data-content='Issues']").shouldHave(exactText("Issues"));
        });
    }

    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение Issues")
    @Owner("Прохорова Юлия")
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Проверка с аннотацией @Step")
    void annotatedIssueTest() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchRepo(REPOSITORY);
        steps.clickOnRepoLink();
        steps.tabShouldBeNamedIssues();
    }
}
