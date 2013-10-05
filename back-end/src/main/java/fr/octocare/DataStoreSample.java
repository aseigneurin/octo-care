package fr.octocare;

import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import fr.octocare.entity.Octo;

public class DataStoreSample {

    public static void main(String[] args) throws Exception {

        String datasetId = "octo-care";

        ObjectifyService.register(Octo.class);
        Objectify objectify = ObjectifyService.ofy();
        List<Octo> octos = objectify.load().type(Octo.class).list();
        System.out.println(octos);

        // Datastore datastore = null;
        // try {
        // // Setup the connection to Google Cloud Datastore and infer credentials
        // // from the environment.
        // datastore = DatastoreFactory.get().create(DatastoreHelper.getOptionsfromEnv().dataset(datasetId).build());
        // } catch (GeneralSecurityException exception) {
        // System.err.println("Security error connecting to the datastore: " + exception.getMessage());
        // System.exit(1);
        // } catch (IOException exception) {
        // System.err.println("I/O error connecting to the datastore: " + exception.getMessage());
        // System.exit(1);
        // }

        // LookupRequest request = LookupRequest.newBuilder().build();
        // LookupResponse response = datastore.lookup(request);
        // System.out.println(response);

        // new Query("Octo");

        // Query.Builder q = Query.newBuilder();
        // q.addKindBuilder().setName("Octo");
        // RunQueryRequest request = RunQueryRequest.newBuilder().setQuery(q).build();
        // RunQueryResponse response = datastore.runQuery(request);
        // for (EntityResult result : response.getBatch().getEntityResultList()) {
        // System.out.println(result);
        // Entity entity = result.getEntity();
        // entity.getpr
        // }
    }
}
