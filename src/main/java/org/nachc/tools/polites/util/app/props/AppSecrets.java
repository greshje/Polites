package org.nachc.tools.polites.util.app.props;

import java.io.File;
import java.util.Properties;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.props.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppSecrets {

	private static Properties PROPS;
	
	static {
		init();
	}
	
	private static void init() {
		log.info("Initializing secrets");
		String secretsFileName = AppParams.get("SecretsFileLocation");
		log.info("Secrets file name: " + secretsFileName);
		File file = new File(secretsFileName);
		PROPS = PropertiesUtil.getAsProperties(file);
		log.info("Done with init AppSecrets.");
	}
	
	public static String get(String key) {
		return PROPS.getProperty(key);
	}

}
