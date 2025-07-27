package org.nachc.tools.politesforsqlserver.util.action;

import org.junit.Test;
import org.nachc.tools.polites.util.action.TestConnectionStringAction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestConnectionStringActionManualTest {

	@Test
	public void shouldGetConnection() {
		// TestConnectionStringAction.exec("ms sqlserver", "jdbc:sqlserver://localhost:1433;databaseName=synthea_omop;encrypt=false;TrustServerCertificate=True;user=synthea_omop;password=Sneaker01");
		// TestConnectionStringAction.exec("postgresql", "jdbc:postgresql://broadsea-atlasdb:5432/postgres?user=postgres&password=mypass");
	}
}
