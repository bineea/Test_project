package test_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleExecutor {
	
	public static void main(String[] args) throws Exception {
		SimpleExecutor executor = new SimpleExecutor();
		executor.query4Update();
	}

	public List<Map<String, String>> query4Update() throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//需要配置mysql-connector-java的依赖
			connection = DriverManager.getConnection("jdbc:mysql://152.136.155.204:3316/sampledb?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true", "sample", "sample");
			
			//参数resultSetType指定ResultSet的类型。其选项有：
			//TYPE_FORWARD_ONLY:缺省类型。只允许向前滚动，并且不会受到其他用户对数据库所做更改的影响。
			//TYPE_SCROLL_INSENSITIVE：允许向前或向后两个方向的滚动，不会受到其他用户对数据库所做更改的影响。
			//TYPE_SCROLL_SENSITIVE: 允许向前或向后两个方向的滚动，受到其他用户对数据库所做更改的影响。即在该参数下，会及时跟踪数据库的更新，以便更改ResultSet中的数据。
			
			//参数resultSetConcurrency设置ResultSet的并发性，该参数设定是否可以更新ResultSet。
			//CONCUR_READ_ONLY:缺省值，指定不可以更新ResultSet
			//CONCUR_UPDATABLE:指定可以更新ResultSet。
			
			//默认Statement不允许更新
			//需要设置ResultSet.CONCUR_UPDATABLE，才能通过ResultSet更新数据
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			statement.execute("select id,code,remark,version from sample_test where id > 0 order by id asc limit 10");
			resultSet = statement.getResultSet();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while(resultSet.next()) {
				do {
					Map<String, String> row = new HashMap<String, String>();
					//列的下标由1开始
					for (int i=1; i<=columnCount; i++) {
						String columnName = rsmd.getColumnLabel(i).trim();
	                    String value;
	                    try {
	                        value = resultSet.getString(i);
	                    } catch (Exception e) {
	                        value = "";
	                    }
	                    row.put(columnName, (value == null) || (value.equals("")) ? "" : value.trim());
					}

					String version = row.get("version");
					//SQL语句的查询结果必须包含操作更新的字段
					resultSet.updateInt("version", Integer.parseInt(version) + 1);
					resultSet.updateRow();
					
				} while(resultSet.next());
				
				resultSet.close();
				statement.execute("select id,code,remark from sample_test where id < 0 order by id asc limit 10");
				resultSet = statement.getResultSet();
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return null;
	}
}
