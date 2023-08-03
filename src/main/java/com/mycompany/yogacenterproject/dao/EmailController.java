/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.MailConfig;
import com.mycompany.yogacenterproject.util.Utils;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

/**
 *
 * @author Oalskad
 */
public class EmailController {

    public static void OTPSender(String OTP, String mailAddress) throws EmailException, MalformedURLException {

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
        email.addTo(mailAddress);

        // Tiêu đề
        email.setSubject("OTP");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>Thank you for choosing YogaCenter. Use the following OTP to complete your Sign Up procedures. OTP is valid for 5 minutes</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + OTP + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void ClassRegister(LopHocDTO lopHocDTO, String mailAddress) throws EmailException, MalformedURLException {

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
        email.addTo(mailAddress);

        // Tiêu đề
        email.setSubject("YogaCenter");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>You have just registered class " + "<p style=\"text-decoration: underline;\">" + lopHocDTO.getLoaiLopHocDTO().getTenLoaiLopHoc() + "</p>" + "Start from: " + lopHocDTO.getNgayBatDau() + " To: " + lopHocDTO.getNgayKetThuc() + ".Thank you for choosing YogaCenter.</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void trainerDTOCreate(TrainerDTO trainerDTO) throws EmailException, MalformedURLException {

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
        email.addTo(trainerDTO.getEmail());

        // Tiêu đề
        email.setSubject("YogaCenter Trainer Account");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>Here is your trainer account " + "<p style=\"text-decoration: underline;\"> Username: " + trainerDTO.getUsername() + "</p>" + "<p style=\"text-decoration: underline;\">Password: " + trainerDTO.getPsw() + "</p>" + "</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void trainerDTOAssign(TrainerDTO trainerDTO, LopHocDTO lopHocDTO) throws EmailException, MalformedURLException {

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
        email.addTo(trainerDTO.getEmail());

        // Tiêu đề
        email.setSubject("YogaCenter Trainer Assign");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>You have been assigned to class: <span style=\"text-decoration: underline;\">" + lopHocDTO.getMaLopHoc() + "</span></p>"
                + "    <p>Start from: " + lopHocDTO.getNgayBatDau() + " To: " + lopHocDTO.getNgayKetThuc() + "</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void trainerDTOAssignDayOff(TrainerDTO trainerDTO, LopHocDTO lopHocDTO, Date date, String slot) throws EmailException, MalformedURLException {

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
        email.addTo(trainerDTO.getEmail());

        // Tiêu đề
        email.setSubject("YogaCenter Trainer Assign");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>You have been assigned to class: <span style=\"text-decoration: underline;\">" + lopHocDTO.getMaLopHoc() + "</span></p>"
                + "</span> <span style=\"text-decoration: underline;\">" + slot + "</span> <span style=\"text-decoration: underline;\">" + date + "</span>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void ClassChange(LopHocDTO lopHocDTO, String mailAddress, String maLopHocCu) throws EmailException, MalformedURLException {

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
        email.addTo(mailAddress);

        // Tiêu đề
        email.setSubject("YogaCenter");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>You have just Changed from class " + maLopHocCu + " To class " + lopHocDTO.getMaLopHoc() + "<p style=\"text-decoration: underline;\"> Class Type " + lopHocDTO.getLoaiLopHocDTO().getTenLoaiLopHoc() + "</p>" + "Start from: " + lopHocDTO.getNgayBatDau() + " To: " + lopHocDTO.getNgayKetThuc() + ".Thank you for choosing YogaCenter.</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void ClassReserve(LopHocDTO lopHocDTO, String mailAddress) throws EmailException, MalformedURLException {

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
        email.addTo(mailAddress);

        // Tiêu đề
        email.setSubject("YogaCenter");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>You have just reserved class " + lopHocDTO.getMaLopHoc() + "</p>" + "<p>. Your next register of  " + lopHocDTO.getLoaiLopHocDTO().getTenLoaiLopHoc() + " will be discounted.</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void approveResquestOff(TrainerDTO trainerDTO, LopHocDTO lopHocDTO) throws EmailException, MalformedURLException {

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
        email.addTo(trainerDTO.getEmail());

        // Tiêu đề
        email.setSubject("YogaCenter Trainer Assign");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>Your off request for class: <span style=\"text-decoration: underline;\">" + lopHocDTO.getMaLopHoc() + "</span> is approved</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void approveResquestDayOff(TrainerDTO trainerDTO, LopHocDTO lopHocDTO, Date date, String slot) throws EmailException, MalformedURLException {

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
        email.addTo(trainerDTO.getEmail());

        // Tiêu đề
        email.setSubject("YogaCenter Trainer Assign");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>Your day off request for class: <span style=\"text-decoration: underline;\">" + lopHocDTO.getMaLopHoc() + "</span> <span style=\"text-decoration: underline;\">" + slot + "</span> <span style=\"text-decoration: underline;\">" + date + "</span>  is approved</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void unapproveResquestOff(TrainerDTO trainerDTO, LopHocDTO lopHocDTO) throws EmailException, MalformedURLException {

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
        email.addTo(trainerDTO.getEmail());

        // Tiêu đề
        email.setSubject("YogaCenter Trainer Assign");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>Your off request for class: <span style=\"text-decoration: underline;\">" + lopHocDTO.getMaLopHoc() + "</span> is not approved</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void requestOff(TrainerDTO trainerDTO, LopHocDTO lopHocDTO) throws EmailException, MalformedURLException {

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
        email.addTo(trainerDTO.getEmail());

        // Tiêu đề
        email.setSubject("YogaCenter Trainer Assign");

        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
        URL url = new URL("https://gpcoder.com");

        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        // Nội dung email
        String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                + "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                + "    <div style=\"border-bottom:1px solid #eee\">"
                + "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">YogaCenter</a>"
                + "    </div>"
                + "    <p style=\"font-size:1.1em\">Hi,</p>"
                + "    <p>Your off request for class: <span style=\"text-decoration: underline;\">" + lopHocDTO.getMaLopHoc() + "</span> is acknowledged.</p>"
                + "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + "</h2>"
                + "    <p style=\"font-size:0.9em;\">Regards,<br />YogaCenter</p>"
                + "    <a href=\"http://localhost:8080/YogaCenter/Public/home.jsp \"><p style=\"font-size:0.9em;\">Visit our Website for more information</p></a>"
                + "    <hr style=\"border:none;border-top:1px solid #eee\" />"
                + "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                + "      <p>YogaCenter Inc</p>"
                + "      <p>99/1 Distric 1</p>"
                + "      <p>TP.HCM</p>"
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

    public static void main(String[] args) throws EmailException, MalformedURLException {
        TrainerDAO trainerDAO = new TrainerDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        EmailController.trainerDTOAssign(trainerDAO.readTrainer("TR0009"), lopHocDAO.searchClassById("LOP0021"));
    }
}
