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
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author iba
 */
public class PaymentServices {

    private static final String CLIENT_ID = "Af57aduykiQl8BGZeEj59L3BNf9YdDGc6BWfwE3quJBp-Zmft1L1Y5XC6cljta93NDD0AYVJgvN5EHPI";
    private static final String CLIENT_SECRET = "EPVh3_T1klsRxe70bP_dKKBO2NZauLmKZ1V-PANrVQci-1016acfvhxDFQtbiTaEvQoo5lTXwQ269R4w";
    private static final String MODE = "sandbox";

    String uuid = null;

    public String createPayment(LopHocDTO lopHocDTO, HocVienDTO hocVienDTO) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        // Create a Payment object using the PayPal SDK
        Payer payer = getPayerInformation(hocVienDTO);
        Transaction transaction = getTransactionInformation(lopHocDTO);
        RedirectUrls redirectUrls = getRedirectURL(lopHocDTO);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(Collections.singletonList(transaction));
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        Payment approvedPayment = requestPayment.create(apiContext);
        String approvalLink = getApprovalLink(approvedPayment);

//        if(!approvalLink.isEmpty()){
//            
//            executePayment(uuid, payer.getPayerInfo().getPayerId());
//        }
        System.out.println(approvalLink);

        return approvalLink;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }
        }
        System.out.println(approvalLink);
        return approvalLink;
    }

    private Transaction getTransactionInformation(LopHocDTO lopHocDTO) {
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();

        long subtotal = loaiLopHocDAO.searchHocPhiLopHocWithDouble(lopHocDTO.getMaLoaiLopHoc()) / 21000; // Replace with actual calculation based on lopHocDTO
        long tax = 0; // Replace with actual calculation based on lopHocDTO
        long shipping = 0; // Replace with actual calculation based on lopHocDTO
        long totalAmount = subtotal + tax + shipping;
        Details details = new Details();
        details.setShipping(String.valueOf(shipping));
        details.setSubtotal(String.valueOf(subtotal));
        details.setTax(String.valueOf(tax));

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.valueOf(subtotal));
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Register for the class: " + loaiLopHocDAO.searchTenLoaiLopHoc(lopHocDTO.getMaLoaiLopHoc()));

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setCurrency("USD");
        item.setName(loaiLopHocDAO.searchTenLoaiLopHoc(lopHocDTO.getMaLoaiLopHoc()));
        item.setPrice(String.valueOf(totalAmount));
        item.setTax("0");
        item.setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return transaction;
    }

    private RedirectUrls getRedirectURL(LopHocDTO lopHocDTO) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/YogaCenter/ClassController?returnID=" + lopHocDTO.getMaLoaiLopHoc() + "&action=showDetails");
        redirectUrls.setReturnUrl("http://localhost:8080/YogaCenter/ClassController?returnID=" + lopHocDTO.getMaLopHoc() + "&action=SuccessfulPayment");

        return redirectUrls;
    }

    private Payer getPayerInformation(HocVienDTO hocVienDTO) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(hocVienDTO.getTen())
                .setLastName(hocVienDTO.getHo())
                .setEmail(hocVienDTO.getEmail());

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private String generatePaymentID() {
        UUID returnUUID = UUID.randomUUID();
        return returnUUID.toString();
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment payment = new Payment();
        uuid = generatePaymentID();
        payment.setId(uuid);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }

}
