package socialnetwork.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	/**
	 * Read a properties file from the classpath and return a Properties object
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	static public Properties readProperties(final String filename) throws IOException {
		Properties props = new Properties();
		InputStream stream = Utils.class.getResourceAsStream(filename);
		props.load(stream);
		return props;
	}
}
