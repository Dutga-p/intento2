package myMiniProject;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used for ...
 * @autor Esteban Camilo Martinez Urbano - esteban.urbano@correounivalle.edu.co
 * @autor David Camilo Ordoñez Marin - david.camilo.ordonez@correounivalle.edu.co
 * @version v.1.0.0 date:05/06/2023
 */

public class FileManager
{
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;
    public static final String usersList = "src/FilesTxt/Users.txt";
    public static final String wordLists = "src/FilesTxt/TwoHundredWords.txt";
    private ArrayList<String> nameAndLevel;
    private DefaultListModel usuariosModel;


    /**
     * Este método lee el archivo 'wordLists.txt' y retorna el arrayList con cada palabra del archivo
     * @return ArrayList leerAchivos
     */

        public ArrayList<String> readFiles(String _file)
    {

        ArrayList<String> texto = new ArrayList<>();

        String elArchivoLeido = "";
        if (Objects.equals(_file, "ListaPalabras")) {
            elArchivoLeido = wordLists;
        } else if (Objects.equals(_file, "ListaUsuarios")) {
            elArchivoLeido = usersList;
        }

        try {
            fileReader = new FileReader(elArchivoLeido);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                texto.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto;
    }


    public void writeText(String linea)
    {
        try {
            fileWriter = new FileWriter(usersList, true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadLevel(int posicion, int nivelNuevo)
    {
        try {
            ArrayList<String> usuariosActualizados = readFiles("ListaUsuarios");
            String usuarioAntiguo = usuariosActualizados.get(posicion);
            String usuarioActualizado = usuarioAntiguo.substring(0, usuarioAntiguo.lastIndexOf("=") + 1) + nivelNuevo;
            usuariosActualizados.remove(posicion);
            usuariosActualizados.add(posicion, usuarioActualizado);
            fileWriter = new FileWriter(usersList, false);
            output = new BufferedWriter(fileWriter);
            for (String usuariosActualizado : usuariosActualizados) {
                output.write(usuariosActualizado);
                output.newLine();

            }
            output.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public DefaultListModel nameAndLevel(){
        usuariosModel = new DefaultListModel<>();
        nameAndLevel = readFiles("ListaUsuarios");
        for (int i = 0; i < nameAndLevel.size() && !Objects.equals(nameAndLevel.get(i), " "); i++)
        {
            String eachUser = nameAndLevel.get(i);
            String[] datosUsuario = eachUser.split("=");
            String name = datosUsuario[0];
            String level = datosUsuario[1];
            String Player = name + " nivel "+ level;
            usuariosModel.addElement(Player);
        }
        return usuariosModel;
    }
}