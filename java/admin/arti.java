package admin;

import java.util.List;

import web.king.ActionContext;
import base.king.DateUtils;

import com.alibaba.druid.util.StringUtils;

import dao.king.DBExecuter;
import model.article;
import model.page;

public class arti
  {
	
	public static void main(String [] arga){
		String content="adsfsdaf<img aaa=  />";
		String img=content.substring(content.indexOf("<img"), content.length());
		img=img.substring(0,img.indexOf(">")+1);
		System.out.println(img);
	}
	
    public String listArti()
    {
    	page p = new page();
		Integer current = ActionContext.getRequestParamInteger("current");
		p.setCurrent(current==null?0:current);
		int count = DBExecuter.selectCount(new article());
		p.setCount(count);
    	
		Integer status=ActionContext.getRequestParamInteger("status");
		String typetext=ActionContext.getRequestParamString("status");
		String source=ActionContext.getRequestParamString("status");
		//String createtime;
		
    	article art=new article();
    	art.setStatus(status);
    	art.setTypetext(typetext);
    	art.setSource(source);
    	@SuppressWarnings("unchecked")
		List<article> artList=(List<article>)DBExecuter.selectPage(art, p.getCurrent(), p.getSize());
		ActionContext.setRequestParam("artList", artList);
		ActionContext.setRequestParam("page", p);
        
        return "/backend/article-list.ftl";
     }
    
    public String initArti()
    {
    	@SuppressWarnings("unchecked")
		List<article> typeList=(List<article>)DBExecuter.select("select DISTINCT typetext from article", article.class);
        ActionContext.setRequestParam("typeList", typeList);
        if(ActionContext.getRequestParamInteger("artId")!=null){
        	article art=new article();
        	art.setId(ActionContext.getRequestParamInteger("artId"));
        	article art1=(article)DBExecuter.selectone(art);
        	if(art1!=null){
        		ActionContext.setRequestParam("art", art1);
        	}
        }
    	return "/backend/article-edit.ftl";
     }
    
    public String saveArti()
    {	
    	String content=ActionContext.getRequestParamString("content");
    	String title=ActionContext.getRequestParamString("title");
    	String author=ActionContext.getRequestParamString("author");
    	if(StringUtils.isEmpty(author))author="ØýÃû";
    	String typetext=ActionContext.getRequestParamString("typetext");
    	if(StringUtils.isEmpty(typetext))typetext="Ëæ±Ê";
    	Integer status=ActionContext.getRequestParamInteger("status");
    	if(status==null)status=0;
    	String source=ActionContext.getRequestParamString("source");
    	if(StringUtils.isEmpty(source))source="Ô­´´";
    	String memo=ActionContext.getRequestParamString("memo"); 
    	
    	String intro=""; 
    	if(!StringUtils.isEmpty(content)){
    		if(content.length()>152){
    			intro=content.substring(0, 150);
    		}else{
    			intro=content;
    		}
    		if(content.contains("<img")){
    			String img=content.substring(content.indexOf("<img"), content.length());
    			img=img.substring(0,img.indexOf(">")+1);
    			img.replace("images", "images/small");
    			intro=img+intro;
    		}
    	}
    	
    	article art=new article();
    	art.setContent(content);
    	art.setAuthor(author);
    	art.setIntro(intro);
    	art.setMemo(memo);
    	art.setSource(source);
    	art.setStatus(status);
    	art.setTitle(title);
    	art.setTypetext(typetext);
    	
    	Integer id=ActionContext.getRequestParamInteger("id");
    	if(id==null){
    		String createtime=DateUtils.getNowString(null);
    		art.setCreatetime(createtime);
    		DBExecuter.save(art);
    	}else{
    		article artParam=new article();
    		artParam.setId(id);
    		String updatetime=DateUtils.getNowString(null);
    		art.setUpdatetime(updatetime);
    		DBExecuter.update(artParam, art);
    	}
    	
        return "rd:/admin/arti/initArti";
     }
  }
