package client.app.obj;
public class Dependencies {
	//string array store object needed
	//private ArrayList<string> Dependencies = new ArrayList<string>;
	private String prerequiste;
	private String object;
	
	//constructor
	public Dependencies{
		
	}
	
	public dependencies(String pre, String obj){
		this.prerequiste = pre;
		this.object = obj;
	}
	
	//set and get functions
	public String get_prerequiste(){
		return prerequiste;
	}
	
	public String get_object(){
		return object;
	}
  
  public void set_object(String s){
    this.object = s;
  }
  
  public void set_prerequiste(String s){
    this.prerequiste = s;
  }
  
  }