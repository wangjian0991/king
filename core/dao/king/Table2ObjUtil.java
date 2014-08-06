package dao.king;
/**
 * 解析sql语句,生成java obj语句.
 * 对sql的格式要求比较严
 * 2014-7-6 19:56:21
 * @author wangjian
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import base.king.StrUtils;

import com.alibaba.druid.util.StringUtils;

public class Table2ObjUtil {
	public static void main(String[] args) {
		try {
			readSql();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readSql() throws Exception{
		//ddl语句路径,只能单表生成
		FileInputStream f = new FileInputStream("D:\\spaces\\demospace\\Aq\\core\\jdao\\kw\\all\\sql.txt"); 
		BufferedReader dr=new BufferedReader(new InputStreamReader(f));
		StringBuffer objStr=new StringBuffer();
		StringBuffer objStr1=new StringBuffer();
		String[] sqltype={"int","tinyint","nvarchar","varchar","mediumtext"};
		String[] objtype={"Integer","Integer","String","String","String"};
		String line=dr.readLine();
		String[] title=line.split("`");
		if(title[0].equals("CREATE TABLE ")){
			String className=title[1];
			objStr.append("public class "+className+"{\n");
		}else{
			throw new RuntimeException("表头信息有误");
		}
		while((line=dr.readLine())!= null){ 
			try{
			if(line.contains("`")){
				String[] strs=line.split("`");
				if(strs[0].equals("  ")){
					String propName=strs[1];
					String propType="";
					for(int i=0;i<sqltype.length;i++){
						if(strs[2].contains(sqltype[i])){
							propType=objtype[i];
							objStr.append("\tprivate "+propType+"  "+propName+";\n");
							objStr1.append("\tpublic "+propType+" get"+StrUtils.upInitial(propName)+"()");
							objStr1.append("{\n\t\treturn "+propName+";\n\t}\n");
							objStr1.append("\tpublic void set"+StrUtils.upInitial(propName)+"("+propType+" "+propName+")");
							objStr1.append("{\n\t\tthis."+propName+"="+propName+";\n\t}\n");
							break;
						}
					}					
				}
			}
			}catch(Exception e){
				throw new RuntimeException(line+" 有误");
			}
		} 
		objStr.append(objStr1);
		objStr.append("}\n");
		System.out.println(objStr.toString());
		return null;
	}
}
