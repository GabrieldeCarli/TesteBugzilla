package br.edu.utfpr.exemplomaven;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class Bugzilla {

    /**
     * Vc precisa identificar onde estah o chromedriver. Baixar de:
     * https://sites.google.com/a/chromium.org/chromedriver/downloads
     *
     * Versão utilizada do chromedriver: 2.35.528139
     */
    private static String CHROMEDRIVER_LOCATION = "C:\\Users\\gabri\\Documents\\Selenium\\chromedriver.exe";

    private static int scId = 0;

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
    }

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void loginInvalido() {
        driver.get("https://landfill.bugzilla.org/bugzilla-5.0-branch/");
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"login_link_top\"]"));
        signUpButton.click();
        WebElement username = driver.findElement(By.xpath("//*[@id=\"Bugzilla_login_top\"]"));
        username.sendKeys("gabriel@gmail.com");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"Bugzilla_password_top\"]"));
        password.sendKeys("senha");
        WebElement signUpButton2 = driver.findElement(By.xpath("//*[@id=\"log_in_top\"]"));
        signUpButton2.click();
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"error_msg\"]"));
        assertEquals("The login or password you entered is not valid.", errorMsg.getText().trim());
    }
    @Test
        public void loginRealizado() {
        driver.get("https://landfill.bugzilla.org/bugzilla-5.0-branch/");
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"login_link_top\"]"));
        signUpButton.click();
        WebElement username = driver.findElement(By.xpath("//*[@id=\"Bugzilla_login_top\"]"));
        username.sendKeys("papu_nilo@hotmail.com");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"Bugzilla_password_top\"]"));
        password.sendKeys("22ams22");
        WebElement signUpButton2 = driver.findElement(By.xpath("//*[@id=\"log_in_top\"]"));
        signUpButton2.click();
        WebElement msg = driver.findElement(By.xpath("//*[@id=\"common_links\"]/ul/li[9]/a"));
        assertEquals("Log out", msg.getText().trim());
    }
    @Test
        public void realizarBusca() {
        driver.get("https://landfill.bugzilla.org/bugzilla-5.0-branch/");
        WebElement username = driver.findElement(By.xpath("//*[@id=\"quicksearch_main\"]"));
        username.sendKeys("security");
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"find\"]"));
        signUpButton.click();

        WebElement msg = driver.findElement(By.xpath("//*[@id=\"title\"]"));
        assertEquals("Bugzilla – Bug List", msg.getText().trim());
    }
    @Test
        public void realizarBusca24hrs() {
        driver.get("https://landfill.bugzilla.org/bugzilla-5.0-branch/");
        WebElement click1 = driver.findElement(By.xpath("//*[@id=\"common_queries\"]/ul/li[1]/a[1]"));
        click1.click();
        WebElement msg = driver.findElement(By.xpath("//*[@id=\"bugzilla-body\"]/ul/li[2]/strong"));
        assertEquals("Creation date:", msg.getText().trim());
    }

}
