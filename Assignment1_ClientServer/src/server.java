import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	private static Socket socket;

	public static void main(String[] args) {
		try {
			int port = 25000;
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server started and listening");
			System.out.println("Client may send the number to receive its double");
			while (true) {
// Reading message from the client
				socket = serverSocket.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String number = br.readLine();
				System.out.println(
						"Message received from the client " + socket.getRemoteSocketAddress() + " is " + number);
				String returnMessage;
				try {
					int num = Integer.parseInt(number);
					int retval = num * 2;
					returnMessage = String.valueOf(retval) + "\n";
				} catch (NumberFormatException e) {
					returnMessage = "Input incorrect. Please try again";
				}
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				bw.write(returnMessage);
				System.out.println("Message sent to the client is " + returnMessage);
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
