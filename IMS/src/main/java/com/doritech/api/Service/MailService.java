package com.doritech.api.Service;

import com.doritech.api.Entity.ItemMasterEntity;
import com.doritech.api.Entity.ItemRecievedEntity;
import com.doritech.api.Entity.ParamEntity;
import com.doritech.api.Repository.EmployeeMasterRepository;
import com.doritech.api.Repository.ItemMasterRepository;
import com.doritech.api.Repository.ItemRecievedRepository;
import com.doritech.api.Repository.ParamRepository;
import com.doritech.api.Repository.VendorMasterRepository;
import com.doritech.api.Service.MailService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
   @Autowired
   EmployeeMasterRepository employeeRepo;
   @Autowired
   JavaMailSender mailSender;
   @Autowired
   ParamRepository paramRepository;
   @Autowired
   VendorMasterRepository vendorMasterRepository;
   @Autowired
   ItemRecievedRepository itemRecievedRepository;
   @Autowired
   ItemMasterRepository itemMasterRepository;

   public static boolean sendOTPToEmail(String subject, String message, String to) {
      boolean f = false;
      String from = "di.internal@digitalsindia.com";
      String password = "uzgrfilbcdemyvmw";
      String host = "smtp.gmail.com";
      Properties properties = System.getProperties();
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "465");
      properties.put("mail.smtp.ssl.enable", "true");
      properties.put("mail.smtp.auth", "true");
      Session session = Session.getInstance(properties, new 1(from, password));
      session.setDebug(true);
      MimeMessage mimeMessage = new MimeMessage(session);

      try {
         mimeMessage.setFrom(new InternetAddress(from));
         mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
         mimeMessage.setSubject(subject);
         mimeMessage.setContent(message, "text/html");
         Transport.send(mimeMessage);
         System.out.println("Sent successfully...");
         f = true;
      } catch (MessagingException var11) {
         var11.printStackTrace();
      }

      return f;
   }

   public void sendPriceUpdateAlerts(List<ItemRecievedEntity> list) throws Exception {
      try {
         MimeMessage mimeMessage = this.mailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
         ParamEntity emailCredentials = this.paramRepository.findByCode("EMAIL_CREDENTIALS");
         String subject = "Urgent: Price Update Notification";
         String mailText = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><style>body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }table { width: 100%; border-collapse: collapse; margin-top: 10px; }th, td { border: 1px solid black; padding: 8px; background-color: #f2f2f2; }th { text-align: left; }</style></head><body><p style=\"margin-bottom: 10px;\"><strong>Dear Sir/Ma'am,</strong></p><p style=\"margin-bottom: 10px;\">I'm writing to inform you that the price of the following items has been updated.</p><p style=\"margin-bottom: 10px;\"><strong>Date : " + new Date() + "</strong></p><table><tr><th>ITEM CODE</th><th>ITEM NAME</th><th>CURRENT PRICE (Rs./pc.)</th><th>NEW PRICE (Rs./pc.)</th></tr>";

         ItemRecievedEntity itemsReceived;
         for(Iterator var7 = list.iterator(); var7.hasNext(); mailText = mailText + "<tr><td>" + itemsReceived.getItemCode() + "</td><td>" + ((ItemMasterEntity)this.itemMasterRepository.getById(itemsReceived.getItemCode())).getItemName() + "</td><td style=\"text-align: right;\">" + itemsReceived.getCurrentPrice() + "</td><td style=\"text-align: right;\">" + itemsReceived.getItemPrice() + "</td></tr>") {
            itemsReceived = (ItemRecievedEntity)var7.next();
         }

         mailText = mailText + "</table><p style=\"margin-top: 20px;\">Best regards</p></body></html>";
         helper.setText(mailText, true);
         helper.setTo(emailCredentials.getSerial());
         helper.setSubject(subject);
         this.mailSender.send(mimeMessage);
      } catch (Exception var9) {
         var9.printStackTrace();
         throw new RuntimeException("Email send failed: " + var9.getLocalizedMessage());
      }
   }

   public void sendStockUpdateAlerts(List<ItemMasterEntity> list) throws Exception {
      try {
         MimeMessage mimeMessage = this.mailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
         ParamEntity emailCredentials = this.paramRepository.findByCode("EMAIL_CREDENTIALS");
         String subject = "Urgent: Stock Update Notification";
         String mailText = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><style>body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }table { width: 100%; border-collapse: collapse; margin-top: 10px; }th, td { border: 1px solid black; padding: 8px; text-align: left; }th { background-color: #f2f2f2; }</style></head><body><p style=\"margin-bottom: 10px;\"><strong>Dear Sir/Ma'am,</strong></p><p style=\"margin-bottom: 10px;\">I'm writing to inform you that the price of the following item's stock has been updated.</p><p style=\"margin-bottom: 10px;\"><strong>Date: " + new Date() + "</strong></p><table><tr><th>ITEM CODE</th><th>ITEM NAME</th><th>QTY. IN STOCK</th><th>MIN. STOCK</th></tr>";

         ItemMasterEntity itemMasterEntity;
         for(Iterator var7 = list.iterator(); var7.hasNext(); mailText = mailText + "<tr><td align=\"left\">" + itemMasterEntity.getItemCode() + "</td><td align=\"left\">" + itemMasterEntity.getItemName() + "</td><td align=\"right\">" + itemMasterEntity.getQuantityInStock() + "</td><td align=\"right\">" + itemMasterEntity.getMinimumStock() + "</td></tr>") {
            itemMasterEntity = (ItemMasterEntity)var7.next();
         }

         mailText = mailText + "</table><p style=\"margin-top: 20px;\">Best regards</p></body></html>";
         helper.setText(mailText, true);
         helper.setTo(emailCredentials.getSerial());
         helper.setSubject(subject);
         this.mailSender.send(mimeMessage);
      } catch (Exception var9) {
         var9.printStackTrace();
         throw new RuntimeException("Email send failed: " + var9.getLocalizedMessage());
      }
   }

   public void sendNewStockUpdateAlerts(List<ItemRecievedEntity> list) throws Exception {
      try {
         MimeMessage mimeMessage = this.mailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
         ParamEntity emailCredentials = this.paramRepository.findByCode("EMAIL_CREDENTIALS");
         String subject = "Urgent: New Stock Arrived Notification";
         String mailText = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><style>body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }table { width: 100%; border-collapse: collapse; margin-top: 10px; }th, td { border: 1px solid black; padding: 8px; background-color: #f2f2f2; }th { text-align: left; }</style></head><body><p style=\"margin-bottom: 10px;\"><strong>Dear Sir/Ma'am,</strong></p><p style=\"margin-bottom: 10px;\">I'm writing to inform you that the following items has been arrived today.</p><p style=\"margin-bottom: 10px;\"><strong>Date : " + new Date() + "</strong></p><table><tr><th>ITEM CODE</th><th>ITEM NAME</th><th>ITEM QTY.</th><th>CURRENT PRICE (Rs./pc.)</th><th>NEW PRICE (Rs./pc.)</th></tr>";

         ItemRecievedEntity itemsReceived;
         for(Iterator var7 = list.iterator(); var7.hasNext(); mailText = mailText + "<tr><td>" + itemsReceived.getItemCode() + "</td><td>" + ((ItemMasterEntity)this.itemMasterRepository.getById(itemsReceived.getItemCode())).getItemName() + "</td><td style=\"text-align: right;\">" + itemsReceived.getItemQuantity() + "</td><td style=\"text-align: right;\">" + itemsReceived.getCurrentPrice() + "</td><td style=\"text-align: right;\">" + itemsReceived.getItemPrice() + "</td></tr>") {
            itemsReceived = (ItemRecievedEntity)var7.next();
         }

         mailText = mailText + "</table><p style=\"margin-top: 20px;\">Best regards</p></body></html>";
         helper.setText(mailText, true);
         helper.setTo(emailCredentials.getSerial());
         helper.setSubject(subject);
         this.mailSender.send(mimeMessage);
      } catch (Exception var9) {
         var9.printStackTrace();
         throw new RuntimeException("Email send failed: " + var9.getLocalizedMessage());
      }
   }
}
