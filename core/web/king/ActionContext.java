package web.king;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionContext {
	//ThreadLocal线程安全? 何时清空?
	private static ThreadLocal<Map<String,Object>> params=new ThreadLocal<Map<String,Object>>();
	
	public static void setParam(String key,Object value){
		Map<String,Object> paramMap;
		paramMap=params.get();
		if(paramMap==null)
			paramMap=new HashMap<String,Object>();
		paramMap.put(key, value);
		params.set(paramMap);
	}
	
	public static Object getParam(String key){
		Map<String,Object> paramMap;
		paramMap=params.get();
		if(paramMap==null){
			return null;
		}else{
			return paramMap.get(key);
		}
	}
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest)getParam("request");
	}
	public static HttpServletResponse getResponse(){
			return (HttpServletResponse)getParam("response");
	}
	public static Object getRequestParam(String key){
		HttpServletRequest request= (HttpServletRequest)getParam("request");
		if(request==null)
			return null;
		else
			return request.getParameter(key);
	}
	public static String getRequestParamString(String key){
		HttpServletRequest request= (HttpServletRequest)getParam("request");
		if(request==null)
			return null;
		else{
			Object obj= request.getParameter(key);
			if(obj==null)
				return null;
			else{
				return obj.toString();
			}
				
		}
	}
	public static Integer getRequestParamInteger(String key){
		String str=getRequestParamString(key);
		if(str==null){
			return null;
		}else{
			try{
				return Integer.parseInt(str);
			}catch(NumberFormatException e){
				return null;
			}
		}
	}
	public static void setRequestParam(String key,Object value){
		HttpServletRequest request= (HttpServletRequest)getParam("request");
		request.setAttribute(key, value);
		setParam("request",request);
	}
}
