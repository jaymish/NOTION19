package com.notion.service;

import java.io.*;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles) throws AddressException, MessagingException 
	{
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
    }
	
	public static void sendEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException, MessagingException
	{
		 // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
	}
	 
	 public void sendVerificationLink(String email, String name)
	 {
		// SMTP info
	        String host = "smtp.gmail.com";
	        String port = "587";
	        String mailFrom = "notion@ljinstitutes.edu.in";
	        String password = "notion@2k18";
	        
	     // message info
	        String mailTo = email;
	        String subject = "Notion 2k19. Verify Email-Id";
	        /*Web Path: http://94.23.204.116:8080/notion/verifyUser?username="+email+"*/
	        /*Local Path: http://192.168.29.221:9090/verifyUser?username="+email+"*/
	        String message="<!DOCTYPE html><html><head><title></title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><style type=\"text/css\">    /* FONTS */    @import url('https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i');    /* CLIENT-SPECIFIC STYLES */    body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }    table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }    img { -ms-interpolation-mode: bicubic; }    /* RESET STYLES */    img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }    table { border-collapse: collapse !important; }    body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }    /* iOS BLUE LINKS */    a[x-apple-data-detectors] {        color: inherit !important;        text-decoration: none !important;        font-size: inherit !important;        font-family: inherit !important;        font-weight: inherit !important;        line-height: inherit !important;    }    /* MOBILE STYLES */    @media screen and (max-width:600px){        h1 {            font-size: 32px !important;            line-height: 32px !important;        }    }    /* ANDROID CENTER FIX */    div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }</style></head><body style=\"background-color: #f3f5f7; margin: 0 !important; padding: 0 !important;\"><!-- HIDDEN PREHEADER TEXT --><div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: 'Poppins', sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">    We're thrilled to have you here! Get ready to dive into your new account.</div><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">    <!-- LOGO -->    <tr>        <td align=\"center\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">                <tr>                    <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 10px 10px;\">                        <a href=\"#\" target=\"_blank\" style=\"text-decoration: none;\">							<span style=\"display: block; font-family: 'Poppins', sans-serif; color: #3e8ef7; font-size: 36px;\" border=\"0\"><b>NOTION</b> 2K19</span>                        </a>                    </td>                </tr>            </table>        </td>    </tr>    <!-- HERO -->    <tr>        <td align=\"center\" style=\"padding: 0px 10px 0px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">                <tr>                    <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Poppins', sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 2px; line-height: 48px;\">                      <h1 style=\"font-size: 36px; font-weight: 600; margin: 0;\">Hi "+name+"!</h1>                    </td>                </tr>            </table>        </td>    </tr>    <!-- COPY BLOCK -->    <tr>        <td align=\"center\" style=\"padding: 0px 10px 0px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 20px 30px; color: #666666; font-family: 'Poppins', sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0;\">We're excited to have you in NOTION 2K19. First, you need to confirm your account. Just press the button below.</p>                </td>              </tr>              <!-- BULLETPROOF BUTTON -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\">                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">                    <tr>                      <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 30px 30px;\">                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">                          <tr>                              <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#17b3a3\"><a href=\"http://94.23.204.116:8080/notion/verifyUser?username="+email+"\" target=\"_blank\" style=\"font-size: 18px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 12px 50px; border-radius: 2px; border: 1px solid #17b3a3; display: inline-block;\">Confirm Account</a></td>                          </tr>                        </table>                      </td>                    </tr>                  </table>                </td>              </tr>              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0;\">If you have any questions, just reply to this email we're always happy to help out.</p>                </td>              </tr>              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 0px 0px; color: #666666; font-family: 'Poppins', sans-serif; font-size: 14px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0;\">Cheers,<br>Team Notion</p>                </td>              </tr>            </table>        </td>    </tr>    <!-- FOOTER -->    <tr>        <td align=\"center\" style=\"padding: 10px 10px 50px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">		      <!-- COPYRIGHT -->              <tr>                <td align=\"center\" style=\"padding: 30px 30px 30px 30px; color: #333333; font-family: 'Poppins', sans-serif; font-size: 12px; font-weight: 400; line-height: 18px;\">                  <p style=\"margin: 0;\">Copyright © 2019 Notion. All rights reserved.</p>                </td>              </tr>            </table>        </td>    </tr></table></body></html>";
	       
	        try {
	            sendEmail(host, port, mailFrom, password, mailTo,
	                subject, message);
	            System.out.println("Email sent.");
	        } catch (Exception ex) {
	            System.out.println("Could not send email.");
	            ex.printStackTrace();
	        }
	 }
	
	 public void sendQRCode(String email)
	 {
	        // SMTP info
	        String host = "smtp.gmail.com";
	        String port = "587";
	        String mailFrom = "notion@ljinstitutes.edu.in";
	        String password = "notion@2k18";
	 
	        // message info
	        String mailTo = email;
	        String subject = "Your Notion-2k19 QRCode";
	        String message = "<!DOCTYPE html><html><head><title></title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><style type=\"text/css\">    /* FONTS */    @import url('https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i');    /* CLIENT-SPECIFIC STYLES */    body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }    table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }    img { -ms-interpolation-mode: bicubic; }    /* RESET STYLES */    img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }    table { border-collapse: collapse !important; }    body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }    /* iOS BLUE LINKS */    a[x-apple-data-detectors] {        color: inherit !important;        text-decoration: none !important;        font-size: inherit !important;        font-family: inherit !important;        font-weight: inherit !important;        line-height: inherit !important;    }    /* MOBILE STYLES */    @media screen and (max-width:600px){        h1 {            font-size: 32px !important;            line-height: 32px !important;        }    }    /* ANDROID CENTER FIX */    div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }</style></head><body style=\"background-color: #f3f5f7; margin: 0 !important; padding: 0 !important;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">    <!-- LOGO -->    <tr>        <td align=\"center\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">                <tr>                    <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 10px 10px;\">                        <a href=\"#\" target=\"_blank\" style=\"text-decoration: none;\">							<span style=\"display: block; font-family: 'Poppins', sans-serif; color: #3e8ef7; font-size: 36px;\" border=\"0\"><b>NOTION</b> 2K19</span>                        </a>                    </td>                </tr>            </table>        </td>    </tr>    <!-- HERO -->    <tr>        <td align=\"center\" style=\"padding: 0px 10px 0px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">                <tr>                    <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px;\">                      <h1 style=\"font-size: 36px; font-weight: 600; margin: 0; font-family: 'Poppins', sans-serif;\">Welcome!</h1>                    </td>                </tr>            </table>        </td>    </tr>    <!-- COPY BLOCK -->    <tr>        <td align=\"center\" style=\"padding: 0px 10px 0px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 20px 30px; color: #666666; font-family: 'Poppins', sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0;\">Thank you for participating in NOTION 2K19! We hope you enjoy your time with us. Select the events you like to participate in from the website. Your QR for attendance is attached below.<br/> <font color=\"red\">Note: This QR will only be activated after you select event/s and complete payment of the same.</font></p>                </td>              </tr>              <!-- BULLETPROOF BUTTON -->              <tr>                <td bgcolor=\"#ffffff\" align=\"center\">                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">                    <tr>                      <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 30px 30px;\">                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">                          <tr>                                                        </tr>                        </table>                      </td>                    </tr>                  </table>                </td>              </tr>              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: 'Poppins', sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0;\">If you have any questions, just reply to this email we're always happy to help out.</p>                </td>              </tr>              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 0px 0px; color: #666666; font-family: 'Poppins', sans-serif; font-size: 14px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0;\">Cheers,<br>Team Notion</p>                </td>              </tr>            </table>        </td>    </tr>    <!-- FOOTER -->    <tr>        <td align=\"center\" style=\"padding: 10px 10px 50px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">		      <!-- COPYRIGHT -->              <tr>                <td align=\"center\" style=\"padding: 30px 30px 30px 30px; color: #333333; font-family: 'Poppins', sans-serif; font-size: 12px; font-weight: 400; line-height: 18px;\">                  <p style=\"margin: 0;\">Copyright © 2019 Notion. All rights reserved.</p>                </td>              </tr>            </table>        </td>    </tr></table></body></html>";
	 
	        // attachments
	        String[] attachFiles = new String[1];
	        /*Web Path: ../webapps/notion/WEB-INF/classes/static/qr/ */
	        /*Local Path: src/main/resources/static/qr/*/
	        attachFiles[0]="../webapps/notion/WEB-INF/classes/static/qr/"+email+".png";
	        
	 
	        try {
	            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
	                subject, message, attachFiles);
	            System.out.println("Email sent.");
	        } catch (Exception ex) {
	            System.out.println("Could not send email.");
	            ex.printStackTrace();
	        }
	    }
	 
	 public void sendResetLink(String email, String token)
	 {
		// SMTP info
	        String host = "smtp.gmail.com";
	        String port = "587";
	        String mailFrom = "notion@ljinstitutes.edu.in";
	        String password = "notion@2k18";
	        
	     // message info
	        String mailTo = email;
	        String subject = "Notion 2k19. Reset Password";
	        /*Web Path: http://94.23.204.116:8080/notion/resetPassword?username="+email+"&token="+token+"*/
	        /*Local Path: http://192.168.29.221:9090/resetPassword?username="+email+"&token="+token+"*/
	        String message="<!DOCTYPE html><html><head><title></title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><style type=\"text/css\">    /* FONTS */    @import url('https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i');    /* CLIENT-SPECIFIC STYLES */    body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }    table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }    img { -ms-interpolation-mode: bicubic; }    /* RESET STYLES */    img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }    table { border-collapse: collapse !important; }    body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }    /* iOS BLUE LINKS */    a[x-apple-data-detectors] {        color: inherit !important;        text-decoration: none !important;        font-size: inherit !important;        font-family: inherit !important;        font-weight: inherit !important;        line-height: inherit !important;    }    /* MOBILE STYLES */    @media screen and (max-width:600px){        h1 {            font-size: 32px !important;            line-height: 32px !important;        }    }    /* ANDROID CENTER FIX */    div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }</style></head><body style=\"background-color: #f3f5f7; margin: 0 !important; padding: 0 !important;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">    <!-- LOGO -->    <tr>        <td align=\"center\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">                <tr>                    <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 10px 10px;\">                        <a href=\"\" style=\"text-decoration: none;\">							<span style=\"display: block; font-family: 'Poppins', sans-serif; color: #3e8ef7; font-size: 36px;\" border=\"0\"><b>NOTION</b> 2K19</span>                        </a>                    </td>                </tr>            </table>        </td>    </tr>    <!-- HERO -->    <tr>        <td align=\"center\" style=\"padding: 0px 10px 0px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">                <tr>                    <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #ff4c52; font-family: 'Poppins', sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 2px; line-height: 48px;\">                      <h1 style=\"font-size: 36px; font-weight: 600; margin: 0;\">Trouble signing in?</h1>                    </td>                </tr>            </table>        </td>    </tr>    <!-- COPY BLOCK -->    <tr>        <td align=\"center\" style=\"padding: 0px 10px 0px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Poppins', sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0;\">There is a request to change your password. Resetting your password is easy. Just press the button below and follow the instructions. We'll have you up and running in no time.</p>                </td>              </tr>              <!-- BULLETPROOF BUTTON -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\">                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">                    <tr>                      <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">                          <tr>                              <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#ff4c52\"><a href=\"http://94.23.204.116:8080/notion/resetPassword?username="+email+"&token="+token+"\" target=\"_blank\" style=\"font-size: 18px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 12px 50px; border-radius: 2px; border: 1px solid #ff4c52; display: inline-block;\">Reset Password</a></td>                          </tr>                        </table>                      </td>                    </tr>                  </table>                </td>              </tr>              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #aaaaaa; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 13px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0; text-align: center;\">If you did not make this request, just ignore this email. Otherwise, pleas click button above to change your password.</p>                </td>              </tr>              <!-- COPY -->              <tr>                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 0px 0px; color: #666666; font-family: 'Poppins', sans-serif; font-size: 14px; font-weight: 400; line-height: 25px;\">                  <p style=\"margin: 0;\">Cheers,<br>Team Notion</p>                </td>              </tr>            </table>        </td>    </tr>    <!-- FOOTER -->    <tr>        <td align=\"center\" style=\"padding: 10px 10px 50px 10px;\">            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">		      <!-- COPYRIGHT -->              <tr>                <td align=\"center\" style=\"padding: 30px 30px 30px 30px; color: #333333; font-family: 'Poppins', sans-serif; font-size: 12px; font-weight: 400; line-height: 18px;\">                  <p style=\"margin: 0;\">Copyright © 2019 Notion. All rights reserved.</p>                </td>              </tr>            </table>        </td>    </tr></table></body></html>";

	        try {
	            sendEmail(host, port, mailFrom, password, mailTo,
	                subject, message);
	            System.out.println("Email sent.");
	        } catch (Exception ex) {
	            System.out.println("Could not send email.");
	            ex.printStackTrace();
	        }
	 }
}
