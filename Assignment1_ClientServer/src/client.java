import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class client {
	private static Socket socket;

	public static void main(String[] args) {
		try {
			String host = "localhost";
			int port = 25000;
			InetAddress add = InetAddress.getByName(host);
			socket = new Socket(add, port);
//Send message to the server
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String number = "2";
			bw.write(number + "\n");
			bw.flush();
			System.out.println("Message sent to the server is " + number + "\n");
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = br.readLine();
			System.out.println("Message received from the server " + msg);
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
