package model;
public class article{
	private Integer  id;
	private String  createtime;
	private String  title;
	private String  intro;
	private String  content;
	private String  author;
	private String  source;
	private String  updatetime;
	private String  typetext;
	private Integer  status;
	private String  memo;
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getCreatetime(){
		return createtime;
	}
	public void setCreatetime(String createtime){
		this.createtime=createtime;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getIntro(){
		return intro;
	}
	public void setIntro(String intro){
		this.intro=intro;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author=author;
	}
	public String getSource(){
		return source;
	}
	public void setSource(String source){
		this.source=source;
	}
	public String getUpdatetime(){
		return updatetime;
	}
	public void setUpdatetime(String updatetime){
		this.updatetime=updatetime;
	}
	public String getTypetext(){
		return typetext;
	}
	public void setTypetext(String typetext){
		this.typetext=typetext;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo){
		this.memo=memo;
	}
}

