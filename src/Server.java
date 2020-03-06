import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	static ExecutorService executor = Executors.newFixedThreadPool(10);

	public static void main(String[] args) {

		ServerSocket serverSocket;
		BufferedReader br;
		int count = 0;

		try {

			serverSocket = new ServerSocket(8000);
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Server start");

			while (!serverSocket.isClosed()) {

				if (br.ready()) {

					String message = br.readLine();
					if (message.equalsIgnoreCase("quit")) {
						System.out.println();
						serverSocket.close();
						break;
					}
				}

				Socket client = serverSocket.accept();
				executor.execute(new ClientThread(client));
				System.out.println("Connection № " + (++count) + " accepted");

			}

			executor.shutdown();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
