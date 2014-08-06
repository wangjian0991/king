package plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.article;

import org.jsoup.nodes.Document;

import base.king.HtmlUtils;
import dao.king.DBExecuter;

public class ArticleGetter {
	
	private static List<String> baseUrls=new ArrayList<String>();
		
    public static void main(String[] args) throws IOException, InterruptedException {
    	initBaseUrl();
    	cycleTask();
    }
    
    public static void initBaseUrl(){
    	baseUrls.add("http://www.mwhongchen.com/top/jinghua/index.html");
    	for(int i=2;i<40;i++){
    		baseUrls.add("http://www.mwhongchen.com/top/jinghua/index_"+i+".html");
    	}
    }
    
    public static void cycleTask() throws InterruptedException{
    	// 创建一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < baseUrls.size(); i++) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                	String url;
                	synchronized (ArticleGetter.class) {
                		url=baseUrls.get(baseUrls.size()-1);
                		baseUrls.remove(baseUrls.size()-1);
                	}   
                    doTask(url);
                }
            };
            // 在未来某个时间执行给定的命令
            service.execute(run);
        }
        // 关闭启动线程
        service.shutdown();
        // 等待子线程结束，再继续执行下面的代码
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("所有任务已结束");
    }
    
    public static void doTask(String url){
    	String url11="";
    	try {
    		System.out.println("================================="+url);
			List<String> list=HtmlUtils.parseHtmlObjAttr(HtmlUtils.loadHtmlObj(url), ".three a", "href");
			String d=new Date().toLocaleString();
			List<article> artList=new ArrayList();
			for(int i=0;i<list.size();i++){
				url11="http://www.mwhongchen.com/"+list.get(i);
				Document doc=HtmlUtils.loadHtmlObj(url11);
				if(HtmlUtils.parseHtmlObjText(doc, ".firsttitle1").size()<=0)
					continue;
				String title=HtmlUtils.parseHtmlObjText(doc, ".firsttitle1").get(0);
				String author=HtmlUtils.parseHtmlObjText(doc, ".author span a").get(0);
				String typetext=HtmlUtils.parseHtmlObjText(doc, ".crumbswrap a").get(1);
				String content=HtmlUtils.parseHtmlObjText(doc, ".content-arc").get(0);
				String intro=content.length()>155?content.substring(0, 150):content;
				article art=new article();
				art.setAuthor(author);
				art.setContent(content);
				art.setCreatetime(d);
				art.setIntro(intro);
				art.setMemo("");
				art.setSource("墨舞红尘");
				art.setStatus(0);
				art.setTitle(title);
				art.setTypetext(typetext);
				artList.add(art);
			}
			DBExecuter.saveList(artList);
		} catch (Exception e) {
			System.out.println("=========="+url11+"  错误!");
			e.printStackTrace();
		}
    }
}