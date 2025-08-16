package org.nachc.tools.polites.util.action;

import org.junit.Test;
import org.nachc.tools.polites.util.action.TestConnectionStringAction;
import org.nachc.tools.polites.util.connection.PolitesDatabricksConnectionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestConnectionStringActionDatabricksManualTest {

	@Test
	public void shouldGetConnection() {
		log.info("Starting test...");
		String url = PolitesDatabricksConnectionFactory.getUrl();
		log.info("Got url...");
		TestConnectionStringAction.exec("databricks", url);
		log.info("Done.");
	}
	
}
