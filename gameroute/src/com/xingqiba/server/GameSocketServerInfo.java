package com.xingqiba.server;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class GameSocketServerInfo {
	public String ip;
	public int port;
	public int status = 0;
	
	public GameSocketServerInfo(String ip, int port, int status) {
		this.ip = ip;
		this.port = port;
		this.status = status;
	}
}
