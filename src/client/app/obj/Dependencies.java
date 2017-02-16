public class Dependencies {
	//string array store object needed
	//private ArrayList<string> Dependencies = new ArrayList<string>;
	private string prerequiste;
	private string object;
	
	//constructor
	public Dependencies{
		
	}
	
	public dependencies(String pre, string obj){
		this.prerequiste = pre;
		this.object = obj;
	}
	
	//set and get functions
	public string get_prerequiste(){
		return prerequiste;
	}
	
	public string get_object(){
		return object;
	}
  
  public void set_object(string s){
    this.object = s;
  }
  
  public void set_prerequiste(string s){
    this.prerequiste = s;
  }
  
  }
	
