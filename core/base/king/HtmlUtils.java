package base.king;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtils {
	public static void main(String[] args) {
		loadHwData();
	}

	public static void loadHwData() {
		Document doc = HtmlUtils.loadHtmlObj("http://www.xclyun.com/article/xiaoshuozhuanqu/");
		List<String> urls=parseHtmlObjAttr(doc,".pbox h4 a","href");
		for(String url:urls){
//			if(StringUtils.isNotEmpty(url)&& url.contains("www.qiqi49.cn")){
//			Document doc1 = HtmlUtils.loadHtmlObj(url);
//			List<String> contents=parseHtmlObjText(doc1,".blog");
//			System.out.println(contents.get(0));
//			System.out.println("--------------------------------------------------------");
//			}
			System.out.println(url);
		}
	}
	
//	public static void loadQiQiData() {
//		Document doc = HtmlUtils.loadHtmlObj("http://www.qiqi49.cn/");
//		List<String> urls=parseHtmlObjAttr(doc,"#sidebar a","href");
//		for(String url:urls){
//			if(StringUtils.isNotEmpty(url)&& url.contains("www.qiqi49.cn")){
//			Document doc1 = HtmlUtils.loadHtmlObj(url);
//			List<String> contents=parseHtmlObjText(doc1,".blog");
//			System.out.println(contents.get(0));
//			System.out.println("--------------------------------------------------------");
//			}
//		}
//	}
	
	
	
	public static Document loadHtmlObj(String url) {
		Document doc = null;
		int i = 0;
		while (i < 2) {
			try {
				doc = Jsoup.connect(url).get();
				Thread.sleep(100);
				break;
			} catch (Exception e) {
				//e.printStackTrace();
			}
			i++;
		}
		return doc;
	}
	public static List<String> parseHtmlObjAttr(Document doc, String pattern,String attr) {
		if (doc == null) {
			System.out.println("documentÎª¿Õ");
			return new ArrayList();
		}
		Elements ems = doc.select(pattern); 
		List<String> list = new ArrayList<String>();
		if (ems != null && ems.size() > 0) {
			for (int i = 0; i < ems.size(); i++) {
				Element em = ems.get(i);
				list.add(em.attr(attr));
			}
		}
		return list;
	}
	public static List<String> parseHtmlObjText(Document doc, String pattern) {
		if (doc == null) {
			System.out.println("documentÎª¿Õ");
			return  new ArrayList();
		}
		Elements ems = doc.select(pattern); 
		List<String> list = new ArrayList<String>();
		if (ems != null && ems.size() > 0) {
			for (int i = 0; i < ems.size(); i++) {
				Element em = ems.get(i);
				list.add(em.text());
			}
		}
		return list;
	}
	public static List<String> parseHtmlObj(Document doc, String pattern) {
		if (doc == null) {
			System.out.println("documentÎª¿Õ");
			return  new ArrayList();
		}
		Elements ems = doc.select(pattern); 
		List<String> list = new ArrayList<String>();
		if (ems != null && ems.size() > 0) {
			for (int i = 0; i < ems.size(); i++) {
				Element em = ems.get(i);
				list.add(em.toString());
			}
		}
		return list;
	}

}
