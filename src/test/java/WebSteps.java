import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchRepo(String repo) {
        $("[aria-label='Search or jump to…']").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Переходим по ссылке репозитория")
    public void clickOnRepoLink() {
        $("[href='/qa-guru/qa_guru_14_10']").click();
    }

    @Step("Проверяем, что таб Issues имеет название Issues")
    public void tabShouldBeNamedIssues() {
        $("[data-content='Issues']").shouldHave(exactText("Issues"));
    }
}