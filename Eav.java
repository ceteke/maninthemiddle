import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Eav {
	private BigInteger Z;
	private BigInteger keyA;
	private BigInteger keyB;
	private BigInteger hC;
	
	public Eav(){

	}
	
	public void eavsdrop(Alice A, Bob B){
		Z = A.G.getRandomElement();
		hC = A.G.powMod(A.g, Z);
		keyA = A.G.powMod(A.hA, Z);
		B.getFromAlice(A.G, A.g, hC);
		keyB = A.G.powMod(B.hB, Z);
		A.getFromBob(hC);
	}
	
	public BigInteger getKeyA(){
		return keyA;
	}
	
	public BigInteger getKeyB(){
		return keyB;
	}
	
	public void getMessageFromAlice(byte[] cipher, Bob B) throws UnsupportedEncodingException{
		System.out.println("---- EAV Got Message From Alice ----");
		System.out.println("Cipher Alice: " + new String(cipher, "UTF-8"));
		String message = CryptoUtil.decrypt(cipher, keyA);
		System.out.println("Message: " + message);
		byte[] cipherB = CryptoUtil.encrypt(message, keyB);
		System.out.println("Cipher Bob: " + new String(cipherB, "UTF-8"));
		B.receiveMessage(cipherB);
	}
	
	public void getMessageFromBob(byte[] cipher, Alice A) throws UnsupportedEncodingException{
		System.out.println("---- EAV Got Message From Bob ----");
		System.out.println("Cipher Bob: " + new String(cipher, "UTF-8"));
		String message = CryptoUtil.decrypt(cipher, keyB);
		System.out.println("Message: " + message);
		byte[] cipherA = CryptoUtil.encrypt(message, keyA);
		System.out.println("Cipher Alice: " + new String(cipherA, "UTF-8"));
		A.receiveMessage(cipherA);
	}
	
}
