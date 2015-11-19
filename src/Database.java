
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class Database {


	//VARIABLES
	private String hostname;  
	private int port; 

	private String dataBaseName;
	private String tableName;

	//database
	private MongoClient mongoClient;
	private DB db;
	private DBCollection table;
	private BasicDBObject document;
	


	public Database(String hostname, int port, String dataBaseName, String tableName)
	{

		this.hostname = hostname;
		this.port = port; 
		this.dataBaseName = dataBaseName;
		this.tableName = tableName;	
		boolean isconnected = connectToDb(hostname, port);

		// To get DB from Mongodb Or if it is not it will create new one 
		if (isconnected) {
			db = mongoClient.getDB(dataBaseName);
			table = db.getCollection(tableName);
		}
	}

	private boolean connectToDb(String hostname, int port){
		try {
			mongoClient = new MongoClient( hostname, port );	
			return true; 

		} catch (Exception e) {
			System.out.println("ERROR WHILE CONNECTION TO DB " + e.getMessage());
			return false; 
		}

	}

	public void writeToDb(String name, int point)
	{
		/**** Insert ****/
		// create a document to store key and value
		document = new BasicDBObject();
		document.append("_id", table.count() + 1); // Auto Increment id 
		document.put("Name", name);
		document.put("Point", point);
		document.put("createdDate", new Date());
		System.out.println( new Date());
		table.insert(document); // If the collection does not exist, then the insert() method will create the collection.
	}
	
	public void updatePoints(int id, int newPoint)
	{
		/**** Update ****/
		// search document where _id=id and update it with new values
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("Point", newPoint);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		table.update(query, updateObj);
	}


}
