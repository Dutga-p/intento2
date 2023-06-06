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


    public ArrayList<String> readFiles(String _file) {

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
}