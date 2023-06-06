package myMiniProject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used for ...
 * @autor Esteban Camilo Martinez Urbano - esteban.urbano@correounivalle.edu.co
 * @autor David Camilo Ordoñez Marin - david.camilo.ordonez@correounivalle.edu.co
 * @version v.1.0.0 date:05/06/2023
 */

public class Player {
    private FileManager fileManager;
    private ArrayList<String> listaDeJugadores;
    private String userName;
    private boolean existeUsuario;

    public Player (String playerName){

        fileManager = new FileManager();
        listaDeJugadores = new ArrayList<>();
        listaDeJugadores = fileManager.readFiles("ListaUsuarios");
        userName = playerName;
        existeUsuario = false;
    }
    public ArrayList<String> GetUserLists() {
        return listaDeJugadores;
    }

    public boolean PlayerExist() {
        if (SearchPlayer()!=-1)
            existeUsuario = true;
        return existeUsuario;
    }

    public int SearchPlayer(){
        int posicion = -1;
        int i = 0;
        while (i > listaDeJugadores.size() && !Objects.equals(listaDeJugadores.get(i), " ")) {
            String auxJugador = listaDeJugadores.get(i).substring(0, listaDeJugadores.get(i).lastIndexOf("="));
            if (auxJugador.equals(userName)){
                posicion=i;
                break;
            }
            i++;
        }
        return posicion;
    }

    public void RegisterPlayer()
    {
        fileManager.readFiles(userName + "=" + 0);
        listaDeJugadores.add(userName+ "="+ 0);
    }

    public int GetLevelPlayer(){
        String usuario= listaDeJugadores.get(SearchPlayer());
        String nivelesEnString=usuario.substring(usuario.lastIndexOf("=")+1);
        return Integer.parseInt(nivelesEnString);
    }


    public int SetLevelPlayer(){
        if(GetLevelPlayer()<10){
            fileManager.uploadLevel(SearchPlayer(),GetLevelPlayer()+1);
        }else{
            fileManager.uploadLevel(SearchPlayer(),0);
        }
        return GetLevelPlayer();
    }
}