package sv.avantia.proyecto.prueba.log4j.test;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import sv.avantia.proyecto.prueba.log4j.model.Log4jInit;

public class TestMainLog4j {

	/* Get actual class name to be printed on */
	public static Logger logger = Logger.getLogger("avantiaLogger");
	
	static{
		Log4jInit.init();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   Logger barlogger = Logger.getLogger("com.foo.Bar");

		   // This request is enabled, because WARN >= INFO.
		   logger.warn("Low fuel level.");

		   // This request is disabled, because DEBUG < INFO.
		   logger.debug("Starting search for nearest gas station.");

		   // The logger instance barlogger, named "com.foo.Bar",
		   // will inherit its level from the logger named
		   // "com.foo" Thus, the following request is enabled
		   // because INFO >= INFO.
		   barlogger.info("Located nearest gas station.");

		   // This request is disabled, because DEBUG < INFO.
		   barlogger.debug("Exiting gas station search");
	}

}
