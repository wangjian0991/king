package dao.king;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import base.king.StrUtils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 执行最终生成的sql语句 2014-7-6 19:56:51
 * 
 * @author wangjian
 * 
 */
@SuppressWarnings("deprecation")
public class DBExecuter {

	public static Object selectone(Object object) {
		String sql = SQLBuilder.select(object);
		ResultSet resultSet = null;
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			List<?> list= turnToObject(resultSet, object.getClass());
			statement.close();
			conn.close();
			if(list!=null&& list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<?> select(Object object) {
		String sql = SQLBuilder.select(object);
		ResultSet resultSet = null;
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			List<?> list=turnToObject(resultSet, object.getClass());
			statement.close();
			conn.close();
			return list;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static List<?> select(String sql, Class<?> objClass) {
		ResultSet resultSet = null;
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			List<?> list=turnToObject(resultSet, objClass);
			statement.close();
			conn.close();
			return list;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static List<?> selectPage(Object object, int start, int size) {
		String sql = SQLBuilder.select(object);
		sql = SQLBuilder.selectPage(sql, start, size);
		System.out.println(sql);
		ResultSet resultSet = null;
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			List<?> list=turnToObject(resultSet, object.getClass());
			statement.close();		
			conn.close();
			return list;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static List<?> selectPage(String sql, Class<?> objClass,
			int start, int size) {
		sql = SQLBuilder.selectPage(sql, start, size);
		System.out.println(sql);
		ResultSet resultSet = null;
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			List<?> list=turnToObject(resultSet, objClass);
			statement.close();
			conn.close();
			return list;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static Integer selectCount(Object object) {
		String sql = SQLBuilder.select(object);
		sql = SQLBuilder.selectCount(sql);
		System.out.println(sql);
		ResultSet resultSet = null;
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			int count=0;
			if (resultSet.first()) {
				count= resultSet.getInt(1);
			} 
			statement.close();
			conn.close();
			return count;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static Integer selectCount(String sql) {
		sql = SQLBuilder.selectCount(sql);
		ResultSet resultSet = null;
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			int count=0;
			if (resultSet.first()) {
				count= resultSet.getInt(1);
			} 
			statement.close();
			conn.close();
			return count;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	public static int delete(Object object) {
		String sql = SQLBuilder.delete(object);
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			int num=statement.executeUpdate(sql);
			statement.close();
			conn.close();
			return num;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	public static int update(Object object1, Object object2) {
		String sql = SQLBuilder.update(object1, object2);
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			int num= statement.executeUpdate(sql);
			statement.close();
			conn.close();
			return num;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[" + sql
					+ "]" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
	public static int[] updateList(List<?> objList1,List<?> objList2) {
		try {
			Connection conn = connection();
			conn.setAutoCommit(false);
			Statement statement = conn.createStatement();
			for(int i=0;i<objList1.size();i++){
				String sql = SQLBuilder.update(objList1.get(i),objList2.get(i));
				System.out.println(sql);
				statement.addBatch(sql);
			}
			
			int[] count= statement.executeBatch();
			conn.commit();
			statement.close();
			conn.close();
			return count;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[批量"
					+ "]" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static int save(Object object) {
		String sql = SQLBuilder.save(object);
		try {
			Connection conn = connection();
			Statement statement = conn.createStatement();
			int num= statement.executeUpdate(sql);
			statement.close();
			conn.close();
			return num;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL["+sql 
					+ "]" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int[] saveList(List<?> objList) {
		try {
			Connection conn = connection();
			conn.setAutoCommit(false);
			Statement statement = conn.createStatement();
			for(Object obj:objList){
				String sql = SQLBuilder.save(obj);
				statement.addBatch(sql);
			}
			
			int[] count= statement.executeBatch();
			conn.commit();
			statement.close();
			conn.close();
			return count;
		} catch (SQLException e) {
			log.error("TIME:" + (new Date()).toLocaleString() + "SQL[批量" 
					+ "]" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
//statement不能静态，因为会线程不安全
	//druid的connection需要close，不然不会回收。 close以后没有销毁，还是druid管理
	private static Connection connection() {
		try {
			if (dataSource == null) {
				synchronized (DBExecuter.class) {
					if (dataSource == null) {
						dataSource = new DruidDataSource();
						dataSource.setMaxActive(30);
						dataSource.setMinIdle(4);
						dataSource.setInitialSize(4);
						dataSource.setPoolPreparedStatements(false);
						dataSource.setTestOnBorrow(true);
						dataSource.setTestOnReturn(false);
						dataSource.setTestWhileIdle(true);
						dataSource.setValidationQuery("select 1");
						dataSource.setDriverClassName("com.mysql.jdbc.Driver");
						dataSource
								.setUrl("jdbc:mysql://localhost:3306/myblog?useUnicode=true&amp;characterEncoding=GBK");
						dataSource.setUsername("root");
						dataSource.setPassword("901116");
												
					}
				}
			}
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static List<Object> turnToObject(ResultSet resultSet,
			Class<?> objClass) {
		/** 存储转化后的实体类 */
		List<Object> listObjs = new ArrayList<Object>();

		/** resultSet数据表中的字段名称 */
		String[] columnNames = null;

		/** resultSet数据表中对应字段的数据类型 */
		String[] columnTypes = null;

		try {
			if (resultSet == null) {
				return listObjs;
			} else {
				ResultSetMetaData metaResult = resultSet.getMetaData();
				int length = metaResult.getColumnCount();
				columnNames = new String[length];
				columnTypes = new String[length];
				for (int i = 0; i < columnNames.length; i++) {
					columnNames[i] = metaResult.getColumnName(i + 1);
					columnTypes[i] = metaResult.getColumnClassName(i + 1);
				}

				while (resultSet.next()) {
					try {
						/* 实例化实体类 */
						Object obj = objClass.newInstance();

						/* 根据字段名调用实体类中的set方法 */
						for (int j = 0; j < columnNames.length; j++) {
							Method method = objClass.getDeclaredMethod("set"
									+ StrUtils.upInitial(columnNames[j]),
									paraTypeClass(columnTypes[j]));
							method.invoke(obj,
									resultSet.getObject(columnNames[j]));
						}

						listObjs.add(obj);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}

			/* 关闭结果集 */
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listObjs;
	}

	private static Class<?> paraTypeClass(String str) {
		if (str.equals("java.lang.String")) {
			return java.lang.String.class;
		} else if (str.equals("java.lang.Integer")) {
			return java.lang.Integer.class;
		} else if (str.equals("java.lang.Character")) {
			return java.lang.Character.class;
		} else if (str.equals("java.lang.Double")) {
			return java.lang.Double.class;
		} else if (str.equals("java.lang.Short")) {
			return java.lang.Short.class;
		} else if (str.equals("java.lang.Byte")) {
			return java.lang.Byte.class;
		} else if (str.equals("java.lang.Float")) {
			return java.lang.Float.class;
		} else if (str.equals("java.lang.Boolean")) {
			return java.lang.Boolean.class;
		} else if (str.equals("java.util.Date")) {
			return java.util.Date.class;
		}
		return null;
	}

	private static DruidDataSource dataSource;
	private static Logger log = Logger.getLogger(DBExecuter.class);
}