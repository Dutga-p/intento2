package myMiniProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used for ...
 * @autor Esteban Camilo Martinez Urbano - esteban.urbano@correounivalle.edu.co
 * @autor David Camilo Ordoñez Marin - david.camilo.ordonez@correounivalle.edu.co
 * @version v.1.0.0 date:05/06/2023
 */

public class Controller {

    private Words diccionario;

    private ArrayList<String> WordsToShow = new ArrayList<>();
    private boolean levelCounter;
    private Player myUser;

    String userName;
    int approvedLevels, currentLevel, cantPalabrasDelNivel, hits, counterCorrectWords, contadorPalabrasAleatorias;
    double percentHits;
    private ArrayList<String> correctWordLists = new ArrayList<>();
    private ArrayList<String> wrongWordLists = new ArrayList<>();
    private ArrayList<String> randomWordLists = new ArrayList<>();
    boolean newUser;

    public Controller() {}

    public void SearchPlayer(String nombreJugador) {
        diccionario = new Words();
        newUser = false;
        userName = nombreJugador;
        myUser = new Player(userName);
        if (myUser.PlayerExist()) {
            approvedLevels = myUser.GetLevelPlayer();
        } else {
            myUser.RegisterPlayer();
            newUser = true;
            approvedLevels = 0;
        }

        counterCorrectWords = 0;
        levelCounter = false;
        if (approvedLevels < 8) {
            currentLevel = approvedLevels + 1;
        } else {
            currentLevel = approvedLevels;
        }
        SetCurrentLevel();
    }

    private void SetCurrentLevel() {
        hits = 0;
        if (GetPassLevel()) {
            currentLevel++;
        }
        WordsPerLevel();
        PercentagesPerLevel();
        correctWordLists = diccionario.PalabrasCorrectas(cantPalabrasDelNivel / 2);
        wrongWordLists = diccionario.PalabrasIncorrectas(cantPalabrasDelNivel / 2);
        RandomWords();
    }

    private void WordsPerLevel() {
        switch (currentLevel) {
            case 1-> cantPalabrasDelNivel =20;
            case 2-> cantPalabrasDelNivel =40;
            case 3-> cantPalabrasDelNivel =50;
            case 4-> cantPalabrasDelNivel =60;
            case 5-> cantPalabrasDelNivel =70;
            case 6-> cantPalabrasDelNivel =80;
            case 7-> cantPalabrasDelNivel =100;
            case 8-> cantPalabrasDelNivel =120;
            case 9-> cantPalabrasDelNivel =140;
            case 10-> cantPalabrasDelNivel =200;
        }
    }

    private void RandomWords() {

        WordsToShow.addAll(correctWordLists);
        WordsToShow.addAll(wrongWordLists);
        ArrayList<String> auxiliary = WordsToShow;

        while (auxiliary.size() > 0) {
            Random random = new Random();
            String palabra = auxiliary.get(random.nextInt(auxiliary.size()));
            int index = auxiliary.indexOf(palabra);
            randomWordLists.add(palabra);
            auxiliary.remove(index);
        }
    }

    private void PercentagesPerLevel() {
        switch (currentLevel) {
            case 1, 2 -> percentHits = 0.7;
            case 3 -> percentHits = 0.75;
            case 4, 5 -> percentHits = 0.8;
            case 6 -> percentHits = 0.85;
            case 7, 8 -> percentHits = 0.9;
            case 9 -> percentHits = 0.95;
        }
    }

    public void ValidateCorrectWord(String palabra) {

        int i = 0;
        while (i < correctWordLists.size()) {
            String elementoListCorrecta = correctWordLists.get(i);
            if (elementoListCorrecta.equals(palabra)) {
                hits++;
                break;
            }
            i++;
        }
    }

    public void ValidateWrongWord(String palabra) {
        int i = 0, arraListPalabrasIncorrectasSize = wrongWordLists.size();
        while (i < arraListPalabrasIncorrectasSize) {
            String elementoListIncorrecta = wrongWordLists.get(i);
            if (elementoListIncorrecta.equals(palabra)) {
                hits++;
                break;
            }
            i++;
        }
    }

    public String GetWordsMemorize() {
        String palabraMemorizar = "";
        if (counterCorrectWords < correctWordLists.size()) {
            palabraMemorizar = correctWordLists.get(counterCorrectWords);
            counterCorrectWords++;
        }
        return palabraMemorizar;
    }

    public String getRandomWords() {
        String palabraAleatoria = "";
        if (contadorPalabrasAleatorias < randomWordLists.size()) {
            palabraAleatoria = randomWordLists.get(contadorPalabrasAleatorias);
            int index = randomWordLists.indexOf(palabraAleatoria);
            randomWordLists.remove(index);
        }
        return palabraAleatoria;
    }

    public int GetHits() {
        return hits;
    }

    public boolean GetPassLevel() {
        return levelCounter;
    }

    public int percentHits() {
        return ((hits * 100) / cantPalabrasDelNivel);
    }

    public int GetCurrentLevel() {
        return currentLevel;
    }

    public void SetApprovedLevels() {

        if (hits >= cantPalabrasDelNivel * percentHits) {
            approvedLevels = myUser.SetLevelPlayer();
            levelCounter = true;
            SetCurrentLevel();
            counterCorrectWords = 0;
        } else {
            levelCounter = false;
            counterCorrectWords = 0;
            SetCurrentLevel();
        }
    }
}