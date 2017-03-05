import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Bob {
	public BigInteger hB;
	private BigInteger key;
	private BigInteger Y;

	public Bob(){

	}

	public void getFromAlice(CyclicGroup G, BigInteger g, BigInteger hA){
		Y = G.getRandomElement(); // Y <- Zq
		hB = G.powMod(g, Y); // hB = g ^ Y mod p
		key = G.powMod(hA, Y); // key = hA ^ Y mod p
	}

	public BigInteger getKey(){
		return key;
	}

	public void sendMessageToAlice(Alice A, String message) throws Exception{
		System.out.println("---- Bob is sending a message ----");
		System.out.println("Message: " + message);
		byte[] cipher = CryptoUtil.encrypt(message, key);
		System.out.println("Cipher: " + new String(cipher, "UTF-8"));
		A.receiveMessage(cipher);
	}
	
	public void sendMessageToAlice(Alice A, Eav E, String message) throws Exception{
		System.out.println("---- Bob is sending a message ----");
		System.out.println("Message: " + message);
		byte[] cipher = CryptoUtil.encrypt(message, key);
		System.out.println("Cipher: " + new String(cipher, "UTF-8"));
		E.getMessageFromBob(cipher, A);
	}
	
	public void receiveMessage(byte[] cipherText) throws UnsupportedEncodingException{
		System.out.println("---- Bob Received Message ----");
		System.out.println("Cipher: " + new String(cipherText, "UTF-8"));
		String message = CryptoUtil.decrypt(cipherText, key);
		System.out.println("Message: " + message);
	}

}
