package com.telnet.project.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.ERole;
import com.telnet.project.Entities.PasswordResetToken;
import com.telnet.project.Entities.Role;
import com.telnet.project.Entities.User;
import com.telnet.project.Repository.NotificationRepository;
import com.telnet.project.Repository.ResetPasswordTokenRepository;
import com.telnet.project.Repository.RoleRepository;
import com.telnet.project.Repository.UserRepository;
import com.telnet.project.ServiceImpl.NotifServiceImpl;
import com.telnet.project.ServiceImpl.UserServiceImpl;
import com.telnet.project.payload.request.LoginRequest;
import com.telnet.project.payload.request.SignupRequest;
import com.telnet.project.payload.response.JwtResponse;
import com.telnet.project.payload.response.MessageResponse;
import com.telnet.project.security.jwt.JwtUtils;
import com.telnet.project.security.services.UserDetailsImpl;
import com.telnet.project.security.services.UserDetailsServiceImpl;
import com.telnet.project.session.MySessionContext;
import com.telnet.project.webSocket.Notification;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/RiskManagementservice/api/pfe")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
    @Autowired
    private JavaMailSender mailSender;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	 @Autowired
	 NotificationRepository notificationRepository;
	 
	 
	@Autowired
	NotifServiceImpl notifService;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
    @Autowired
    private ResetPasswordTokenRepository resettokenrepository;
    
    
	@PostMapping("/getUserProfile")
	public User getUderProfile(@RequestBody String username) {
		User user = userRepository.findUserByUsername(username);
		return user;
	}
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
		User userNotActive=userRepository.findUserByUsername(loginRequest.getUsername());
		String sid = session.getId(); 
		Map<String, Object> map = new HashMap<String, Object>();
	
		MySessionContext myc= MySessionContext.getInstance(); 
		 session = myc.getSession(sid);  //This is the session information obtained through sessionId
		if(session!=null) {
			String user=String.valueOf(session.getAttribute("user"));
			String keySecret=String.valueOf(session.getAttribute("keySecret"));
			map.put("user", user);
			map.put("keySecret", keySecret);
		}
				
		
	
		
		if(!userNotActive.isActive()) {
			return ResponseEntity.ok("NotActive");
		}else 
		{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		System.out.println("yesLogIn");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		Set<Role> permissions = new HashSet<Role>();
		
		for(String role:roles) {
			System.out.println(role);
		permissions.add(roleRepository.findRoleByName(role));
		
		}
		User user = userRepository.findUserByUsername(userDetails.getUsername());
		//System.out.println(user.getProfilePic().getName());
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles,
												 permissions
												));}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		else {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		else {
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getName(),
							 signUpRequest.getLastName());
		System.out.println(signUpRequest.getRoles());
		user.setActive(false);
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		System.out.println(strRoles);
		if (strRoles == null) {
			System.out.println("yes");
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				System.out.println(role);
				Role foundRole = roleRepository.findRoleByName(role);
				roles.add(foundRole);
				/*switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName("ROLE_ADMIN")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
					
					
				case "mod":
					Role modRole = roleRepository.findByName("ROLE_MODERATOR")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}*/
			});
		}

		user.setRoles(roles);
		User savedUser = new User();
		savedUser=userRepository.save(user);
		
		Notification saved = new Notification();
		saved.setMessage("accusé");
		//saved.setTransactionId(notif.getTransactionId());
		User reciever=userRepository.findUserByUsername("admin");
		saved.setRecieverId(reciever.getId());
		User sender=userRepository.findUserByUsername(savedUser.getUsername());
		saved.setSenderId(sender.getId());
		saved.setSeen(false);
		 notificationRepository.save(saved);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}}}
	
	

@GetMapping("resetPassword/{email}")
public void resetPassword(HttpServletRequest request,@PathVariable String email) {
    User user = userRepository.findUserByEmail(email);
    if (user == null) {
       
    }
    String token = UUID.randomUUID().toString();
    userService.createPasswordResetTokenForUser(user, token);
    String appUrl = 
            "http://" + request.getServerName() + 
            ":" + request.getServerPort() + 
            request.getContextPath();
    mailSender.send(userService.constructResetTokenEmail(appUrl,request.getLocale(), token, user));
 
}

@GetMapping("/changePassword")
public String showChangePasswordPage(Locale locale, Model model,HttpServletResponse httpServletResponse,
@RequestParam("token") String token) throws IOException {
Boolean result = userService.validatePasswordResetToken(token);
if(result== false ) {
	  httpServletResponse.sendRedirect("http://localhost:4200/invalid-token-page"); 


return "ok";} else {
   // model.addAttribute("token", token); Map<Object, Object> model = new HashMap<>();
    Map<Object, Object> modelo = new HashMap<>();


	  httpServletResponse.sendRedirect("http://localhost:4200/auth/resetPassword/"+token); 

return"okiii";
}
}

@PostMapping("/savePassword/{token}")
public void savePassword(@PathVariable String token ,@RequestBody String password) {
 
PasswordResetToken tokenreset= resettokenrepository.findByToken(token);
User user = tokenreset.getUser();
System.out.println(tokenreset.getToken());
user.setPassword(encoder.encode(password));
userRepository.save(user);
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}