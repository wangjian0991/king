package base.king;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtils {

	public static void main(String[] args) {
		try {
			send20991();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void send20991() throws Exception{
		Session session=createSession("smtp.163.com","vip62755@163.com","tx901116");	
		MimeMessage mm=createMessage(session,  "给你一张图", "给你发一张图片",new String[]{"E:\\458328.jpg"});
		setFromTo(mm,"vip62755@163.com",new String[]{"wangjian0991@163.com","1115679859@qq.com"});
		Transport.send(mm);
		System.out.println("发送完成");
	}
	
	//与服务器获取连接
	public static Session createSession(String host, String username, String password)
			throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.host", host);
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");

		AuthenticatorImp auth = new AuthenticatorImp();
		auth.setUserName(username);
		auth.setPassword(password);

		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(true);
		return session;
	}

	public static MimeMessage createMessage(Session session, 
			String subject, String body, String[] files) throws Exception {

		MimeMessage msg = new MimeMessage(session);


		msg.setSubject(subject);

		MimeMultipart allPart = new MimeMultipart("mixed");

		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(body, "text/html;charset=gbk");
		allPart.addBodyPart(textBody, 0);
		if (files != null && files.length > 0) {
			for (String file : files) {
				MimeBodyPart attachmentPart = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(file);
				attachmentPart.setDataHandler(new DataHandler(fds));
				attachmentPart.setFileName(fds.getName());

				allPart.addBodyPart(attachmentPart);
			}
		}
		msg.setContent(allPart);
		msg.saveChanges();
		return msg;
	}

	public static void setFromTo(MimeMessage msg,String from, String[] to) throws Exception{
		msg.setFrom(new InternetAddress(from));
		if (to.length > 1) {
			Address tos[] = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++)
				tos[i] = new InternetAddress(to[i]);
			msg.setRecipients(javax.mail.Message.RecipientType.TO, tos);
		} else {
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					to[0]));
		}
	}
	
	public static class AuthenticatorImp extends Authenticator {

		public AuthenticatorImp() {
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		private String userName;
		private String password;
	}
}
