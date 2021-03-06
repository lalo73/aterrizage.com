package ar.edu.unq.persistencia1.homes;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.operations.*;

public class SocialNetworkManager {

    public void addFriend(Usuario usuario, Usuario newFriend){
        NeoManager.runInSession(new AddFriend(usuario, newFriend));
    }

    public void getFriend(Usuario usuario){
        NeoManager.runInSession(new GetFriends(usuario));
    }

    public void sendMessage(Usuario usuario, Usuario receiver, String message){
        NeoManager.runInSession((new SendMessage(usuario,receiver,message)));
    }
    
    public void friends(Usuario usuario){
        NeoManager.runInSession((new GetAllFriends(usuario)));
    }

    public boolean areFriends(Usuario user, Usuario friend){
        return NeoManager.runInSession((new AreFriends(user, friend)));
    }
    
}
