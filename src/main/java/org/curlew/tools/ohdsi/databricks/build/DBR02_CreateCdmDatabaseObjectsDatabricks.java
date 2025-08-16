package org.curlew.tools.ohdsi.databricks.build;

import java.io.InputStream;
import java.sql.Connection;

import org.curlew.tools.ohdsi.databricks.util.PolitesDatabricksUtil;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.polites.util.connection.PolitesDatabricksConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.params.ApplicationParams;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * CDM 5.3
 * 
 * The only thing this class does is execute the DDL from the CommonDataModel to create the database objects (tables) for the CDM.  
 * This script will drop the schema if it exists (making the A01 class somewhat academic, but it is useful for testing/debugging).  
 * This script is for CDM 5.3.  
 *
 */

@Slf4j
public class DBR02_CreateCdmDatabaseObjectsDatabricks {

	private static final String DDL_FILE = "/databricks/cdm/spark/OMOPCDM_spark_5.3_ddl.sql";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = PolitesDatabricksConnectionFactory.getConnection();
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		// get the schema name
		String schemaName = AppParams.get("CdmSchemaName");
		log.info("Creating schema: " + schemaName);
		// check the connection
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		// echo status
		log.info("-------------------------------");
		log.info("START: Executing CDM 5.3 script to create tables: " + schemaName);
		log.info("-------------------------------");
		// get the sql from the ddl file
		log.info("Getting ddl file...");
		InputStream is = FileUtil.getInputStream(DDL_FILE);
		String sqlString = FileUtil.getAsString(is);
		sqlString = replace(sqlString, "@cdmDatabaseSchema", schemaName);
		// drop the schema if it exists
		log.info("Dropping existing database...");
		DatabricksDatabase.update("drop database if exists " + schemaName + " cascade", conn);
		// create the database
		log.info("Creating the database...");
		DatabricksDatabase.update("create database " + schemaName, conn);
		// populate the database from the ddl sql
		log.info("Creating database objects...");
		Database.executeSqlScript(sqlString, conn);
		// echo status
		log.info("-------------------------------");
		log.info("DONE: Executing CDM 5.3 script to create tables: " + schemaName);
		log.info("The tables shown below now exist in " + schemaName);
		log.info("-------------------------------");
		PolitesDatabricksUtil.getTablesForSchema(schemaName, conn);
	}

	private static String replace(String sqlString, String src, String dst) {
		return sqlString.replace(src, dst);
	}
	
}
