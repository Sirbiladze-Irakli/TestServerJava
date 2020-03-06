import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTester implements Runnable {

	private Socket client;
	private int num;

	public ClientTester(int num) {

		try {
			this.num = num;
			client = new Socket("localhost", 8000);
			System.out.println("Client № " + num + " connected to socket");
			Thread.sleep(1000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		try {

			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			DataInputStream in = new DataInputStream((client.getInputStream()));
			System.out.println("Client № " + num + " out & in initialized");

			for (int i = 0; i < 5; i++) {

				out.writeUTF("Client № " + num + " send request");
				out.flush();

				Thread.sleep(10);
				System.out.println("Client № " + num + " wrote & start waiting for data from server...");
				String message = in.readUTF();
				Thread.sleep(2000);
			}


		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
