
/**
 * @author Orkhan
 * 
 */
public class Score {
	
	//VARIABLES 
	private String name;
	private int id ;
	private int point;
	
	public Score ()
	{
		
	}
	public Score(String name, int id, int point) {
		this.name = name;
		this.id = id;
		this.point = point;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return "Name = " + name + ",  Point = " + point + "\n";
	}

}
