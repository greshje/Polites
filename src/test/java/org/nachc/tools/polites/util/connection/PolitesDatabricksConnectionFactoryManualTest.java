package org.nachc.tools.polites.util.connection;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.polites.util.action.TestConnectionStringAction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PolitesDatabricksConnectionFactoryManualTest {

	@Test
	public void testConnection() {
		log.info("Starting test...");
		Connection conn = PolitesDatabricksConnectionFactory.getConnection();
		TestConnectionStringAction.testConnection(conn, "databricks");
		log.info("Done.");
	}
	
}
