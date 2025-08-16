package org.curlew.tools.ohdsi.databricks.build;

import java.sql.Connection;

import org.curlew.tools.ohdsi.databricks.util.PolitesDatabricksUtil;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.polites.util.connection.PolitesDatabricksConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

/**
 * The only thing this class does is create the Databricks schema to hold the CDM. 
 *   - If the schema already exists it is dropped.  
 *   - There is nothing version specific about this class as all it does is create an empty schema.  
 */

@Slf4j
public class DBR01_CreateCdmDatabaseDatabricks {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = PolitesDatabricksConnectionFactory.getConnection();
			log.info("Got connection.");
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		// get the schemaName
		String schemaName = AppParams.get("CdmSchemaName");
		log.info("Got schema: " + schemaName);
		// check the connection 
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		// echo status
		log.info("-------------------------------");
		log.info("Dropping and recreating schema: " + schemaName);
		log.info("-------------------------------");
		// drop the schema if it exists
		log.info("Doing drop...");
		String dropString = "drop database if exists " + schemaName + " cascade";
		log.info(dropString);
		DatabricksDatabase.update(dropString, conn);
		// create the schema
		log.info("Doing create...");
		String sqlString = "create database " + schemaName;
		DatabricksDatabase.update(sqlString, conn);
		// echo status
		log.info("-------------------------------");
		log.info("Done creating schema: " + schemaName);
		log.info("The schemas listed below now exist in your Databricks instance: ");
		log.info("-------------------------------");
		PolitesDatabricksUtil.getSchemas(conn);
	}
	
}
