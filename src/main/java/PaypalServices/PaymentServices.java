/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaypalServices;

/**
 *
 * @author Oalskad
 */
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import java.util.*;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

public class PaymentServices {

    private static final String CLIENT_ID = "Af57aduykiQl8BGZeEj59L3BNf9YdDGc6BWfwE3quJBp-Zmft1L1Y5XC6cljta93NDD0AYVJgvN5EHPI";
    private static final String CLIENT_SECRET = "EPVh3_T1klsRxe70bP_dKKBO2NZauLmKZ1V-PANrVQci-1016acfvhxDFQtbiTaEvQoo5lTXwQ269R4w";
    private static final String MODE = "sandbox";

    public String authorizePayment(LopHocDTO lopHocDTO)
            throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(lopHocDTO);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);
        System.out.println(approvedPayment);
        return getApprovalLink(approvedPayment);

    }

    private Payer getPayerInformation() {

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("William")
                .setLastName("Peterson")
                .setEmail("william.peterson@company.com");

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/YogaCenter/ClassController?returnID=TYPE0002&action=showDetails");
        redirectUrls.setReturnUrl("http://localhost:8080/YogaCenter/ClassController?returnID=TYPE0001&action=showDetails");
       

        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(LopHocDTO lopHocDTO) {
        double subtotal = 1000; // Replace with actual calculation based on lopHocDTO
        double tax = 1000; // Replace with actual calculation based on lopHocDTO
        double shipping = 1000; // Replace with actual calculation based on lopHocDTO

        Details details = new Details();
        details.setShipping(String.valueOf(shipping));
        details.setSubtotal(String.valueOf(subtotal));
        details.setTax(String.valueOf(tax));

        double totalAmount = subtotal + tax + shipping;

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.valueOf(totalAmount));
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("abc");

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD");
        item.setName("abc");
        item.setPrice("1000");
        item.setTax("1000");
        item.setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }
}
