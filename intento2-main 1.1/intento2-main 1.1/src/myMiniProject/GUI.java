package myMiniProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class is used for ...
 * @autor Esteban Camilo Martinez Urbano - esteban.urbano@correounivalle.edu.co
 * @autor David Camilo Ordoñez Marin - david.camilo.ordonez@correounivalle.edu.co
 * @version v.1.0.0 date:05/06/2023
 */

public class GUI extends JFrame {
    private static final String Information = "Se te van a presentar una serie de palabras de una en una"
            +"\n y tendras que memorizarlas, en seguida se te presentaran"
            +"\n el doble de palabras presentadas anteriormente, pero con"
            +"\n ciertas palabras que no se encontraban en la primera lista"
            +"\n tu funcion sera determinar que palabras se encontraban en"
            +"\n la primera lista y cuales no, por cada nivel el numero de"
            +"\n palabras se ira duplicando consecutivamente. !SUERTE¡";

    private static final String Info1 =" Puede salir cuando desee.\n"
            +  "si la partida no ha terminado la próxima vez que ingreses se iniciará la misma. ";

    private Header header;
    private Controller controller;
    private Escucha escucha;
    private JPanel initiationPanel,gamePanel,buttonsPanel,wordsPanel,optionPanel;
    private JTextField box;
    private JTextArea messages;
    private JButton ok,help,exit,instructions,yes,start, no,continueButton,newGame,ContinueButton,back,OK;
    private JLabel userNameLabel,levelLabel,timeLabel,wordLabel;
    private String userName;
    private Timer timer;
    private GridBagConstraints constraints, layoutGame;
    private GUI gui;

    public GUI() {
        initGUI();
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/fondo3.jpg"));
        JLabel etiquetaFondo = new JLabel(imageIcon);
        etiquetaFondo.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(etiquetaFondo, BorderLayout.CENTER);

    }

    private void initGUI() {

        //Set up JFrame Container's Layout
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        //Create Listener Object or Control Object
        escucha = new Escucha();
        controller = new Controller();

        header = new Header("juego:I KNOW THAT WORD", Color.BLACK);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.CENTER;
        this.add(header, constraints);

        //Boton de ayuda
        help = new JButton("Ayuda");
        help.addActionListener(escucha);
        help.setPreferredSize(new Dimension(100, 80));
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(help, constraints);

        //Boton de salir
        exit = new JButton("Salir");
        exit.addActionListener(escucha);
        exit.setPreferredSize(new Dimension(110, 70));
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(exit, constraints);

        //panel que contiene el label del usuario, la entrada de texto y el botón de confirmación
        initiationPanel = new JPanel(new GridBagLayout()); // Set up JPanel Container's Layout
        initiationPanel.setPreferredSize(new Dimension(999, 666));
        initiationPanel.setOpaque(false);
        initiationPanel.setBackground(new Color(255,255,255,0));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(initiationPanel, constraints);
        NewGameAndContinue();
        revalidate();
        repaint();
    }

    public void NewGameAndContinue(){
        layoutGame = new GridBagConstraints();
        
        /** new game button */
        newGame = new JButton("Nuevo juego");
        newGame.addActionListener(escucha);
        newGame.setPreferredSize(new Dimension(110, 30));
        newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGame.setBorder(new EmptyBorder(100, 0, 100, 0));
        //newGame.setBackground(new Color(255,255,255,0));
        layoutGame.gridx = 1;
        layoutGame.gridy = 1;
        layoutGame.gridwidth = 1;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.CENTER;
        initiationPanel.add(newGame, layoutGame);

        /** continue game button */
        ContinueButton = new JButton("Continuar");
        ContinueButton.addActionListener(escucha);
        ContinueButton.setPreferredSize(new Dimension(110, 30));
        ContinueButton.setBorder(new EmptyBorder(100, 0, 100, 0));
        //ContinueButton.setBackground(new Color(255,255,255,0));
        layoutGame.gridx = 1;
        layoutGame.gridy = 2;
        layoutGame.gridwidth = 1;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.CENTER;
        initiationPanel.add(ContinueButton, layoutGame);
    }

