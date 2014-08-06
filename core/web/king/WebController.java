package web.king;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

/**
 * Controller��
 * 
 * @author wangjian
 * @time 2014-7-12 13:34:12
 */
public class WebController implements Filter {
	
	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		// ����Ϣ����ActionContext
		initActionContext(request, response);
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		try {
			//�������., ����Ϊ����Դ�ļ�. �Ź�.
			if(servletPath.indexOf(".")>=0){
				filterchain.doFilter(servletrequest, servletresponse);
				return;
			}
			// ִ��Ŀ�귽��
			String result = invokeTarget(servletPath);
			// ������
			invokeResult(result);
		} catch (Exception e) {
			ActionContext.setRequestParam("error", e.getMessage());
			invokeResult(Config.errorpage);
		}
	}

	/**
	 * ��ʼ��ActionContext
	 * 
	 * @param request
	 * @param response
	 */
	public void initActionContext(HttpServletRequest request,
			HttpServletResponse response) {
		// ����gbk����
		try {
			request.setCharacterEncoding(Config.encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("contextPath", "localhost/");
		ActionContext.setParam("request", request);
		ActionContext.setParam("response", response);
	}

	/**
	 * ִ��Ŀ�귽��
	 * 
	 * @param servletPath
	 * @return
	 */
	public String invokeTarget(String servletPath) {

		String[] pathinfo = servletPath.split("/");

		String packageName = "";
		String className = "";
		String methodName = "";

		if (pathinfo.length >= 4) {
			// 0:�հ� 1.package 2.class 3.method
			packageName = pathinfo[1];
			className = pathinfo[2];
			methodName = pathinfo[3];
		} else if (pathinfo.length >= 3) {
			// 0:�հ� 1.package 2.class
			packageName = pathinfo[1];
			className = pathinfo[2];
			methodName = "request";
		} else {
			throw new RuntimeException(Config.e404);
		}
		for (int i = 0; i < Config.virtualpath.length; i++) {
			if (Config.virtualpath[i].equals(packageName)) {
				packageName = Config.packagepath[i];
				break;
			}
			if (i == Config.virtualpath.length - 1) {
				throw new RuntimeException(Config.e404);
			}
		}
		String classPath = packageName + "." + className;

		try {
			Class<?> classObj = Class.forName(classPath);
			if (classObj == null) {
				return "";
			}
			Method methodObj = classObj.getMethod(methodName);
			if (methodObj == null)
				return "";
			Object resutlObj = methodObj.invoke(classObj.newInstance());
			return resutlObj + "";
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException(Config.e404);
		} catch (NoSuchMethodException me) {
			throw new RuntimeException(Config.e404);
		} catch (Exception e) {
			e.printStackTrace();
			String message = e.getCause() != null ? e.getCause().getMessage()
					: "δ֪����";
			throw new RuntimeException("ִ��actionʧ��![" + message + "]");

		}
	}

	/**
	 * ִ�з��ؽ��
	 * 
	 * @param result
	 */
	public void invokeResult(String result) {
		if (StringUtils.isNullOrEmpty(result)) {
			return;
		}
		// ���rd:��ͷ���ض���,����ת��
		if (result.startsWith("rd:")) {
			try {
				result = result.substring(3);
				ActionContext.getResponse().sendRedirect(result);
			} catch (IOException e) {
				e.printStackTrace();
				String message = e.getMessage() == null ? e.getCause()
						.getMessage() : e.getMessage();
				throw new RuntimeException("ִ����Դʧ��"+ message + "]");
			}
		} else {
			RequestDispatcher requestDispatcher = ActionContext.getRequest()
					.getRequestDispatcher(result);
			try {
				if (requestDispatcher != null)
					requestDispatcher.forward(ActionContext.getRequest(),
							ActionContext.getResponse());
			} catch (Exception e) {
				e.printStackTrace();
				String message = e.getMessage() == null ? e.getCause()
						.getMessage() : e.getMessage();
				throw new RuntimeException("ִ����Դʧ��"+ message + "]");
			}
		}
	}
}
