package sv.avantiac.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestEncrypt {

	private static final char[] CONSTS_HEX = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	public static void main(String[] args) {
		try {
			String cadenaParaEncriptar = "prueba";
			MessageDigest msgd = MessageDigest.getInstance("MD5");
			byte[] bytes = msgd.digest(cadenaParaEncriptar.getBytes());
			StringBuilder stb = new StringBuilder(2*bytes.length);
			for (int i = 0; i < bytes.length; i++) {
				int bajo;
				int alto;
				
					
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
