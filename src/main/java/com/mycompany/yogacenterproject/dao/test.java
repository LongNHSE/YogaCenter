/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.util.MailConfig;
import com.mycompany.yogacenterproject.util.Utils;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

/**
 *
 * @author Oalskad
 */
public class test {

    public static void main(String[] args) throws EmailException, MalformedURLException {

        // Tạo đối tượng Email
        ImageHtmlEmail email = new ImageHtmlEmail();

        // Cấu hình thông tin Email Server
        email.setHostName(MailConfig.HOST_NAME);
        email.setSmtpPort(MailConfig.SSL_PORT);
        email.setAuthenticator(new DefaultAuthenticator(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD));
        email.setSSLOnConnect(true);
        // Người gửi
        email.setFrom(MailConfig.APP_EMAIL);

        // Người nhận
        email.addTo(MailConfig.RECEIVE_EMAIL);

        // Tiêu đề
        email.setSubject("Testing Subject");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        String OTP = Utils.generateRandomString(5);
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">Your Brand</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>Thank you for choosing Your Brand. Use the following OTP to complete your Sign Up procedures. OTP is valid for 5 minutes</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + OTP + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />Your Brand</p>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>Your Brand Inc</p>"
                + "      <p>1600 Amphitheatre Parkway</p>"
                + "      <p>California</p>"
                + "    </div>"
                + "  </div>"
                + "</div>";
        ;
        email.setHtmlMsg(htmlContent);

        // Nội dung thay thế:
        // Trong trường hợp chương trình đọc email của người nhận ko hỗ trợ HTML
        email.setTextMsg("Your email client does not support HTML messages");

        // send message
        email.send();

        System.out.println("Message sent successfully");
    }
}
