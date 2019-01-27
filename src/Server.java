import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
public class Server {
	public static void main(String[] args) throws IOException 
	{
		ServerSocket ss = new ServerSocket(1234);
		//1234 est le numeros du port
		System.out.println("j'attend la connexion d'un client");
		//le serveur attend qu un client se connect
		//quand un client se connect il y a automatiquement une géneration d'une socket
		Socket s = ss.accept();
		//l'objet socket génere possede getInputStream() pour lire la donnée envoyé par le client
		//et getOutputStream();  pour envoyé la repense
		InputStream is = s.getInputStream();
		//InputStreamReader c pour lire une chaine de cararctére
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br =  new BufferedReader(isr);
		System.out.println("j'attens une chaine de caractére");
		String msg = br.readLine();
		while(msg != null)
		{
		System.out.println("message reçu est  : " + msg);
		 msg = br.readLine();
		}
		OutputStream os = s.getOutputStream();
		System.out.println("j envois la repense");
		PrintWriter pw = new PrintWriter(os);
		pw.write("c'est la repense");
		pw.flush();
//		System.out.println("j attend un nombre");
		// quand on utilise  la methode read on attend 4 octet se qui veut dire 4 caractére
//		int nb = is.read();
//		quand c que avec inputstream (recevoir un entier)
//		int rep = nb + 3 ;
		
		//os.write(rep);
		//fermer la connexion
		s.close();
		
		}
	
}

//	ServerSocketChannel ssc;
//	//Selector selector;
//	public Server() throws IOException
//	{
//		ssc = ServerSocketChannel.open();/*server*/
//		SocketAddress sa = new InetSocketAddress(2023);
//		ssc.bind(sa);/* etablir la connexion listen il est fait implicitement */
//		//ssc.configureBlocking(true);
//		//ssc.register(selector,SelectionKey.OP_ACCEPT);
//		
//	}
//
// public void run() throws IOException
// {
//	SocketChannel sc =  ssc.accept();
//	/*allocatedirecte tres efficace , allocate rapide mais utilisation coute chére */
//	while(true) {/*permet de lire tous */
//	ByteBuffer bb = ByteBuffer.allocateDirect(512);
//	sc.read(bb);
//	bb.flip();
//	Charset c = Charset.forName("UTF-8");/*faut avoir l encodage du client comme */
//	CharBuffer cb  = c.decode(bb);
//	System.out.print(cb.toString());
//	bb.clear();
//	}
// }
// public static void main(String[] args) throws IOException
// {  System.out.print("avant accepte");
//	 Server s = new Server();
//	 System.out.print("aprés accepte");
//	 s.run();
//	 
// }
//}
////public void run2()throws IOException
////{
////	while(true)
////	{
////		selector.select();
////		for(SelectionKey sk :selector.selectedKeys())
////		{
////			ServerSocketChannel scc2 = (ServerSocketChannel)sk.channel();
////			if()
////			{
////				
////			}
////			SocketChannel sc = scc2.accept();
////			sc.configureBlocking(false);
////			sc.register(selector,SelectionKey.OP_READ);
////		 
////		
////		else
////		{
////			
////		}
////	}}
//}
