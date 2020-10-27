package com.panjohnny.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.panjohnny.client.ServerClient;

public class Server {
	public boolean acceptingClients =true;
	public List<ServerClient> clients = new ArrayList<ServerClient>();
	public static void main(String[] args) {
		Server s = new Server();
		s.start();
	}

	public void start() {
		try {
			ServerSocket ss = new ServerSocket(8756);
			while(acceptingClients) {
				Socket socket = ss.accept();
				ServerClient client = new ServerClient(socket, this);
				System.out.println("accepted conection from: "+socket.toString());
				clients.add(client);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
