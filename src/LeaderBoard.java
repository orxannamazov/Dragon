import static java.lang.Math.toIntExact;

import java.util.*;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * 
 * @author Orxan
 *
 */
public class LeaderBoard {

	Database db ;
	ArrayList<Score> score;
	
	public LeaderBoard ()
	{
		db =  new Database();
		score = new ArrayList<Score>();
		fillArray();
	}

	
	public void fillArray()
	{
		DBCursor cursor =  db.getDBCursor();
		Iterator<DBObject> interate = cursor.iterator();

		while (interate.hasNext()) {
			DBObject dbObject = (DBObject) interate.next();

			int id = toIntExact((Long)dbObject.get("id"));
			int point = (Integer) dbObject.get("Point");
			String name = (String) dbObject.get("Name");

			Score sc = new Score();
			sc.setId(id);
			sc.setName(name);
			sc.setPoint(point);
			score.add(sc);

		}
	}


	public ArrayList<Score> getArray() {
		return score;
	}
	
	// Print all score 
	public void printScore()
	{
		for (int i = 0; i < score.size(); i++) {
			System.out.println(score.get(i).toString());
		}
	}
	
	//Print just top 3 scores 
	public void topThreeScore(){

		for (int i = 0; i < 3; i++) {
			System.out.println(score.get(i).toString());
		}	
	}
	
	public void readFromDB()
	{

	}
	





}
