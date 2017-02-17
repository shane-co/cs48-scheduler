/**
 * for example:
 * ScheduleEvent s1 = new EventBuilder().start(100 means 1:00).end(150).buildEvent();
 * ScheduleEvent s2 = new EventBuilder()
 * 					  .deps(Dependencies1)
 * 					  .start(200)
 * 					  .end(300)
 * 					  .description("Midterm is so hard")
 * 					  .buildEvent();
 */



public class EventBuilder 
{
	private ArrayList<Dependencies> _deps=new ArrayList<Dependencies>();
    private int _start=0;
    private int _end=0;
    private String _description="";
    
    public EventBuilder() { }
    
    public ScheduleEvent buildEvent()
    {
    	return new ScheduleEvent(_deps,_start,_end,_description);
    }
    
    public EventBuilder start(int _start)
    {
    	this._start=_start;
    	return this;
    }
    
    public EventBuilder end(int _end)
    {
    	this._end=_end;
    	return this;
    }
    
    public EventBuilder end(String _description)
    {
    	this._description=_description;
    	return this;
    }
    
    public EventBuilder deps(ArrayList<Dependencies> _deps)
    {
    	this._deps=_deps;
    	return this;
    }
    
}
