import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
public class Server1 {
	ServerSocketChannel ssc; //la socket ou le canal
	public Server1() throws IOException // notre constructeur 
	{
		//creation de la socket et configurer le type de communication et le port
		ssc = ServerSocketChannel.open();/*server*/
		SocketAddress sa = new InetSocketAddress(2020);
		ssc.bind(sa);/*la connexion listen il est fait implicitement */
		
	}
 public void run() throws IOException
 {  //accepte la connexion 
	SocketChannel sc =  ssc.accept();
	/*allocatedirecte tres efficace alors que  allocate rapide mais utilisation coute chére */
	ByteBuffer bb = ByteBuffer.allocateDirect(512);
	sc.read(bb);
	bb.flip();/*prépare un tampon pour une nouvelle séquence d' opérations d' écriture de canal ou d' obtention relative 
	définit la limite à la position actuelle, puis la position à zéro.*/
	Charset c = Charset.forName("UTF-8");/*faut avoir l encodage du client comme */
	CharBuffer cb  = c.decode(bb);
	System.out.print(cb.toString());
	bb.clear();/*prépare un tampon pour une nouvelle séquence d'opérations de lecture de canal ou d' opérations de vente relatives 
	définit la limite de capacité et la position à zéro*/
 }
 public static void main(String[] args) throws IOException
 {  System.out.println("avant accepte");
	 Server1 s = new Server1();
	 System.out.println("aprés accepte");
	 s.run();
	 
 }
}
