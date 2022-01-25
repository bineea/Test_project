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
			//��Ҫ����mysql-connector-java������
			connection = DriverManager.getConnection("jdbc:mysql://152.136.155.204:3316/sampledb?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true", "sample", "sample");
			
			//����resultSetTypeָ��ResultSet�����͡���ѡ���У�
			//TYPE_FORWARD_ONLY:ȱʡ���͡�ֻ������ǰ���������Ҳ����ܵ������û������ݿ��������ĵ�Ӱ�졣
			//TYPE_SCROLL_INSENSITIVE��������ǰ�������������Ĺ����������ܵ������û������ݿ��������ĵ�Ӱ�졣
			//TYPE_SCROLL_SENSITIVE: ������ǰ�������������Ĺ������ܵ������û������ݿ��������ĵ�Ӱ�졣���ڸò����£��ἰʱ�������ݿ�ĸ��£��Ա����ResultSet�е����ݡ�
			
			//����resultSetConcurrency����ResultSet�Ĳ����ԣ��ò����趨�Ƿ���Ը���ResultSet��
			//CONCUR_READ_ONLY:ȱʡֵ��ָ�������Ը���ResultSet
			//CONCUR_UPDATABLE:ָ�����Ը���ResultSet��
			
			//Ĭ��Statement���������
			//��Ҫ����ResultSet.CONCUR_UPDATABLE������ͨ��ResultSet��������
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			statement.execute("select id,code,remark,version from sample_test where id > 0 order by id asc limit 10");
			resultSet = statement.getResultSet();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while(resultSet.next()) {
				do {
					Map<String, String> row = new HashMap<String, String>();
					//�е��±���1��ʼ
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
					//SQL���Ĳ�ѯ�����������������µ��ֶ�
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
