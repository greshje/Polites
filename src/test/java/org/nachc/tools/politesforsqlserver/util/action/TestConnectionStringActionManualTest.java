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
		String url = "";				
		url += "jdbc:databricks://dbc-bac6f3c2-1e35.cloud.databricks.com:443/default;";
		url += "ssl=1;AuthMech=3;httpPath=/sql/1.0/warehouses/4513cdef142f8c41;UID=token;PWD=dapie6bff005a93e59fea01bce21443e2862;";
		log.info("\n" + url);
		TestConnectionStringAction.exec("databricks", url);
	}
}
