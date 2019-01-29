import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
public class ServerSelector {
	ServerSocketChannel ssc; //la socket ou le canal
	Selector selector ;//le selecteur
	public ServerSelector() throws IOException // notre constructeur 
	{
		//creation de la socket et configurer le type de communication et le port
		ssc = ServerSocketChannel.open();/*server*/
		selector =  Selector.open();//creation du selecteur 
		ssc.configureBlocking(false);//il faudra que sa soit non bloquante 
		SocketAddress sa = new InetSocketAddress(1921);
		ssc.bind(sa);/*la connexion listen il est fait implicitement */
		ssc.register(selector,SelectionKey.OP_ACCEPT);
	}
 public void run() throws IOException
 { 
	 ByteBuffer bb = ByteBuffer.allocateDirect(512); 
	 while(true) {
			selector.select();//ne pas oublie sinon n'affiche rien
			for(SelectionKey sk : selector.selectedKeys()) {
				if(sk.isAcceptable()) {
					ServerSocketChannel ssc2 = (ServerSocketChannel)sk.channel();
					SocketChannel sc = ssc2.accept();
					sc.configureBlocking(false);
					sc.register(selector,SelectionKey.OP_READ);
				} else {
					SocketChannel sc = (SocketChannel)sk.channel();
					sc.read(bb);
					bb.flip();
					Charset c = Charset.forName("UTF-8");
					CharBuffer cb = c.decode(bb);
					System.out.println(cb.toString());
					bb.clear();
				}
			}
			selector.selectedKeys().clear();
		}
	}

 public static void main(String[] args) throws IOException
 {  System.out.println("avant accepte");
     ServerSelector  s = new ServerSelector ();
	 System.out.println("aprés accepte");
	 s.run();
	 
 }

}
