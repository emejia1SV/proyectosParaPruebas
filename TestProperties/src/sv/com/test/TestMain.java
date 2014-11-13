package sv.com.test;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle("test.bundletest.LAZO");
		
		System.out.println(rb.getString("url.base"));
		
		
		/*Enumeration <String> keys = rb.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = rb.getString(key);
			System.out.println(key + ": " + value);
		}*/
	}
}