
package prechtl.psi.insekta.utils;

import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author Mike Prechtl
 */
public class SecureRandomFactory {

    private static final int STD_NIBBLE_LENGTH = 32;

    private static final int NUM_SEED_BYTES = 32;

    private static final SecureRandom SECURE_RANDOM;

    private static int counter;

    static {
	SECURE_RANDOM = new SecureRandom();
	SECURE_RANDOM.setSeed(SECURE_RANDOM.generateSeed(NUM_SEED_BYTES));
	counter = 0;
    }

    public static String generateToken() {
	return generateToken(STD_NIBBLE_LENGTH);
    }

    public static String generateToken(int nibbleLength) {
	return DatatypeConverter.printHexBinary(generateRandom(nibbleLength));
    }

    private static byte[] generateRandom(int nibbleLength) {
	if (nibbleLength < 1) {
	    return null;
	}

	nibbleLength = (nibbleLength / 2 + nibbleLength % 2);

	byte[] randomBytes = new byte[nibbleLength];
	reseed();
	SECURE_RANDOM.nextBytes(randomBytes);

	return randomBytes;
    }

    /**
     * The random instance is reseeded with a counter and the current system time in order to provide better random numbers.
     */
    private static void reseed() {
	counter++;
	SECURE_RANDOM.setSeed(counter);
	SECURE_RANDOM.setSeed(System.nanoTime());
    }

}
