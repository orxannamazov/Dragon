
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author Orxan
 *
 */
public class Database {


	//VARIABLES
	private String hostname;  
	private int port; 
	private String fileName = "resources/DatabaseInfo.properties";
	private String onlineHost = "mongodb://orkhan:Orxan513@ds061454.mongolab.com:61454/dragonvalley";

	private String dataBaseName;
	private String tableName;

	//database
	private MongoClient mongoClient;
	private DB db;
	private DBCollection table;
	private BasicDBObject document;
	private boolean isconnected = false;



	public Database()
	{

		try {
			
			if (isInternetReachable()) {
				readProperties(fileName);
				isconnected = connectToDb(onlineHost);
			}
//			else 
//			{
//				readProperties(fileName);
//				isconnected = connectToDb(hostname);
//			}
			
		//	readProperties(fileName);
			//isconnected = connectToDb(hostname);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//checkCollection(tableName);

		
		

//		// To get DB from Mongodb Or if it is not it will create new one 
//		if (isconnected) {
//			db = mongoClient.getDB(dataBaseName);
//			table = db.getCollection(tableName);
//		}
	}
	
	//Read properties from file 
	private void readProperties(String fileName) throws Exception
	{
		Properties properties = new Properties();
		InputStream input = null;

		try {
			properties = new Properties();
			input = Database.class.getClassLoader().getResourceAsStream(fileName);
			properties.load(input);

			//ftp_port = Integer.parseInt(prop.getProperty("ftp.port"));
			hostname  	 =	properties.getProperty("hostName");
			port		 =	Integer.parseInt(properties.getProperty("port"));
			dataBaseName =	properties.getProperty("dataBase");
			tableName	 =  properties.getProperty("collection");
			
		} catch (IOException ex) {
			System.out.println("Error while reading properties " + ex.getMessage());
		} 

	}

	private boolean connectToDb(String hostname){
		try {
			if (isInternetReachable()) {
		        MongoClientURI uri  = new MongoClientURI(hostname); 
		        MongoClient client = new MongoClient(uri);
		        db = client.getDB(uri.getDatabase());
		        table = db.getCollection(tableName);
		        System.out.println("I connected to online mongodb");
			}
			else{
				mongoClient = new MongoClient( hostname );	
				db = mongoClient.getDB(dataBaseName);
				table = db.getCollection(tableName);
				System.out.println("I connected to local mongodb");
			}
//			mongoClient = new MongoClient( hostname );	
//			db = mongoClient.getDB(dataBaseName);
//			table = db.getCollection(tableName);
//			System.out.println("I connected to local mongodb");

			return true; 

		} catch (Exception e) {
			System.out.println("ERROR WHILE CONNECTION TO DB \n" + e.getMessage());
			return false; 
		}

	}

	public void writeToDb(String name, int point)
	{
		/**** Insert ****/
		// create a document to store key and value
		document = new BasicDBObject();
		document.append("id", table.count() + 1); // Auto Increment id 
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
		query.put("id", id);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("Point", newPoint);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		table.update(query, updateObj);
	}

	
	/**
	 * Return DBCursor Object with descending order
	 */
	public DBCursor getDBCursor()
	{
		DBCursor cursor = table.find().sort(new BasicDBObject("Point",-1));  
		
		System.out.println("Length  of collection is " + cursor.length());
		
		return cursor;
		
	}
	public int getCollectionLength()
	{
		return getDBCursor().length();
	}
	
	public boolean isInternetReachable() {
		Process p1;
		int returnVal = -1;
		boolean reachable;
		try {
			p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
			returnVal = p1.waitFor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		}
		
		 reachable = (returnVal==0);
		 return reachable;
	}
	public void checkCollection(final String collectionName) {
		 boolean collectionExists = db.collectionExists(collectionName);
		 if (!collectionExists) {
			 document = new BasicDBObject();
				document.append("id", 0); // Auto Increment id 
				document.put("Name", "");
				document.put("Point", 0);
				document.put("createdDate", new Date());
				table.insert(document); // If the collection does not exist, then the insert() method will create the collection.
		 }else
		 {
			 updatePoints(0, 0);
		 }
	}

}
