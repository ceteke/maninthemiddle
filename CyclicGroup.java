//Reference: http://crypto.stackexchange.com/questions/22716/generation-of-a-cyclic-group-of-prime-order

import java.math.BigInteger;
import java.util.ArrayList;

public class CyclicGroup {
    private static final int EQUAL = 0;
    private static final BigInteger TWO = new BigInteger("2");

    private BigInteger p, g;

    public CyclicGroup(int bitLength) {
        init(bitLength);
    }

    private void init(int bitLength) {
        BigInteger q = BigInteger.ZERO;

        while (true) {
            q = CryptoUtil.getPrime(bitLength);
            
            p = (q.multiply(TWO)).add(BigInteger.ONE); // p = 2q+1

            if (!p.isProbablePrime(40)) {
                continue;
            }

            while (true) {
                g = CryptoUtil.rand(TWO, p.subtract(BigInteger.ONE));

                BigInteger exp = (p.subtract(BigInteger.ONE)).divide(q);

                if (g.modPow(exp, p).compareTo(BigInteger.ONE) != EQUAL) {
                    break;
                }
            }

            break;
        }
    }

    public BigInteger getRandomElement() {      
        return g.modPow(CryptoUtil.rand(BigInteger.ONE, p), p);
    }

    public ArrayList<BigInteger> getElements() {
        // This method is horribly ineffective for large groups and should not be used

        ArrayList<BigInteger> elements = new ArrayList<BigInteger>();

        BigInteger index = BigInteger.ONE;
        BigInteger element = BigInteger.ZERO;

        while (element.compareTo(BigInteger.ONE) != EQUAL) {
            element = g.modPow(index, p);
            elements.add(element);

            index = index.add(BigInteger.ONE); // index++
        }

        return elements;
    }

    public BigInteger getModulus() {
        return p;
    }

    public BigInteger getGenerator() {
        return g;
    }
    
    //I added the below code
    public BigInteger powMod(BigInteger base, BigInteger exp){
    	BigInteger result = base.modPow(exp, this.p);
    	return result;
    }
}