import java.io.*;
import java.net.*;

public class ClientThread implements Runnable {

	private static Socket clientSocket;

	public ClientThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {

		try {
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());

			while (!clientSocket.isClosed()) {

				String message = in.readUTF();

				if (message.equalsIgnoreCase("quit")) {
					out.writeUTF(message + " - OK");
					out.flush();
					break;
				}

				out.writeUTF("I nothing do");
				out.flush();

			}

			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
