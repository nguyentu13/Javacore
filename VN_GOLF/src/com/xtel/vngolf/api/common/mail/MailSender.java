//package com.xtel.vngolf.api.common.mail;
//
//import java.util.List;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.tbv.utils.textbase.StringUtil;
//import com.xtel.vngolf.api.common.mail.response.SendMailResult;
//import com.xtel.vngolf.api.config.MailConfig;
//
//public class MailSender {
//
//	protected static Log logger = LogFactory.getLog(MailSender.class);
//
//    private static MailConfigEntity mailConfig = MailConfig.mailSetupConfig;
//    private static List<FromMailEntity> fromMailsConfig = MailConfig.froms;
//    protected static Properties properties = new Properties();
//
//    public static void setup() {
//
//        properties.setProperty("mail.smtp.host", mailConfig.getHost());
//        properties.setProperty("mail.smtp.port", String.valueOf(mailConfig.getPort()));
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.protocol", mailConfig.getProtocol());
//        if(mailConfig.isSsl()) {
//        	properties.setProperty("mail.smtp.ssl.enable", "true");
//        	properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            properties.setProperty("mail.smtp.ssl.trust", "*");
//        } else {
//        	properties.setProperty("mail.smtp.starttls.enable", "true");
//        	properties.setProperty("mail.smtp.socketFactory.fallback", "true");
//        }
//    }
//
//    private static int idxSeed = 0;
//    public synchronized static FromMailEntity roundRobinFromMail(String transid) {
//
//    	try {
//    		if(fromMailsConfig == null || fromMailsConfig.isEmpty()) {
//    			logger.info(String.format("transid: %s, FROM_MAIL_CONFIG IS NULL OR EMPTY, PLEASE RECONFIG, return", transid));
//    			return null;
//    		}
//
//			if(idxSeed >= fromMailsConfig.size()) {
//				idxSeed = 0;
//				return fromMailsConfig.get(idxSeed);
//			}
//
//			FromMailEntity fromMail = fromMailsConfig.get(idxSeed);
//			idxSeed += 1;
//
//			return fromMail;
//		} catch (Exception e) {
//			logger.warn(String.format("###### transid: %s, Exception: %s", transid, e.getMessage()), e);
//		}
//		return null;
//
//    }
//
//    public static SendMailResult roundRobinFromMailAndSend(
//            String transid, String subject, String content, List<String> toMails, List<String> ccMails) throws Exception {
//
//        FromMailEntity fromMail = roundRobinFromMail(transid);
//        if (fromMail == null) {
//            logger.info(String.format("transid: %s, subject: %s, fromMail: %s, toMails: %s, ccMails: %s, FROM_MAIL IS NULL, return",
//                    transid, subject, fromMail, toMails, ccMails));
//
//            SendMailResult sendMailResult = new SendMailResult();
//            sendMailResult.setTransid(transid);
//            sendMailResult.setDesc("ROUND_ROBIN_AND_SEND: NO EMAIL TO SEND !!");
//            return sendMailResult;
//        }
//
//        return send(transid, subject, content, fromMail, toMails, ccMails);
//    }
//
//    public static SendMailResult send(
//            String transid, String subject, String content, FromMailEntity fromMail, List<String> toMails, List<String> ccMails) throws Exception {
//
//        SendMailResult sendMailResult = new SendMailResult();
//        sendMailResult.setTransid(transid);
//        sendMailResult.setFrom(fromMail.getFromMail());
//
//        logger.info(String.format("transid: %s, subject: %s, fromMail: %s, toMails: %s, ccMails: %s, begin ...", transid, subject, fromMail, toMails, ccMails));
//
//        properties.setProperty("mail.smtp.user", fromMail.getUsername());
//        properties.setProperty("mail.smtp.password", fromMail.getPassword());
//
//        // Get the default Session object.
//        Session session = Session.getDefaultInstance(properties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(fromMail.getUsername(), fromMail.getPassword());
//                    }
//                });
//        session.setDebug(true);
//
//        logger.info(String.format("++++ transid: %s, begin send with props: %s ...", transid, properties));
//
//        MimeMessage msg = new MimeMessage(session);
//
//        /*
//         * from address
//         */
//        InternetAddress addressFrom = new InternetAddress(fromMail.getFromMail());
//        if (!StringUtil.isNullOrEmpty(mailConfig.getFromPersonal())) {
//            addressFrom.setPersonal(mailConfig.getFromPersonal(), "UTF8");
//        }
//        msg.setFrom(addressFrom);
//
//        /*
//         * to address
//         */
//        if (toMails != null && !toMails.isEmpty()) {
//            InternetAddress[] addressTo = getAddresses(toMails);
//            msg.setRecipients(Message.RecipientType.TO, addressTo);
//        } else {
//            logger.warn(String.format("transid: %s, TO ADDRESS IS NULL OR EMPTY", transid));
//        }
//
//        /*
//         * cc address
//         */
//        if (ccMails != null && !ccMails.isEmpty()) {
//            InternetAddress[] addressCc = getAddresses(ccMails);
//            msg.setRecipients(Message.RecipientType.CC, addressCc);
//        } else {
//            logger.warn(String.format("transid: %s, CC ADDRESS IS NULL OR EMPTY", transid));
//        }
//
//        msg.setSubject(subject, "UTF-8");
//
//        /*
//         * content
//         */
//        msg.setContent(content, "text/html; charset=utf-8");
//
//        logger.info(String.format("###### transid: %s, Begin send mail", transid));
//
//        try {
//            Transport.send(msg);
//            sendMailResult.setSendSuccess(true);
//            sendMailResult.setDesc("SUCCESS");
//            logger.info(String.format("###### transid: %s, Mail has been sent", transid));
//        } catch (Exception e) {
//            sendMailResult.setDesc("SEND_EMAIL_EXCEPTION: " + e.getMessage());
//            logger.warn(String.format("###### transid: %s, Exception: %s, Mail can't sent", transid, e.getMessage()), e);
//        }
//        return sendMailResult;
//    }
//
//    private static InternetAddress[] getAddresses(List<String> lsMail) throws Exception {
//
//        InternetAddress[] result = new InternetAddress[lsMail.size()];
//        for (int i = 0; i < lsMail.size(); i++) {
//            result[i] = new InternetAddress(lsMail.get(i));
//        }
//        return result;
//    }
//
//	protected static void addAttachment(Multipart multipart, String filename) throws Exception {
//
//	    DataSource source = new FileDataSource(filename);
//	    BodyPart messageBodyPart = new MimeBodyPart();
//	    messageBodyPart.setDataHandler(new DataHandler(source));
//	    messageBodyPart.setFileName(filename);
//	    multipart.addBodyPart(messageBodyPart);
//	}
//
//}
