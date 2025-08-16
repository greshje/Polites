package org.nachc.tools.polites.util.app.props;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppSecretsTest {

	@Test
	public void testGetUrl() {
		log.info("Starting test...");
		String url = AppSecrets.get("Url");
		String token = AppSecrets.get("Token");
		String maskedToken = getMaskedToken(token);
		log.info("Got URL: " + url);
		log.info("Got Token: " + maskedToken);
		log.info("Done.");
	}
	
	public static String getMaskedToken(String token) {
		String rtn = "xxxxxxxxxxx";
	    if (token == null || token.isEmpty() || token.length() < 4) {
	    	rtn += "INVALID-TOKEN";
	    } else {
		    int len = token.length();
		    rtn += token.substring(len - 4);
	    }
	    return rtn;
	}
	

}
