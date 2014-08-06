package dao.king;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.king.StrUtils;

/**
 * sql 2 java obj, java obj 2 sql. 2014-7-6 19:58:14
 * 
 * @author wangjian
 * 
 */
public class SQLBuilder {

	public static String select(Object object) {
		String tableName = object.getClass().getSimpleName();
		Field[] fields = object.getClass().getDeclaredFields();
		StringBuffer sql1 = new StringBuffer(" SELECT * FROM " + tableName
				+ " ");
		StringBuffer sql2 = new StringBuffer(" WHERE 1=1 ");
		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String fieldValue = getClassFieldValue(object,fields[i]);
				if (fieldValue == null) {
					continue;
				}
				sql2.append(" AND " + fieldName + "=" + fieldValue);
			}
			String sql = sql1.append(sql2).toString();
			return sql;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String selectPage(String sql, int start, int size) {
		String sqlLimit = " limit " + start*size + "," + size;
		return sql.concat(sqlLimit);
	}

	public static String selectCount(String sql) {
		String sql1="SELECT count(1) ";
		String sql2=sql.substring(sql.toUpperCase().indexOf("FROM"));
		return sql1+sql2;
	}
	
	public static String delete(Object object) {

		String tableName = object.getClass().getSimpleName();
		Field[] fields = object.getClass().getDeclaredFields();
		StringBuffer sql1 = new StringBuffer(" DELETE FROM " + tableName);
		StringBuffer sql2 = new StringBuffer(" WHERE 1=1 ");

		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String fieldValue = getClassFieldValue(object,fields[i]);
				if (fieldValue == null) {
					continue;
				}
				sql2.append(" AND " + fieldName + "=" + fieldValue);
			}
			String sql = sql1.append(sql2).toString();
			return sql;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String update(Object object1, Object object2) {
		if (object1.getClass() != object2.getClass()) {
			return "";
		}
		String tableName = object1.getClass().getSimpleName();
		Field[] fields1 = object1.getClass().getDeclaredFields();
		Field[] fields2 = object2.getClass().getDeclaredFields();
		StringBuffer sql1 = new StringBuffer(" UPDATE " + tableName + " ");
		StringBuffer sql2 = new StringBuffer(" SET ");
		StringBuffer sql3 = new StringBuffer(" WHERE 1=1 ");

		try {
			for (int i = 0; i < fields2.length; i++) {
				String fieldName = fields2[i].getName();
				String fieldValue = getClassFieldValue(object2,fields2[i]);
				if (fieldValue == null) {
					continue;
				}
				sql2.append(fieldName + "=" + fieldValue + ",");
			}
			sql2.deleteCharAt(sql2.length() - 1);
			for (int i = 0; i < fields1.length; i++) {
				String fieldName = fields1[i].getName();
				String fieldValue = getClassFieldValue(object1,fields1[i]);
				if (fieldValue == null) {
					continue;
				}
				sql3.append(" AND " + fieldName + "=" + fieldValue);
			}
			String sql = sql1.append(sql2).append(sql3).toString();
			return sql;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String save(Object object) {

		String tableName = object.getClass().getSimpleName();
		Field[] fields = object.getClass().getDeclaredFields();
		StringBuffer sql1 = new StringBuffer(" INSERT INTO " + tableName + " (");
		StringBuffer sql2 = new StringBuffer(" VALUES(");

		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String fieldValue = getClassFieldValue(object,fields[i]);

				if (fieldValue == null) {
					continue;
				}
				sql1.append(fieldName + ",");
				sql2.append(fieldValue + ",");
			}
			sql1.deleteCharAt(sql1.length() - 1);
			sql2.deleteCharAt(sql2.length() - 1);
			sql1.append(") ");
			sql2.append(") ");
			String sql = sql1.append(sql2).toString();
			return sql;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 使用了这个方法以后,就不能正确的识别null了. null可以用'',0等代替
	 * @param object
	 * @param field
	 * @return
	 */
	private static String getClassFieldValue(Object object, Field field) {
		try {
			Method method = object.getClass().getDeclaredMethod(
					"get" + StrUtils.upInitial(field.getName()));
			Object value = method.invoke(object);
			if (value == null) {
				return null;
			}
			value=sqlValidate(value+"");
			String result="";
			if (field.getType() == java.lang.String.class) {
				result= "'" + value + "'";
			} else {
				result= value + "";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("转换字段出错!!!");
		}
	}
	private static String sqlValidate(String str) {  
		str=str.replaceAll("'", "‘");
		str=str.replaceAll("#", "#");
		return str;
    }  
}