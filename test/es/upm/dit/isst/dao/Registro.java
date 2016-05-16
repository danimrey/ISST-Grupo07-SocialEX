package es.upm.dit.isst.dao;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class Registro {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://1-dot-isst-grupo07-socialex-1281.appspot.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAsdfasdf() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Menu")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys("ISSTGrupo07SocialEx");
    driver.findElement(By.id("next")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("MassMoney1996");
    driver.findElement(By.id("signIn")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("Bruce");
    driver.findElement(By.id("apellidos")).clear();
    driver.findElement(By.id("apellidos")).sendKeys("Wayne");
    driver.findElement(By.id("pais")).clear();
    driver.findElement(By.id("pais")).sendKeys("USA");
    driver.findElement(By.id("titularTarjeta")).clear();
    driver.findElement(By.id("titularTarjeta")).sendKeys("Bruce Wayne");
    driver.findElement(By.id("direccion")).clear();
    driver.findElement(By.id("direccion")).sendKeys("Mansi��n Wayne");
    driver.findElement(By.id("ciudad")).clear();
    driver.findElement(By.id("ciudad")).sendKeys("Gotham City");
    driver.findElement(By.id("provincia")).clear();
    driver.findElement(By.id("provincia")).sendKeys("Gotham");
    driver.findElement(By.id("codigoPostal")).clear();
    driver.findElement(By.id("codigoPostal")).sendKeys("36880");
    driver.findElement(By.id("paisTarjeta")).clear();
    driver.findElement(By.id("paisTarjeta")).sendKeys("USA");
    new Select(driver.findElement(By.id("divisaTarjeta"))).selectByVisibleText("D��lar estadounidense $ (USD)");
    driver.findElement(By.id("tarjeta")).clear();
    driver.findElement(By.id("tarjeta")).sendKeys("0000 0000 0000 0001");
    driver.findElement(By.id("caducidadTarjeta")).clear();
    driver.findElement(By.id("caducidadTarjeta")).sendKeys("01/2020");
    driver.findElement(By.id("codigoSecreto")).clear();
    driver.findElement(By.id("codigoSecreto")).sendKeys("123");
    driver.findElement(By.cssSelector("input.special")).click();
    driver.findElement(By.linkText("Menu")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
