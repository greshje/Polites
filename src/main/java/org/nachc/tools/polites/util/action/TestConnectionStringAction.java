package org.nachc.tools.polites.util.action;

import java.sql.Connection;

import org.nachc.tools.polites.util.connection.PolitesConnectionFactory;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestConnectionStringAction {

	public static void exec(String dbmsType, String jdbcConnectionString) {
		Connection conn = null;
		try {
			String msg = "";
			msg += "\n\n";
			msg += "JAVA VERSION: " + System.getProperty("java.version") + "\n";
			msg += "JAVA RUNTIME: " + System.getProperty("java.runtime.version") + "\n";
			msg += "\n\n";
			log.info(msg);
			log.info("Getting connection...");
			conn = PolitesConnectionFactory.getConnection(jdbcConnectionString);
			log.info("Got connection: \n" + conn);
			testConnection(conn, dbmsType);
			log.info("Done with connection test.");
		} finally {
			Database.close(conn);
		}
		log.info("Done testing connection.");
	}
	
	public static void testConnection(Connection conn, String dbmsType) {
		if("databricks".equals(dbmsType)) {
			testDatabricksConnection(conn);
		}
	}
	
	private static void testDatabricksConnection(Connection conn) {
		String sqlString;
		sqlString = "select distinct catalog_name, schema_name, schema_owner from information_schema.schemata;";
		Data data = Database.query(sqlString, conn);
		log.info("Got " + data.size() + " rows...");
		for(int i = 0;i < data.size();i++) {
			
		}
	}
}
