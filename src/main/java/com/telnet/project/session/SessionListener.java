package com.telnet.project.session;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements  HttpSessionListener{
	 private MySessionContext myc = MySessionContext.getInstance();  
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();  
        myc.addSession(session); 
       
        session.setMaxInactiveInterval(3);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		 HttpSession session = se.getSession();  
         myc.delSession(session);  //This is used to remove your session object from the map
	}
	
}