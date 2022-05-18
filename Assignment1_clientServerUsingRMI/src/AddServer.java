import java.net.*;
import java.rmi.*;

public class AddServer {
	public static void main(String[] args) {
		try {
			AddServerImpl addServerImpl = new AddServerImpl();
			Naming.rebind("AddServer", (Remote) addServerImpl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}