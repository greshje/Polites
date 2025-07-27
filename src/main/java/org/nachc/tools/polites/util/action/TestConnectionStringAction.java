package org.nachc.tools.polites.util.action;

import java.sql.Connection;

import org.nachc.tools.polites.util.connection.PolitesConnectionFactory;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestConnectionStringAction {

	public static void exec(String dbmsType, String jdbcConnectionString) {
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = PolitesConnectionFactory.getConnection(jdbcConnectionString);
			log.info("Got connection: \n" + conn);
			String sqlString = getTestQuery(dbmsType);
			Data data = Database.query(sqlString, conn);
			Row row = data.get(0);
			String testString = row.get("test");
			log.info("Test String: " + testString);
		} finally {
			Database.close(conn);
		}
		log.info("Done testing connection.");
	}
	
	public static String getTestQuery(String dbmsType) {
		return "select 'hello world' as test";
	}
}
