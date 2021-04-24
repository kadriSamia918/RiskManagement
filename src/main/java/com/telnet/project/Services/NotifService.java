package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.webSocket.Notification;

public interface NotifService {
	List<Notification> findAll();

}
