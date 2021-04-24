package com.telnet.project.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.User;
import com.telnet.project.Repository.UserRepository;

@Service
public class MailServiceImpl {
	private JavaMailSender javaMailsender;
	 @Autowired
	 private UserRepository userrepository;
	@Autowired
	public MailServiceImpl(JavaMailSender javaMailsender) {
		this.javaMailsender = javaMailsender;
	}



	
	public void SendNotification(String idFrom,String idTo,String text) throws MailException {
		User from=  userrepository.findUserByUsername(idFrom);
		User to=  userrepository.findUserByUsername(idTo);

		try {
			   MimeMessagePreparator preparator = (mimeMessage) -> {
			        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			        helper.setFrom(new InternetAddress(from.getEmail(), from.getUsername()));
			        helper.setTo(to.getEmail());
			       // helper.setCc(from.getEmail());
			        helper.setSubject("Gestion de risque: Plan d' Action: ");
			        helper.setText(text, true);
			    };          
			    javaMailsender.send(preparator);
			  } catch (MailException exception) {
			    throw exception;
			}
	}
	public void SendNotificationWithCC(String idFrom,String idTo,String text) throws MailException {
		User from=  userrepository.findUserByUsername(idFrom);
		User to=  userrepository.findUserByUsername(idTo);
		 String textBody ="Bonjour Mr/Mme \n"+ idTo +","
					 ;

		try {   
			   MimeMessagePreparator preparator = (mimeMessage) -> {
			        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			        helper.setFrom(new InternetAddress(from.getEmail(), from.getUsername()));
			        helper.setTo(to.getEmail());
			        helper.setCc(from.getEmail());
			        helper.setSubject("Gestion de risque: Plan d' Action: ");
			       helper.setText("<html>"+"<body>"+
			        		"<table class=\"body-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0;\" bgcolor=\"#f6f6f6\">"
			        	 +  " <tbody>"+
			        	        "<tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">"+
			        	          "  <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>"+
			        	           " <td class=\"container\" width=\"600\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; display: block !important; max-width: 600px !important; clear: both !important; margin: 0 auto;\""
			        	              +"  valign=\"top\">"+
			        	               " <div class=\"content\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; max-width: 600px; display: block; margin: 0 auto; padding: 20px;\">"+
			        	                   " <table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0; border: 1px solid #e9e9e9;\""
			        	                      +"bgcolor=\"#fff\">"+"<tbody>"+" <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">"+"<td class=\"\" style=\"font-family: 'Helvetica Neue','Helvetica','Arial','sans-serif'; box-sizing: \"border-box\"; font-size: 16px; vertical-align: \"top\"; color: #fff; font-weight: 500; text-align: \"center\"; border-radius: 3px 3px 0 0; background-color: #331acc" + 
			        	                      		"; margin: 0; padding: 20px;" + 
			        	                      		"	        	                                    align=\"center\" bgcolor=\"#331acc" + 
			        	                      		"\" valign=\"top\">"+" <p style=\"font-size:32px;color:#fff;\">"+"                Système de gestion des risques Telnet"+"</p> <br>\n" + 
			        	                      				"	        	                                </td>\n" + 
			        	                      				"	        	                            </tr>\n" + 
			        	                      				"	        	                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                <td class=\"content-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                        <tbody>" + 
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                                \n <strong style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      			        	                          
			        	                      				"	        	                                                </td>" + 
			        	                      				"	        	                                            </tr>\n" +           " <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
					        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + textBody+"<br>"+text + 
					        	                      				"	        	                                                </td>" + 
					        	                      				"	        	                                            </tr>" + 
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                                  Connectez vous pour Plus de détails" + 
			        	                      				"	        	                                                </td>" + 
			        	                      				"	        	                                            </tr>" + 
			        	                      			
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                                    <a href=\"http://localhost:4200/auth/login\" class=\"btn-primary\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; color: #FFF; text-decoration: none; line-height: 2em; font-weight: bold; text-align: center; cursor: pointer; display: inline-block; border-radius: 5px; text-transform: capitalize; background-color: #4BB543; margin: 0; border-color: #4BB543; border-style: solid; border-width: 8px 16px;\">Se Connecter " + 
			        	                      				"	        	                                  </a>" + 
			        	                      				"	        	                                                </td>" + 
			        	                      				"	        	                                            </tr>\n" + 
			        	                      			       	                                    
			        	                      				"	        	                                        </tbody>\n" + 
			        	                      				"	        	                                    </table>\n" + 
			        	                      				"	        	                                </td>\n" + 
			        	                      				"	        	                            </tr>\n" + 
			        	                      				"	        	                        </tbody>\n" + 
			        	                      				"	        	                    </table>\n" + 
			        	                      				"	        	                    <div class=\"footer\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0; padding: 20px;\">" + 
			        	                      				"	        	                        <table width=\"100%\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                            <tbody>\n" + 
			        	                      				"	        	                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" + 
			        	                      				"	        	                                    </td>\n" + 
			        	                      				"	        	                                </tr>\n" + 
			        	                      				"	        	                            </tbody>\n" + 
			        	                      				"	        	                        </table>\n" + 
			        	                      				"	        	                    </div>\n" + 
			        	                      				"	        	                </div>\n" + 
			        	                      				"	        	            </td>\n" + 
			        	                      				"	        	            <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>\n" + 
			        	                      				"	        	        </tr>\n" + 
			        	                      				"	        	    </tbody>\n" + 
			        	                      				"	        	</table>\n" + 
			        	                      				"\n" + 
			        	                      				"	        	</body>\n" + 
			        	                      				"\n" + 
			        	                      				"	        	</html>\n" + 
			        	                      				"\"\n"
			   	    				   ,true);
			        
			    };          
			    javaMailsender.send(preparator);
			  } catch (MailException exception) {
			    throw exception;
			}
	}
	public void SendEmailAfterRegistrationWithCC(String idTo,String userName) throws MailException {
		///User from=  userrepository.findUserByUsername(idFrom);
	///	User to=  userrepository.findUserByUsername(idTo);
 String text ="Bonjour Mr/Mme"+ userName+"Votre demande d'inscription est envoyée avec succée "
 		+ "nous allons vous envoyer un Email de confirmation dans les brefs délais."
		 ;
		try {
			   MimeMessagePreparator preparator = (mimeMessage) -> {
			        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			        helper.setFrom(new InternetAddress("sanaladhar96@gmail.com", "riskManagement"));
			        helper.setTo(idTo);
			        helper.setCc("sanalaadhar6@gmail.com");
			        helper.setSubject("Accusé de réception RiskManagement ");
			        helper.setText("<html>"+"<body>"+
			        		"<table class=\"body-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0;\" bgcolor=\"#f6f6f6\">"
			        	 +  " <tbody>"+
			        	        "<tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">"+
			        	          "  <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>"+
			        	           " <td class=\"container\" width=\"600\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; display: block !important; max-width: 600px !important; clear: both !important; margin: 0 auto;\""
			        	              +"  valign=\"top\">"+
			        	               " <div class=\"content\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; max-width: 600px; display: block; margin: 0 auto; padding: 20px;\">"+
			        	                   " <table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0; border: 1px solid #e9e9e9;\""
			        	                      +"bgcolor=\"#fff\">"+"<tbody>"+" <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">"+"<td class=\"\" style=\"font-family: 'Helvetica Neue','Helvetica','Arial','sans-serif'; box-sizing: \"border-box\"; font-size: 16px; vertical-align: \"top\"; color: #fff; font-weight: 500; text-align: \"center\"; border-radius: 3px 3px 0 0; background-color: #331acc" + 
			        	                      		"; margin: 0; padding: 20px;" + 
			        	                      		"	        	                                    align=\"center\" bgcolor=\"#331acc" + 
			        	                      		"\" valign=\"top\">"+" <p style=\"font-size:32px;color:#fff;\">"+"                Système de gestion des risques Telnet"+"</p> <br>\n" + 
			        	                      				"	        	                                </td>\n" + 
			        	                      				"	        	                            </tr>\n" + 
			        	                      				"	        	                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                <td class=\"content-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                        <tbody>" + 
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                                  \n <strong style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      			        	                          
			        	                      				"	        	                                                </td>" + "<img src=\\\"cid:image1\\\" width=\\\"30%\\\" height=\\\"30%\\\" /><br>"+
			        	                      				"	        	                                            </tr>\n" +           " <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
					        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + text + 
					        	                      				"	        	                                                </td>" + 
					        	                      				"	        	                                            </tr>" + 
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                                 Equipe SSI Telnet" + 
			        	                      				"	        	                                                </td>" + 
			        	                      				"	        	                                            </tr>" + 
			        	                      			
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      		
			        	                      				"	        	                                                </td>" + 
			        	                      				"	        	                                            </tr>\n" + 
			        	                      			       	                                    
			        	                      				"	        	                                        </tbody>\n" + 
			        	                      				"	        	                                    </table>\n" + 
			        	                      				"	        	                                </td>\n" + 
			        	                      				"	        	                            </tr>\n" + 
			        	                      				"	        	                        </tbody>\n" + 
			        	                      				"	        	                    </table>\n" + 
			        	                      				"	        	                    <div class=\"footer\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0; padding: 20px;\">" + 
			        	                      				"	        	                        <table width=\"100%\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                            <tbody>\n" + 
			        	                      				"	        	                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" + 
			        	                      				"	        	                                    </td>\n" + 
			        	                      				"	        	                                </tr>\n" + 
			        	                      				"	        	                            </tbody>\n" + 
			        	                      				"	        	                        </table>\n" + 
			        	                      				"	        	                    </div>\n" + 
			        	                      				"	        	                </div>\n" + 
			        	                      				"	        	            </td>\n" + 
			        	                      				"	        	            <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>\n" + 
			        	                      				"	        	        </tr>\n" + 
			        	                      				"	        	    </tbody>\n" + 
			        	                      				"	        	</table>\n" + 
			        	                      				"\n" + 
			        	                      				"	        	</body>\n" + 
			        	                      				"\n" + 
			        	                      				"	        	</html>\n" + 
			        	                      				"\"\n"
			   	    				   ,true);
			    };             
			    Map<String, String> inlineImages = new HashMap<String, String>();
		        inlineImages.put("image1", "D:/image/TelnetLogo.jpeg");
		 
			    javaMailsender.send(preparator);
			  } catch (MailException exception) {
			    throw exception;
			}
	}
	public void SendEmailAfterConfirmAccountWithCC(String idTo,String userName) throws MailException {
		///User from=  userrepository.findUserByUsername(idFrom);
	///	User to=  userrepository.findUserByUsername(idTo);
 String text ="Bonjour Mr/Mme"+ userName+"Votre compte est validée "
 		+ "vous pouvez se connecter en cliquant sur le button ci dessous."
		 ;
		try {
			   MimeMessagePreparator preparator = (mimeMessage) -> {
			        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			        helper.setFrom(new InternetAddress("sanaladhar96@gmail.com", "riskManagement"));
			        helper.setTo(idTo);
			        helper.setCc("sanalaadhar6@gmail.com");
			        helper.setSubject("Accusé de réception RiskManagement ");
			        helper.setText("<html>"+"<body>"+
			        		"<table class=\"body-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0;\" bgcolor=\"#f6f6f6\">"
			        	 +  " <tbody>"+
			        	        "<tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">"+
			        	          "  <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>"+
			        	           " <td class=\"container\" width=\"600\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; display: block !important; max-width: 600px !important; clear: both !important; margin: 0 auto;\""
			        	              +"  valign=\"top\">"+
			        	               " <div class=\"content\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; max-width: 600px; display: block; margin: 0 auto; padding: 20px;\">"+
			        	                   " <table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0; border: 1px solid #e9e9e9;\""
			        	                      +"bgcolor=\"#fff\">"+"<tbody>"+" <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">"+"<td class=\"\" style=\"font-family: 'Helvetica Neue','Helvetica','Arial','sans-serif'; box-sizing: \"border-box\"; font-size: 16px; vertical-align: \"top\"; color: #fff; font-weight: 500; text-align: \"center\"; border-radius: 3px 3px 0 0; background-color: #331acc" + 
			        	                      		"; margin: 0; padding: 20px;" + 
			        	                      		"	        	                                    align=\"center\" bgcolor=\"#331acc" + 
			        	                      		"\" valign=\"top\">"+" <p style=\"font-size:32px;color:#fff;\">"+"                Système de gestion des risques Telnet"+"</p> <br>\n" + 
			        	                      				"	        	                                </td>\n" + 
			        	                      				"	        	                            </tr>\n" + 
			        	                      				"	        	                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                <td class=\"content-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                        <tbody>" + 
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                                  \n <strong style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      			        	                          
			        	                      				"	        	                                                </td>" + "<img src=\\\"cid:image1\\\" width=\\\"30%\\\" height=\\\"30%\\\" /><br>"+
			        	                      				"	        	                                            </tr>\n" +           " <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
					        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + text + 
					        	                      				"	        	                                                </td>" + 
					        	                      				"	        	                                            </tr>" + 
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      				"	        	                                                 Equipe SSI Telnet" + 
			        	                      				"	        	                                                </td>" + 
			        	                      				"	        	                                            </tr>" + 
			        	                      			
			        	                      				"	        	                                            <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                                                <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">" + 
			        	                      				 "<a href=\"http://localhost:4200/auth/login\" class=\"btn-primary\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; color: #FFF; text-decoration: none; line-height: 2em; font-weight: bold; text-align: center; cursor: pointer; display: inline-block; border-radius: 5px; text-transform: capitalize; background-color: #4BB543; margin: 0; border-color: #4BB543; border-style: solid; border-width: 8px 16px;\">Se Connecter " + 
						        	                      				"	        	                                  </a>" + 
			        	                      				"	        	                                                </td>" + 
			        	                      				"	        	                                            </tr>\n" + 
			        	                      			       	                                    
			        	                      				"	        	                                        </tbody>\n" + 
			        	                      				"	        	                                    </table>\n" + 
			        	                      				"	        	                                </td>\n" + 
			        	                      				"	        	                            </tr>\n" + 
			        	                      				"	        	                        </tbody>\n" + 
			        	                      				"	        	                    </table>\n" + 
			        	                      				"	        	                    <div class=\"footer\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0; padding: 20px;\">" + 
			        	                      				"	        	                        <table width=\"100%\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">" + 
			        	                      				"	        	                            <tbody>\n" + 
			        	                      				"	        	                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" + 
			        	                      				"	        	                                    </td>\n" + 
			        	                      				"	        	                                </tr>\n" + 
			        	                      				"	        	                            </tbody>\n" + 
			        	                      				"	        	                        </table>\n" + 
			        	                      				"	        	                    </div>\n" + 
			        	                      				"	        	                </div>\n" + 
			        	                      				"	        	            </td>\n" + 
			        	                      				"	        	            <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>\n" + 
			        	                      				"	        	        </tr>\n" + 
			        	                      				"	        	    </tbody>\n" + 
			        	                      				"	        	</table>\n" + 
			        	                      				"\n" + 
			        	                      				"	        	</body>\n" + 
			        	                      				"\n" + 
			        	                      				"	        	</html>\n" + 
			        	                      				"\"\n"
			   	    				   ,true);
			    };             
			    Map<String, String> inlineImages = new HashMap<String, String>();
		        inlineImages.put("image1", "D:/image/TelnetLogo.jpeg");
		 
			    javaMailsender.send(preparator);
			  } catch (MailException exception) {
			    throw exception;
			}
	}
public void SendEmailAfterRegistration(String idFrom,String idTo,String text) throws MailException {
	User from=  userrepository.findUserByUsername(idFrom);
	User to=  userrepository.findUserByUsername(idTo);

	try {
		   MimeMessagePreparator preparator = (mimeMessage) -> {
		        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		        helper.setFrom(new InternetAddress(from.getEmail(), from.getUsername()));
		        helper.setTo(to.getEmail());
		        helper.setCc(from.getEmail());
		        helper.setSubject("Gestion de risque: Plan d' Action: ");
		        helper.setText(text, true);
		    };          
		    javaMailsender.send(preparator);
		  } catch (MailException exception) {
		    throw exception;
		}
}
}
