package action;

import java.util.List;
import java.util.Random;

import dao.king.DBExecuter;
import web.king.ActionContext;
import model.article;
import model.page;

public class arti
  {
    public String getArti()
    {
    	article art=new article();
    	Integer id=ActionContext.getRequestParamInteger("artId");
    	if(id==null)
    		return "/action/index";
    	art.setId(id);
    	article art1=(article)DBExecuter.selectone(art);
    	ActionContext.setRequestParam("art", art1);

        return "/frontend/article.ftl";
     }
    //πÈµµ
    public String listArtiTitle()
    {
		page p = new page();
		Integer current = ActionContext.getRequestParamInteger("current");
		p.setCurrent(current==null?0:current);
		int count = DBExecuter.selectCount(new article());
		p.setCount(count);
		System.out.println(p.getLast());
    	
    	String sql="select temp.id,temp.title,temp.createmonth from ("+
    			"select DATE_FORMAT(createtime,'%Y-%m') 'createmonth',id,title from article order by id desc ) temp"+
    			"group by temp.createmonth,temp.id";
    	@SuppressWarnings("unchecked")
		List<article> artList=(List<article>)DBExecuter.selectPage(sql, p.getCurrent(), p.getSize());
		for(article art:artList)
			previewArticle(art);
		ActionContext.setRequestParam("artList", artList);
		ActionContext.setRequestParam("page", p);
		ActionContext.setRequestParam("pagepath", "/action/arti/listArti");
        
        return "/frontend/index.ftl";
    }
    //‘§¿¿
    public String listArti()
    {
		page p = new page();
		Integer current = ActionContext.getRequestParamInteger("current");
		p.setCurrent(current==null?0:current);
		int count = DBExecuter.selectCount(new article());
		p.setCount(count);
		System.out.println(p.getLast());
    	
    	article artp=new article();
    	@SuppressWarnings("unchecked")
		List<article> artList=(List<article>)DBExecuter.selectPage(artp, p.getCurrent(), p.getSize());
		for(article art:artList)
			previewArticle(art);
		ActionContext.setRequestParam("artList", artList);
		ActionContext.setRequestParam("page", p);
		ActionContext.setRequestParam("pagepath", "/action/arti/listArti");
        
        return "/frontend/index.ftl";
    }
      
    private void previewArticle(article art){
    	Random r=new Random();
    	int rr=r.nextInt(8);
    	if(rr==2){
    		String intro= art.getIntro();
    		intro="<img src=\"/images/small/2.jpg\"/>"+intro;
    		art.setIntro(intro);
    	}
    }
  }
