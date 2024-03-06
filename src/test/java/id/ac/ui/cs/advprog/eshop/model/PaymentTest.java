package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentTest {
    private List<Product> products;
    private Order order;

    @BeforeEach
    void setUpOrder(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        this.order = new Order("13652556-012a0-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentByVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals("VOUCHER_CODE", paymentByVoucherCode.getMethod());
    }

    @Test
    void testCreatePaymentByBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");

        Payment paymentByBankTransfer = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData, this.order);

        assertEquals("BANK_TRANSFER", paymentByBankTransfer.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "WRONG_METHOD", paymentData, this.order);
        });
    }

    @Test
    void testSetToSuccessStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);
        paymentByVoucherCode.setStatus(PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(),paymentByVoucherCode.getStatus());
    }

    @Test
    void testSetToRejectedStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);
        paymentByVoucherCode.setStatus(PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeMoreThan16CharLong(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC56781");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeLessThan16CharLong(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeIsNotStartedWithESHOP(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeDoesNotContain8NumChar(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeBecauseInvalidPaymentDataFormat(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");
        
        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }
    
    @Test
    void testInvalidBankTransferBecauseInvalidPaymentDataFormat(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByBankTransfer = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByBankTransfer.getStatus());
    }

    @Test
    void testInvalidBankTransferBecauseBankNameIsNull(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", null);
        paymentData.put("referenceCode", "1234567890");
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByBankTransfer = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByBankTransfer.getStatus());
    }

    @Test
    void testInvalidBankTransferBecauseReferenceCodeIsNull(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", null);
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByBankTransfer = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByBankTransfer.getStatus());
    }

}