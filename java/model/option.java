package model;
public class option{
	private String  name;
	private String  value;
	private Integer  type;
	private Integer  status;
	private String  memo;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value=value;
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