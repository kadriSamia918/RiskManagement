package com.telnet.project.security.services;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telnet.project.Entities.PasswordResetToken;
import com.telnet.project.Entities.User;
import com.telnet.project.Repository.ResetPasswordTokenRepository;
import com.telnet.project.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired   ResetPasswordTokenRepository resetpasswordRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	
	
	public void createPasswordResetTokenForUser(User user, String token) {
	    PasswordResetToken myToken = new PasswordResetToken(token, user);
	    resetpasswordRepository.save(myToken);
	}
	
	public SimpleMailMessage constructResetTokenEmail(
			  String contextPath, Locale locale, String token, User user) {
			    String url = contextPath + "/api/pfe/changePassword?token=" + token;

			    return constructEmail("Reset Password", url, user);
			}
			 
			private SimpleMailMessage constructEmail(String subject, String body, 
			  User user) {
			    SimpleMailMessage email = new SimpleMailMessage();
			    email.setSubject(subject);
			    email.setText(body);
			    email.setTo("sanalaadhar6@gmail.com");
		
			    return email;
			}
			public Boolean validatePasswordResetToken(String token) {
			    final Calendar cal = Calendar.getInstance();

				final PasswordResetToken passToken = resetpasswordRepository.findByToken(token);
			 if (passToken!=null) {return true;}
			  else return false;
			}
			 
			private boolean isTokenFound(PasswordResetToken passToken) {
			    return passToken != null;
			}
			 
			private boolean isTokenExpired(PasswordResetToken passToken) {
			    final Calendar cal = Calendar.getInstance();
			    return passToken.getExpiryDate().before(cal.getTime());
			}
	

}