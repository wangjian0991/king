package web.king;

public class Config {
	public final static String encoding="GBK";
	
	public final static String errorpage="/error.ftl";
	
	public final static String[] packagepath={"action","admin"};//实际包名
	public final static String[] virtualpath={"action","admin"};//虚拟包名
	
	public final static int actionType=1;
	public final static int resourceType=0;
	
	public final static String e404="您访问的页面跟人跑了!";
}

