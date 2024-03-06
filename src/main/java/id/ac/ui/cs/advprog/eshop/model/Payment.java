package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.paymentData = paymentData;
        this.order = order;

        if (method.equals("VOUCHER_CODE")) {
            verifyVoucherCode(paymentData);
        } else if (method.equals("BANK_TRANSFER")){
            verifyBankTransfer(paymentData);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void verifyVoucherCode(Map<String, String> paymentData){
        String voucherCode = paymentData.get("voucherCode");
        if (isPaymentDataByVoucherCodeFormatValid(paymentData) && isVoucherCodeValid(voucherCode)){
            this.setMethod("VOUCHER_CODE");
            this.setStatus("SUCCESS");
        } else {
            this.setStatus("REJECTED");
        }
    }

    public void verifyBankTransfer(Map<String, String> paymentData){
        if (paymentDataByBankTransferValid(paymentData) && bankNameNotNull(paymentData) && referenceCodeNotNull(paymentData)) {
            this.setMethod("BANK_TRANSFER");
            this.setStatus("SUCCESS");
        } else {
            this.setStatus("REJECTED");
        }
    }

    public boolean paymentDataByBankTransferValid(Map<String, String> paymentData){
        List<String> requiredKeys = Arrays.asList("bankName", "referenceCode");
        return paymentData.keySet().containsAll(requiredKeys);
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    static public boolean isPaymentDataByVoucherCodeFormatValid(Map<String, String> paymentData){
        return paymentData.size() == 1 && paymentData.containsKey("voucherCode");
    }

    static public boolean isVoucherCodeValid(String voucherCode){
        return isVoucherCodeStartedWithESHOP(voucherCode) && isVoucherCode16Length(voucherCode) && isNumCharInVoucherCode8length(voucherCode);
    }

    static public boolean isVoucherCodeStartedWithESHOP(String voucherCode){
        return voucherCode.startsWith("ESHOP");
    }

    static public boolean isVoucherCode16Length(String voucherCode){
        return voucherCode.length() == 16;
    }

    static public boolean isNumCharInVoucherCode8length(String voucherCode){
        int numChar = 0;
        for(int i = 0; i < voucherCode.length(); i++){
            if(Character.isDigit(voucherCode.charAt(i))){
                numChar++;
            }
        }
        return numChar == 8;
    }

    static public boolean bankNameNotNull(Map<String, String> paymentData){
        return paymentData.get("bankName") != null;
    }

    static public boolean referenceCodeNotNull(Map<String, String> paymentData){
        return paymentData.get("referenceCode") != null;
    }
}