package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class PaymentTest {

    @Test
    void testCreatePaymentByVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData);

        assertEquals("VOUCHER_CODE", paymentByVoucherCode.getMethod());
    }

    @Test
    void testCreatePaymentByBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");

        Payment paymentByBankTransfer = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData);

        assertEquals("BANK_TRANSFER", paymentByBankTransfer.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "WRONG_METHOD", paymentData);
        });
    }

    @Test
    void testSetToSuccessStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData);
        paymentByVoucherCode.setStatus("SUCCESS");

        assertEquals("SUCCESS",paymentByVoucherCode.getStatus());
    }

    @Test
    void testSetToRejectedStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData);
        paymentByVoucherCode.setStatus("REJECTED");

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeMoreThan16CharLong(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC56781");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeLessThan16CharLong(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeIsNotStartedWithESHOP(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeDoesNotContain8NumChar(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeBecauseInvalidPaymentDataFormat(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");
        
        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }
    
    @Test
    void testInvalidBankTransferBecauseInvalidPaymentDataFormat(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByBankTransfer = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData);

        assertEquals("REJECTED", paymentByBankTransfer.getStatus());
    }

    @Test
    void testInvalidBankTransferBecauseBankNameIsNull(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", null);
        paymentData.put("referenceCode", "1234567890");
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByBankTransfer = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData);

        assertEquals("REJECTED", paymentByBankTransfer.getStatus());
    }

    @Test
    void testInvalidBankTransferBecauseReferenceCodeIsNull(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", null);
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByBankTransfer = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData);

        assertEquals("REJECTED", paymentByBankTransfer.getStatus());
    }

}