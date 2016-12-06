package dds.mongodb;

import com.mongodb.MongoClient;
import dds.softpoi.ElementoDeConsulta;
import dds.softpoi.Parametros;

import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

public class MongoDBConnection {

    private static MongoDBConnection instance = null;
    private final Datastore datastore;
    private static Parametros objParam = new Parametros();
    
    public static MongoDBConnection getInstance() {
        if (instance == null) {
            instance = new MongoDBConnection();
        }
        return instance;
    }

    private MongoDBConnection(){
        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("org.mongodb.morphia.example");
        
        // create the Datastore connecting to the default port on the local host
        this.datastore = morphia.createDatastore(new MongoClient(), objParam.getBaseMongoDB());
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return this.datastore;
    }
    
    public static void persistirElementoDeConsulta(ElementoDeConsulta unElementoDeConsulta){
    	Datastore datastore = MongoDBConnection.getInstance().getDatastore();
    	datastore.save(unElementoDeConsulta);
    }
    
    public static ArrayList<ElementoDeConsulta> recuperarElementoDeConsulta(){
    	Datastore datastore = MongoDBConnection.getInstance().getDatastore();
		Query<ElementoDeConsulta> query = datastore.find(ElementoDeConsulta.class);
		List<ElementoDeConsulta> items = query.asList();
		System.out.println("Total: " + items.size() + " registros de busquedas");
		//if (items.size() > 0){
		//	items.remove(0);
		//}
		
		
		return (ArrayList<ElementoDeConsulta>)items;
    }
    public static void dropearBase(){
    	Datastore datastore = MongoDBConnection.getInstance().getDatastore();
    	datastore.getDB().dropDatabase();
    }
    
}