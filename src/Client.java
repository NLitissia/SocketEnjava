import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		
			Socket s = new Socket("localhost",1234);
			//l'objet socket génere possede getInputStream() pour lire la donnée envoyé par le client
			//et getOutputStream();  pour envoyé la repense
		
			OutputStream os = s.getOutputStream();
			System.out.println("Ecrivez votre message");
			PrintWriter pw = new PrintWriter(os,true); 
			Scanner e = new Scanner(System.in);
			System.out.println("envoyer votre message");
			String st = e.next();
			pw.write(st);
			pw.flush();
			//System.out.println(" donner un nombre  : ");
		   // int nb = e.nextInt();
		    //le client doit ecrire , car le serveur attend 
		    //os.write(nb);
		    // recevoir la repense du serveur
		    //int rep = is.read();
			InputStream is = s.getInputStream();
		    
		    
			
		
		
	}

}
