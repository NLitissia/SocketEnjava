import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		OutputStream os = s.getOutputStream();
		System.out.println("j attend un nombre");
		int nb = is.read();
		int rep = nb + 3 ;
		System.out.println("j envois la repense");
		os.write(rep);
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
