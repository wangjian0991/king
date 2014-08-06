package action;

import java.util.List;

import dao.king.DBExecuter;
import web.king.ActionContext;
import model.userinfo;

public class user{
	
    // default request
    public String request() { return login(); }
	
	public String login(){
		String userName=ActionContext.getRequestParam("userName")+"";
		String passWord=ActionContext.getRequestParam("passWord")+"";
		userinfo user=new userinfo();
		user.setUsername(userName);
		user.setPassword(passWord);
		@SuppressWarnings("unchecked")
		List<userinfo> objList=(List<userinfo>)DBExecuter.select(user);
		if(objList.size()>0   )
			ActionContext.setRequestParam("message", "µÇÂ½³É¹¦");
		return "/frontend/test.ftl";
	}
}
