import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		
			Socket s = new Socket("localhost",1234);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			Scanner e = new Scanner(System.in);
			System.out.println(" donner un nombre  : ");
		    int nb = e.nextInt();
		    //le client doit ecrire , car le serveur attend 
		    os.write(nb);
		    // recevoir la repense du serveur
		    int rep = is.read();
		    System.out.println("Resultat = "+rep);
		    
			
		
		
	}

}
