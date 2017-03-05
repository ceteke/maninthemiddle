import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class CryptoUtil {

	public static BigInteger rand(BigInteger start, BigInteger end){
		Random rand = new Random();
		BigInteger result;

		do{
			result = new BigInteger(end.bitCount(), rand);
		}while(result.compareTo(end) >= 0 || result.compareTo(start) < 0);
		return result;
	}

	public static BigInteger getPrime(int bitLength){
		Random rnd = new Random();
		BigInteger bi = BigInteger.probablePrime(bitLength, rnd);

		return bi;
	}

	public static byte[] encrypt(String plainText, BigInteger encryptionKey) throws UnsupportedEncodingException{
		byte[] keyBytes = encryptionKey.toByteArray();
		byte[] messageBytes = plainText.getBytes();
		byte[] cipherBytes = new byte[messageBytes.length];
		for(int i = 0; i < messageBytes.length; i++){
			cipherBytes[i] = (byte) (messageBytes[i] ^ keyBytes[i]);
		}
		return cipherBytes;
	}

	public static String decrypt(byte[] cipherText, BigInteger encryptionKey) throws UnsupportedEncodingException{
		byte[] keyBytes = encryptionKey.toByteArray();
		byte[] messageBytes = new byte[cipherText.length];
		for(int i = 0; i < messageBytes.length; i++){
			messageBytes[i] = (byte) (cipherText[i] ^ keyBytes[i]);
		}
		return new String(messageBytes, StandardCharsets.UTF_8);
	}

}
