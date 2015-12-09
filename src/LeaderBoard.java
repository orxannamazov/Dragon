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

	Database db = Main.db;
	ArrayList<Score> score;
	
	public LeaderBoard ()
	{
		score = new ArrayList<Score>();
		fillArray();
	}

	
	public void fillArray()
	{
		DBCursor cursor =  db.getDBCursor();
		Iterator<DBObject> interate = cursor.iterator();

		while (interate.hasNext()) {
			DBObject dbObject = (DBObject) interate.next();

			try {
				String name =	(String)  dbObject.get("Name");
				int point 	= 	(Integer) dbObject.get("Point");
				long value =  (long) dbObject.get("id");
				int id 		= toIntExact(value)	;
				
				

				Score sc = new Score();
				sc.setId(id);
				sc.setName(name);
				sc.setPoint(point);
				score.add(sc);
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("FILL ARRAY: " + e.getMessage());
				e.printStackTrace();
			}
		

		}
	}


	public ArrayList<Score> getArray() {
		return score;
	}
	public int getCollectionLength()
	{
		return db.getCollectionLength();
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
