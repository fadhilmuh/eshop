package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EshopApplicationTests {

    @Autowired
    EshopApplication application;

    @Test
    void contextLoads() {
    }

    @Test
    public void main() {
        EshopApplication.main(new String[] {});
    }
}