    public void Components() {

        GridBagConstraints layoutStar = new GridBagConstraints();//Componente del layoutGame
        userNameLabel = new JLabel("Nombre de Usuario");
        layoutStar.gridx = 0;
        layoutStar.gridy = 0;
        layoutStar.gridwidth = 2;
        layoutStar.fill = GridBagConstraints.NONE;
        layoutStar.anchor = GridBagConstraints.CENTER;
        initiationPanel.add(userNameLabel, layoutStar);

        //Box
        box = new JTextField();
        box.setPreferredSize(new Dimension(260, 45));
        box.setFont(new Font("Arial ", Font.PLAIN, 32));
        layoutStar.gridx = 0;
        layoutStar.gridy = 1;
        layoutStar.gridwidth = 1;
        layoutStar.fill = GridBagConstraints.NONE;
        layoutStar.anchor = GridBagConstraints.LINE_END;
        initiationPanel.add(box, layoutStar);

        //Boton Aceptar
        ok = new JButton("Aceptar");
        ok.addActionListener(escucha);
        ok.setPreferredSize(new Dimension(109, 59));
        ok.setBorderPainted(false);
        ok.setContentAreaFilled(false);
        layoutStar.gridx = 1;
        layoutStar.gridy = 1;
        layoutStar.gridwidth = 1;
        layoutStar.fill = GridBagConstraints.NONE;
        layoutStar.anchor = GridBagConstraints.LINE_START;
        initiationPanel.add(ok, layoutStar);

        //Boton Atras
        back = new JButton("Atras");
        back.addActionListener(escucha);
        back.setPreferredSize(new Dimension(100, 100));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        layoutStar.gridx = 1;
        layoutStar.gridy = 2;
        layoutStar.gridwidth = 1;
        layoutStar.fill = GridBagConstraints.NONE;
        layoutStar.anchor = GridBagConstraints.CENTER;
        initiationPanel.add(back, layoutStar);
        revalidate();
        repaint();
    }

    //Continuacion de la configuracion y visualizacion de los componentesd de la interfaz
    public void ContinueComponents() {

        GridBagConstraints layoutStar = new GridBagConstraints();//Componente del layoutGame
        userNameLabel = new JLabel("Nombre de Usuario a buscar");
        layoutStar.gridx = 0;
        layoutStar.gridy = 0;
        layoutStar.gridwidth = 2;
        layoutStar.fill = GridBagConstraints.NONE;
        layoutStar.anchor = GridBagConstraints.CENTER;
        initiationPanel.add(userNameLabel, layoutStar);

        //Box
        box = new JTextField();
        box.setPreferredSize(new Dimension(260, 45));
        box.setFont(new Font("Arial ", Font.PLAIN, 32));
        layoutStar.gridx = 0;
        layoutStar.gridy = 1;
        layoutStar.gridwidth = 1;
        layoutStar.fill = GridBagConstraints.NONE;
        layoutStar.anchor = GridBagConstraints.LINE_END;
        initiationPanel.add(box, layoutStar);

        //Boton Aceptar
        OK = new JButton("Aceptar");
        OK.addActionListener(escucha);
        OK.setPreferredSize(new Dimension(109, 59));
        OK.setBorderPainted(false);
        OK.setContentAreaFilled(false);
        layoutStar.gridx = 1;
        layoutStar.gridy = 1;
        layoutStar.gridwidth = 1;
        layoutStar.fill = GridBagConstraints.NONE;
        layoutStar.anchor = GridBagConstraints.LINE_START;
        initiationPanel.add(OK, layoutStar);

        //Boton Atras
        back = new JButton("Atras");
        back.addActionListener(escucha);
        back.setPreferredSize(new Dimension(100, 100));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        layoutStar.gridx = 1;
        layoutStar.gridy = 2;
        layoutStar.gridwidth = 1;
        layoutStar.fill = GridBagConstraints.NONE;
        layoutStar.anchor = GridBagConstraints.CENTER;
        initiationPanel.add(back, layoutStar);
        revalidate();
        repaint();
    }

