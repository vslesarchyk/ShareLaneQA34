import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ZipCodeTest {

    /*
    Прекондишен: открыть браузер

    1. Открыть страницу https://www.sharelane.com/cgi-bin/register.py
    2. Ввести в поле ZIP Code значение 12345
    3. Нажать кнопку Continue
    Ожидаемый результат: Мы оказались на странице с формой регистрации

    Посткондишен: закрыть браузер
     */

    @Test
    public void checkZipCodeFieldWithFiveDigits() {
        //инициализируем браузер как Chrome
        WebDriver browser = new ChromeDriver();
        //определяем время ожидания элемента
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //переходим на страницу https://www.sharelane.com/cgi-bin/register.py
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        //вводим значение 12345 в поле ZIP code
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        //нажимаем кнопку Continue
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        //создаем boolean переменную, которая определяет наличие кнопки Register на странице
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        browser.close();
    }

    /*
    Прекондишен: открыть браузер

    1. Открыть страницу https://www.sharelane.com/cgi-bin/register.py
    2. Ввести в поле ZIP Code значение 1234
    3. Нажать кнопку Continue
    Ожидаемый результат: Выводится сообщение об ошибке "Oops, error on page. ZIP code should have 5 digits"

    Посткондишен: закрыть браузер
    */

    @Test
    public void checkZipCodeFieldWithFourDigits() {
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("1234");
        browser.findElement(By.cssSelector("[value=Continue]")).click();
        String actualValue = browser.findElement(By.cssSelector("[class=error_message]")).getText();

        Assert.assertEquals(actualValue, "Oops, error on page. ZIP code should have 5 digits");

        browser.close();
    }
}

