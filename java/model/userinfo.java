package model;
public class userinfo{
	private Integer  id;
	private String  username;
	private String  password;
	private Integer  type;
	private Integer  status;
	private String  memo;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type=type;
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
