package org.curlew.tools.ohdsi.databricks.util;

import java.sql.Connection;

import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PolitesDatabricksUtil {

	public static Data getSchemas(Connection conn) {
		String sqlString;
		sqlString = "select distinct catalog_name, schema_name, schema_owner from information_schema.schemata;";
		Data data = Database.query(sqlString, conn);
		log.info("Got " + data.size() + " rows...");
		for(int i = 0;i < data.size();i++) {
			log.info("\t" + data.get(i).get("schemaName"));
		}
		return data;
	}
	
}
