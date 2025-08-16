package org.nachc.tools.polites.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.nachc.tools.polites.util.app.props.AppSecrets;

public class PolitesDatabricksConnectionFactory {

	public static Connection getConnection() {
		try {
			String url = getUrl();
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public static String getUrl() {
		String url = AppSecrets.get("Url");
		String token = AppSecrets.get("Token");
		String rtn = "";
		rtn += url;
		rtn += "UID=token;PWD=";
		rtn += token;
		rtn += ";";
		return rtn;
	}
	
	public static String getBaseUrl() {
		return AppSecrets.get("Url");
	}
	
	public static String getToken() {
		return AppSecrets.get("Token");
	}
	
	public static String getMaskedToken() {
		String rtn = "xxxxxxxxxxx";
		String token = getToken();
	    if (token == null || token.isEmpty() || token.length() < 4) {
	    	rtn += "INVALID-TOKEN";
	    } else {
		    int len = token.length();
		    rtn += token.substring(len - 4);
	    }
	    return rtn;
	}
	
	
	
}
