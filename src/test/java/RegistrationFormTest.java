import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class RegistrationFormTest {

    /*
    Прекондишен: открыть браузер

    1. Открыть страницу https://sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
    2. Ввести в поле First Name значение Veranika
    3. Ввести в поле Last Name значение Slesarchyk
    4. Ввести в поле Email значение test@gmail.com
    5. Ввести в поле Password значение 12345
    6. Ввести в поле Confirm Password значение 12345
    7. Нажать кнопку Register
    Ожидаемый результат: Мы оказались на странице со сгенерированным логином и паролем

    Посткондишен: закрыть браузер
     */

    @Test
    public void checkSuccessfulSignUpWithAllFieldsFilling() {
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browser.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        browser.findElement(By.name("first_name")).sendKeys("Veranika");
        browser.findElement(By.name("last_name")).sendKeys("Slesarchyk");
        browser.findElement(By.name("email")).sendKeys("test@gmail.com");
        browser.findElement(By.name("password1")).sendKeys("12345");
        browser.findElement(By.name("password2")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Register]")).click();
        String actualValue = browser.findElement(By.cssSelector("[class=confirmation_message]")).getText();

        Assert.assertEquals(actualValue, "Account is created!");

        browser.close();
    }

     /*
    Прекондишен: открыть браузер

    1. Открыть страницу https://sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
    2. Оставить поле First Name незаполненным
    3. Ввести в поле Last Name значение Slesarchyk
    4. Ввести в поле Email значение test@gmail.com
    5. Ввести в поле Password значение 12345
    6. Ввести в поле Confirm Password значение 12345
    7. Нажать кнопку Register
    Ожидаемый результат: Выводится сообщение об ошибке "Oops, error on page. Some of your fields have invalid data or email was previously used"

    Посткондишен: закрыть браузер
     */

    @Test
    public void checkSignUpWithEmptyFirstName() {
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        browser.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        browser.findElement(By.name("last_name")).sendKeys("Slesarchyk");
        browser.findElement(By.name("email")).sendKeys("test@gmail.com");
        browser.findElement(By.name("password1")).sendKeys("12345");
        browser.findElement(By.name("password2")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Register]")).click();

        String actualValue = browser.findElement(By.cssSelector("[class=error_message]")).getText();

        Assert.assertEquals(actualValue, "Oops, error on page. Some of your fields have invalid data or email was previously used");

        browser.close();
    }
}
