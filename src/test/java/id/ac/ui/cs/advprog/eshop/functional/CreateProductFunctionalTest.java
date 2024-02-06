package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest(){
        baseUrl = String.format("%s:%d",testBaseUrl, serverPort);
    }

    @Test
    void buttonFromHometoProductList_isWorking (ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.className("btn")).click();

        String titleProductList = driver.findElement(By.tagName("h2")).getText();

        // cek apakah muncul html create-product nya
        assertEquals("Product List", titleProductList);
    }

    @Test
    void createNewProductButton_isWorking (ChromeDriver driver) throws Exception {
        driver.get(baseUrl+"/product/create");
        String titleCreateProduct = driver.findElement(By.tagName("h3")).getText();

        // cek apakah muncul html create-product nya
        assertEquals("Create New Product", titleCreateProduct);
    }

    @Test
    void createNewProduct_isWorking (ChromeDriver driver) throws Exception {
        String name = "Testing Product";
        Integer quantity = 100;

        driver.get(baseUrl+"/product/create");
        driver.findElement(By.id("nameInput")).sendKeys(name);
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(quantity));
        driver.findElement(By.className("btn")).click();

        
        String titleProductList = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Product List", titleProductList);
    }

    @Test
    void newlyCreatedProductisInList (ChromeDriver driver) throws Exception {
        String name = "Testing Product";
        Integer quantity = 100;

        driver.get(baseUrl+"/product/create");
        driver.findElement(By.id("nameInput")).sendKeys(name);
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(quantity));
        driver.findElement(By.className("btn")).click();
        Thread.sleep(2000);

        // Check if the product is in the list
        List<WebElement> tabelProduct = driver.findElements(By.tagName("td"));
        WebElement productNameElement = tabelProduct.getFirst();
        String productName = productNameElement.getText();
        assertEquals(name, productName);

        WebElement productQuantityElement = tabelProduct.get(1);
        String productQuantity = productQuantityElement.getText();
        assertEquals(String.valueOf(quantity), productQuantity);

        // Delete the product
        WebElement buttonDelete = driver.findElement(By.className("btn-danger"));
        buttonDelete.click();
    }

    @Test
    void createMultipleProduct_isWorking (ChromeDriver driver) throws Exception {
        String[] name = new String[100];
        Integer[] quantity = new Integer[100];

        for (int i = 0; i < name.length; i++) {
            name[i] = "Testing Product " + i;
            quantity[i] = (i+1)*100 % 256;

            driver.get(baseUrl+"/product/create");
            driver.findElement(By.id("nameInput")).sendKeys(name[i]);
            driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(quantity[i]));
            driver.findElement(By.className("btn")).click();
            
            String titleProductList = driver.findElement(By.tagName("h2")).getText();
            assertEquals("Product List", titleProductList);
        }
        
        for (int i = 0; i < name.length; i++) {
            // Check if the product is in the list
            List<WebElement> tabelProduct = driver.findElements(By.tagName("td"));
            WebElement productNameElement = tabelProduct.get(0);
            String productName = productNameElement.getText();
            assertEquals(name[i], productName);

            WebElement productQuantityElement = tabelProduct.get(1);
            String productQuantity = productQuantityElement.getText();
            assertEquals(String.valueOf(quantity[i]), productQuantity);

            // Delete current inspected product
            WebElement buttonDelete = driver.findElement(By.className("btn-danger"));
            buttonDelete.click();
        }
    }
    
}