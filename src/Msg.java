import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Msg {
	int n;
	String message;
	Msg follow;
	public Msg(int n , String m, Msg follow)
	{
		this.n=n;
		this.message= m;	
		this.follow = follow;
	}
 
	final static Charset c = Charset.forName("UTF-8");
	final static Charset c2 = Charset.forName("ASCII");
	public void serialize(ByteBuffer bb)
	{
		bb.putInt(n);
		ByteBuffer cb = c.encode(message);
		bb.putInt(cb.remaining());
		bb.put(cb);
		if(follow != null)
		{   bb.put((byte)1);
			follow.serialize(bb);
		}   
		else
		{
			bb.put((byte)0);
		}
	}
	public String toString (Msg m)
	{
		//'System.out.println(arg0);
		return message;
		
	}
	public static Msg deserialization(ByteBuffer bb)
	{
		int n =bb.getInt();
		int t =bb.getInt();
		int lim = bb.limit();
		bb.limit(bb.position()+t);
		String m =c.decode(bb).toString();
		bb.limit(lim);
		Msg f = null;
		if(bb.get()!= (byte)0)
		{
			f = deserialization(bb);
		}
		return new Msg(n,m,f);
		
		
	}
	
	public static void main(String argc[])
	{
//		Msg m1 =new Msg(42,"titi",new Msg(32,"test",null));
//		Msg m2 =new Msg(42,"titi",new Msg(32,"test",null));
	ByteBuffer bb = ByteBuffer.allocate(512);
//		m1.serialize(bb);
//		m2.serialize(bb);
//		
//		bb.flip();
//		
//		Msg m3 = deserialization(bb);
//		Msg m4 = deserialization(bb);
//		//System.out.println(m3.toString());
		bb.clear();//c pour la lim et la pos
	    bb = encode("titi");
		//System.out.println(b.toString());
	   System.out.println(bb.toString());	
		for(byte b : bb.array())
		{
			System.out.print("'"+b+"'");
		}
		
	}
	public static ByteBuffer encode(String s)
	{
		ByteBuffer bb = ByteBuffer.allocate(8);
		ByteBuffer b2 = c2.encode(s);
		int n = bb.remaining();
		bb.put(c2.encode(Integer.toString(n)));
		bb.putChar(':');
		bb.put(b2);
		
		return bb;
		
	}
	
	
}
