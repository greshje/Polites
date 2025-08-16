package org.curlew.databricks;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.polites.util.action.TestConnectionStringAction;
import org.nachc.tools.polites.util.connection.PolitesDatabricksConnectionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DB000_TestConnectionDatabricksTest {

	@Test
	public void shouldGetConnection() {
		log.info("Starting test...");
		log.info("Getting connection...");
		Connection conn = PolitesDatabricksConnectionFactory.getConnection();
		log.info("Testing connection...");
		TestConnectionStringAction.testConnection(conn, "databricks");
		log.info("Connection tests passed");
		log.info("Done.");
	}
	
}