    public void StartGame() {
        gamePanel = new JPanel();//Crea el panel con la imagen
        gamePanel.setLayout(new GridBagLayout());//Set up JPanel Container's Layout
        layoutGame = new GridBagConstraints();//Componente del layoutGame del gamePanel
        gamePanel.setPreferredSize(new Dimension(769, 407));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(gamePanel, constraints);

        messages = new JTextArea("   ¡¡Que Tal " + userName + "!!\n" +
                "  Usted Esta en el nivel " + controller.GetCurrentLevel() + "\n   Presiona JUGAR para iniciar el juego");
        messages.setEditable(false);
        messages.setLineWrap(true);
        messages.setWrapStyleWord(true);
        messages.setBackground(new Color(255,255,255,0));
        messages.setOpaque(true);
        messages.setPreferredSize(new Dimension(405, 155));
        messages.setFont(new Font("Impact", Font.PLAIN, 27));
        layoutGame.gridx = 0;
        layoutGame.gridy = 0;
        layoutGame.gridwidth = 1;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.CENTER;
        gamePanel.add(messages, layoutGame);
        StartAndInstructionButtons();
        revalidate();
        repaint();
    }

    //Configura y agrega los botones de inicio e instrucciones a la interfaz.
    //Este método crea un panel para contener los botones, configura sus propiedades
    public void StartAndInstructionButtons() {

        buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(977, 150));
        buttonsPanel.setOpaque(false);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(buttonsPanel, constraints);

        //Boton de instrucciones del juego
        instructions = new JButton("Instruciones");
        instructions.addActionListener(escucha);
        instructions.setPreferredSize(new Dimension(280, 85));
        instructions.setBorderPainted(false);
        instructions.setContentAreaFilled(false);
        buttonsPanel.add(instructions);

