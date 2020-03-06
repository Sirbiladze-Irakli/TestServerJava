import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(10);

		try {

			for (int i = 0; i < 10; i++) {
				executor.execute(new ClientTester(i+1));
				Thread.sleep(10);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();

	}

}
