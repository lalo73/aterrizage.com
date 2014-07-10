package ar.edu.unq.persistencia1.services.neo4j.operations;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import ar.edu.unq.persistencia1.services.neo4j.RelTypes;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import java.util.ArrayList;
import java.util.List;

public class GetMessages implements NeoOperation<List<String>> {

    private Usuario user;

    public GetMessages (Usuario user){this.user = user;}


    public List<String> execute(){
        Node userNode = NeoManager.runInSession(new GetUserNode(this.user));
        List<String> nodes = new ArrayList<String>();
        for (Relationship relationship : userNode.getRelationships(RelTypes.SENDER)) {
            nodes.add((String) relationship.getOtherNode(userNode).getProperty("message"));
        }
        return nodes;


    }

}