        //Boton de inicio del juego
        start = new JButton("Jugar");
        start.addActionListener(escucha);
        start.setPreferredSize(new Dimension(280, 85));
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        buttonsPanel.add(start);
    }

    public void Components2() {

        //Configura restricciones de diseño para la etiqueta de nivel
        levelLabel = new JLabel("NIVEL: " + Integer.toString(controller.GetCurrentLevel()));
        levelLabel.setFont(new Font("Impact", Font.PLAIN, 27));
        layoutGame.gridx = 0;
        layoutGame.gridy = 0;
        layoutGame.gridwidth = 1;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.LINE_START;
        gamePanel.add(levelLabel, layoutGame);

        //Caracteristicas de diseño en la etiqueta de tiempo
        timeLabel = new JLabel("00:00");
        timeLabel.setFont(new Font("Impact", Font.PLAIN, 27));
        layoutGame.gridx = 1;
        layoutGame.gridy = 0;
        layoutGame.gridwidth = 1;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.LINE_END;
        gamePanel.add(timeLabel, layoutGame);

        //Se configura y agrega el panel de palabras a la interfaz de juego
        wordsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints layoutPanelPalabras = new GridBagConstraints();
        wordsPanel.setPreferredSize(new Dimension(696, 357));
        wordsPanel.setOpaque(false);
        layoutGame.gridx = 0;
        layoutGame.gridy = 1;
        layoutGame.gridwidth = 2;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.CENTER;
        gamePanel.add(wordsPanel, layoutGame);

        //Se configura la etiqueta de palabra y se agrega alpanel de palabras
        wordLabel = new JLabel();
        wordLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        layoutPanelPalabras.gridx = 0;
        layoutPanelPalabras.gridy = 0;
        layoutPanelPalabras.gridwidth = 1;
        layoutPanelPalabras.fill = GridBagConstraints.NONE;
        layoutPanelPalabras.anchor = GridBagConstraints.CENTER;
        wordsPanel.add(wordLabel, layoutPanelPalabras);
        timer = new Timer(1000, escucha);
        revalidate();
        repaint();
    }

    public void Components3() {
        messages.setText("\n               ¡Es tu momento! \n   Demuestra tu capacidad\n   " +
                "memorizar ");
        messages.setBackground(new Color(255,255,255,0));
        messages.setPreferredSize(new Dimension(400, 180));
        messages.setForeground(Color.RED);
        layoutGame.gridx = 0;
        layoutGame.gridy = 0;
        layoutGame.gridwidth = 1;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.CENTER;
        gamePanel.add(messages, layoutGame);

        continueButton = new JButton("Continuar");
        continueButton.addActionListener(escucha);
        continueButton.setPreferredSize(new Dimension(200, 65));
        continueButton.setBorderPainted(false);
        continueButton.setContentAreaFilled(false);
        layoutGame.gridx = 0;
        layoutGame.gridy = 1;
        layoutGame.gridwidth = 1;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.LINE_END;
        gamePanel.add(continueButton, layoutGame);
        revalidate();
        repaint();
    }
    
    public void Components4() {

        //Se agrega el panel de opciones al panel de juego
        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(690, 90));
        optionPanel.setOpaque(false);
        layoutGame.gridx = 0;
        layoutGame.gridy = 2;
        layoutGame.gridwidth = 2;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.CENTER;
        gamePanel.add(optionPanel, layoutGame);

        // Agregar el boton "Si" al panel de juego
        yes = new JButton("Si");
        yes.addActionListener(escucha);
        yes.setPreferredSize(new Dimension(85, 85));
        yes.setBorderPainted(false);
        yes.setContentAreaFilled(false);
        optionPanel.add(yes);

        //Agregar el boton "No" al panel de juego
        no = new JButton("No");
        no.addActionListener(escucha);
        no.setPreferredSize(new Dimension(85, 85));
        no.setBorderPainted(false);
        no.setContentAreaFilled(false);
        optionPanel.add(no);
        revalidate();
        repaint();
    }

    //Metodo que continua el siguiente nivel del juego
    public void ContinueLevel() {
        String textoFinal = "";
        int aciertos = controller.GetHits();
        int porcentaje = controller.percentHits();
        controller.SetApprovedLevels();
        if (controller.GetPassLevel()) {
            textoFinal = "\n               Has superado el nivel. \n   Número de aciertos: " + aciertos +
                    "\n   porcentaje: " + porcentaje + "%";
        } else {
            textoFinal = "\n               No has superado el nivel. \n   Número de aciertos: " + aciertos +
                    "\n   porcentaje: " + porcentaje + "%";
        }
        messages.setText(textoFinal);
        layoutGame.gridx = 0;
        layoutGame.gridy = 0;
        layoutGame.gridwidth = 1;
        layoutGame.fill = GridBagConstraints.NONE;
        layoutGame.anchor = GridBagConstraints.CENTER;
        gamePanel.add(messages, layoutGame);
        start.setVisible(true);
        System.out.println(textoFinal);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    //Contiene la lógica para responder a los eventos de acción, como el temporizador o los botones.
    private class Escucha implements ActionListener {
        private int counter = -1, fase;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == timer) {
                timeLabel.setText("00:0" + counter);
                counter++;

                if (fase == 1) {
                    if (counter > 5) {
                        counter = 1;
                        wordLabel.setText(controller.GetWordsMemorize());
                    }
                    if (Objects.equals(wordLabel.getText(), "")) {
                        timer.stop();
                        gamePanel.removeAll();
                        revalidate();
                        repaint();
                        Components3();
                    }
                }
                if (fase == 2) {
                    if (counter > 7) {
                        wordLabel.setText(controller.getRandomWords());
                        counter = 0;
                    }
                    if (Objects.equals(wordLabel.getText(), "")) {
                        timer.stop();
                        gamePanel.removeAll();
                        revalidate();
                        repaint();
                        ContinueLevel();
                    }
                }
            }
            //si se presiona el boton new game se remueve el panel de inicio
            if (e.getSource()==newGame){
                initiationPanel.removeAll();
                Components();
                revalidate();
                repaint();
            }
            //Si se presiona el botón "ContinueButton", se remueve el panel de inicio
            if(e.getSource()==ContinueButton){
                initiationPanel.removeAll();
                ContinueComponents();
                revalidate();
                repaint();
            }
            //Si se presiona el botón "back", se llama al método main(null) de la clase gui para regresar a la pantalla anterior.
            if(e.getSource()==back){gui.main(null);}
            //Si se presiona el botón "exit", se llama al método System.exit(0) para cerrar la aplicación.
            if (e.getSource() == exit) System.exit(0);
            //Si se presiona el botón "help", se muestra un cuadro de diálogo de información con el contenido de la variable Info1.
            if (e.getSource() == help)
                JOptionPane.showMessageDialog(null, Info1, null, JOptionPane.INFORMATION_MESSAGE);
            //Configuracion al prfesionar el boton ok
            if (e.getSource() == ok) {
                userName = box.getText();
                if(userName.isEmpty()){
                    JOptionPane.showMessageDialog(initiationPanel, "Necesitas un nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    remove(initiationPanel);
                    Player jugador = new Player(userName);
                    if(jugador.PlayerExist()){
                        JOptionPane.showMessageDialog(initiationPanel, "El usuario ya existe, ingresa un usurio diferente", "Error", JOptionPane.ERROR_MESSAGE);
                        add(initiationPanel);
                    }else{
                        controller.SearchPlayer(userName);
                        StartGame();
                        revalidate();
                        repaint();
                    }
                }
            }
            if (e.getSource() == OK) {
                userName = box.getText();
                if(userName.isEmpty()){
                    JOptionPane.showMessageDialog(initiationPanel, "Necesitas un nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    remove(initiationPanel);
                    Player jugador = new Player(userName);
                    if(jugador.PlayerExist()){
                        remove(initiationPanel);
                        controller.SearchPlayer(userName);
                        StartGame();
                        revalidate();
                        repaint();
                    }else{
                        JOptionPane.showMessageDialog(initiationPanel, "El usuario no existe, ingresa un usurio existente", "Error", JOptionPane.ERROR_MESSAGE);
                        add(initiationPanel);
                    }
                }
            }
            //Si se presiona el botón "instructions", se muestra un cuadro de diálogo de información con el contenido de la variable Information.
            if (e.getSource() == instructions) {
                JOptionPane.showMessageDialog(null, Information, null, JOptionPane.PLAIN_MESSAGE);
            }
            //Si se presiona el botón "start", se remueve el mensaje (messages) del panel de juego (gamePanel).
            if (e.getSource() == start) {
                gamePanel.remove(messages);
                Components2();
                wordLabel.setText(controller.GetWordsMemorize());
                start.setVisible(false);
                fase = 1;
                counter = 1;
                timer.start();
            }
            //Si se presiona el botón "yes", se llama al método ValidateCorrectWord(wordLabel.getText()) de la clase controller.
            if (e.getSource() == yes) {
                controller.ValidateCorrectWord(wordLabel.getText());
                wordLabel.setText(controller.getRandomWords());
                counter = 1;
                revalidate();
                repaint();
            }
            //Si se presiona el botón "no", se llama al método ValidateWrongWord(wordLabel.getText()) de la clase controller
            if (e.getSource() == no) {
                controller.ValidateWrongWord(wordLabel.getText());
                wordLabel.setText(controller.getRandomWords());
                counter = 1;
                revalidate();
                repaint();
            }
            //Si se presiona el botón "continueButton", se remueven el mensaje (messages) y el botón (continueButton) del panel de juego (gamePanel).
            if (e.getSource() == continueButton) {
                gamePanel.remove(messages);
                gamePanel.remove(continueButton);
                fase = 2;
                revalidate();
                repaint();
                Components2();
                wordsPanel.setPreferredSize(new Dimension(690, 260));
                Components4();
                wordLabel.setText(controller.getRandomWords());
                timer.start();
            }
        }
    }
}