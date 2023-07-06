/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayPal;

import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.LoaiLopHocDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author iba
 */
public class PaymentServices {

    private static final String CLIENT_ID = "your_paypal_client_id";
    private static final String CLIENT_SECRET = "your_paypal_client_secret";
    private static final String MODE = "sandbox";
    LopHocDAO lopHocDAO = new LopHocDAO();
    LopHocDTO lopHocDTO = new LopHocDTO();
    LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
    HocVienDAO hocVienDAO = new HocVienDAO();
    HocVienDTO hocVienDTO = new HocVienDTO();

    public String createPayment(LopHocDTO lopHocDTO) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        hocVienDTO = hocVienDAO.searchHocVienById("HV0001"); //dynamic
        lopHocDTO = lopHocDAO.searchClassById("LOP0001");

        // Create a Payment object using the PayPal SDK
        Payer payer = getPayerInformation();
        Transaction transaction = getTransactionInformation(lopHocDTO);
        RedirectUrls redirectUrls = getRedirectURL();

        Payment payment = new Payment();
        payment.setRedirectUrls(redirectUrls)
                .setPayer(payer)
                .setTransactions(Collections.singletonList(transaction))
                .setIntent("authorize");
        payment.create(apiContext);

        System.out.println(payment);

        return getApprovalLink(payment);
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }
        }
        return approvalLink;
    }

    private Transaction getTransactionInformation(LopHocDTO lopHocDTO) {
        Amount amount = new Amount();
        amount.setCurrency("VND");
        amount.setTotal(loaiLopHocDAO.searchHocPhiLopHoc(lopHocDTO.getMaLopHoc()));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Enrollment for Class: " + lopHocDTO.getMaLopHoc());

        return transaction;
    }

    private RedirectUrls getRedirectURL() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl("http://example.com/payment-success");
        redirectUrls.setCancelUrl("http://example.com/payment-cancel");
        return redirectUrls;
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(hocVienDTO.getTen())
                .setLastName(hocVienDTO.getHo())
                .setEmail(hocVienDTO.getEmail())
                .setBirthDate(String.valueOf(hocVienDTO.getDob()))
                .setPhone(hocVienDTO.getPhone());

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }

    
}
