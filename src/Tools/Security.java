package Tools;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Security {

	public static final int ITERATIONS = 1000;
	public static final int KEY_LENGTH = 512;

	/**
	 * Hashing Algorithm from https://www.owasp.org/index.php/Hashing_Java
	 * @param password
	 * @param salt
	 * @param iterations
	 * @param keyLength
	 * @return
	 */
	public static byte[] hashPassword(final char[] password, final byte[] salt) {
		try {
	           SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
	           PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
	           SecretKey key = skf.generateSecret(spec);
	           byte[] res = key.getEncoded();
	           return res;

	    } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
	    	throw new RuntimeException(e);
	    }
	}

	public static byte[] generateSalt() {
		return generateSalt(8);
	}

	public static byte[] generateSalt(int numBytes) {
		final Random random = new SecureRandom();
		byte[] salt = new byte[numBytes];
		random.nextBytes(salt);
		return salt;
	}
}
