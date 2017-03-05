import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Alice {
	public CyclicGroup G;
	private BigInteger X;
	public BigInteger hA;
	public BigInteger g;
	private BigInteger key;
	
	public Alice(int n){
		G = new CyclicGroup(n);
		g = G.getGenerator();
		X = G.getRandomElement(); // X <- Zq
		hA = G.powMod(g, X); // hA = g^X mod p
	}
	
	public void getFromBob(BigInteger hB){
		key = G.powMod(hB, X);
	}
	
	public BigInteger getKey(){
		return key;
	}
	
	public void receiveMessage(byte[] cipherText) throws UnsupportedEncodingException{
		System.out.println("---- Alice Received Message ----");
		System.out.println("Cipher: " + new String(cipherText, "UTF-8"));
		String message = CryptoUtil.decrypt(cipherText, key);
		System.out.println("Message: " + message);
	}
	
	public void sendMessageToBob(Bob B, String message) throws Exception{
		System.out.println("---- Alice is sending a message ----");
		System.out.println("Message: " + message);
		byte[] cipher = CryptoUtil.encrypt(message, key);
		System.out.println("Cipher: " + new String(cipher, "UTF-8"));
		B.receiveMessage(cipher);
	}
	
	public void sendMessageToBob(Bob B, Eav E, String message) throws Exception{
		System.out.println("---- Alice is sending a message ----");
		System.out.println("Message: " + message);
		byte[] cipher = CryptoUtil.encrypt(message, key);
		System.out.println("Cipher: " + new String(cipher, "UTF-8"));
		E.getMessageFromAlice(cipher, B);
	}
}
