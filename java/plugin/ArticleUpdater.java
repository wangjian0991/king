package plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import base.king.CollectionUtil;
import dao.king.DBExecuter;
import model.article;
import model.userinfo;



public class ArticleUpdater {
	private static int pages=1;
	private static int size=20;
	private static int count=3;
	private static int p=0;
	public static void main(String[] args) {
		try {
			cycleTask();
		} catch (InterruptedException e) {

		}
	}
	
    public static void cycleTask() throws InterruptedException{
    	// ����һ���̶���С���̳߳�
        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i < pages; i++) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                	int pp=0;
                	synchronized (ArticleGetter.class) {
                		pp=p;
                		p++;
                	}   
                    doTask(pp);
                }
            };
            // ��δ��ĳ��ʱ��ִ�и���������
            service.execute(run);
        }
        // �ر������߳�
        service.shutdown();
        // �ȴ����߳̽������ټ���ִ������Ĵ���
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("���������ѽ���");
    }
    
    public static void doTask(int p){
    	try {
    		article artParm=new article();
    		artParm.setIntro("AA");
			@SuppressWarnings({"unchecked" })
			List<article> artList=(List<article>)DBExecuter.selectPage(artParm, p, size);
			List<article> artList1=new ArrayList<article>();
			for(article a:artList){
				String intro="";
				if(a.getContent().length()>151){
					intro=a.getContent().substring(0,150);
				}else{
					intro=a.getContent();
				}
				intro=intro+". . . . . .";
				article art=new article();
				art.setIntro(intro);
				artList1.add(art);
			}
			DBExecuter.updateList(artList, artList1);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println();
    	System.out.println();
    }

}
