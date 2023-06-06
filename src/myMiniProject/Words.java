package myMiniProject;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * This class is used for ...
 * @autor Esteban Camilo Martinez Urbano - esteban.urbano@correounivalle.edu.co
 * @autor David Camilo Ordoñez Marin - david.camilo.ordonez@correounivalle.edu.co
 * @version v.1.0.0 date:05/06/2023
 */

public class Words {
    private ArrayList<String> dictionary;
    private final ArrayList<String> RightWords;
    private final ArrayList<String> WrongWords;

    public Words() {

        dictionary = new ArrayList<>();
        RightWords = new ArrayList<>();
        WrongWords = new ArrayList<>();

        FileManager fileManager = new FileManager();
        dictionary = fileManager.readFiles("ListaPalabras");
    }

    public ArrayList<String> PalabrasCorrectas(int nroPalabras) {
        IntStream.range(0, nroPalabras).mapToObj(i -> new Random()).mapToInt(random -> random.nextInt(dictionary.size())).forEachOrdered(auxIndex -> {
            RightWords.add(dictionary.get(auxIndex));
            dictionary.remove(auxIndex);
        });
        return RightWords;
    }

    public ArrayList<String> PalabrasIncorrectas(int nroPalabras) {

        IntStream.range(0, nroPalabras).mapToObj(i -> new Random()).mapToInt(random -> random.nextInt(dictionary.size())).forEach(auxIndex -> {
            WrongWords.add(dictionary.get(auxIndex));
            dictionary.remove(auxIndex);
        });
        return WrongWords;
    }
}