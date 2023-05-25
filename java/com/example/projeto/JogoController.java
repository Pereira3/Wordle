package com.example.projeto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;

public class JogoController{
    //-----------------------------------------------------VARIAVEIS CODIGO-----------------------------------------------------
    @FXML
    private Label L00, L01, L02, L03, L04, L05, L06, LCheck;
    @FXML
    private Label L10, L11, L12, L13, L14, L15, L16;
    @FXML
    private Label L20, L21, L22, L23, L24, L25, L26;
    @FXML
    private Label L30, L31, L32, L33, L34, L35, L36;
    @FXML
    private Label L40, L41, L42, L43, L44, L45, L46;
    @FXML
    private Label L50, L51, L52, L53, L54, L55, L56;
    @FXML
    private Label L60, L61, L62, L63, L64, L65, L66;
    @FXML
    private TextField TFGuess;
    @FXML
    private Button BCheck;
    @FXML
    private TextArea TALB;
    @FXML
    private Label LPontuação;

    //-----------------------------------------------------VARIAVEIS PALAVRA-----------------------------------------------------
    //-----PORTUGUES-----
    String[] SPPalavras3 = {"Voo", "Fim", "Paz", "Voz", "Lua", "Lei", "Bem", "Mal", "Sal", "Par"};
    String PPalavra3 = (SPPalavras3[(int)(Math.random()*SPPalavras3.length)]);
    String[] SPPalavras4 = {"Nome", "Jogo", "Goma", "Sede", "Fome", "Doce", "Foco", "Rima", "Nexo", "Mito"};
    String PPalavra4 = (SPPalavras4[(int)(Math.random()*SPPalavras4.length)]);
    String[] SPPalavras5 = {"Mútua", "Plena", "Êxito", "Audaz", "Justo", "Muito", "Anexo", "Sonho", "Etnia", "Lapso"};
    String PPalavra5 = SPPalavras5[(int)(Math.random()*SPPalavras5.length)];
    String[] SPPalavras6 = {"Fóssil", "Lápide", "Múmias", "Fábula", "Cálice", "Tóxico", "Míssil", "Órbita", "Nítido", "Rígido"};
    String PPalavra6 = SPPalavras6[(int)(Math.random()*SPPalavras6.length)];
    String[] SPPalavras7 = {"Exótico", "Místico", "Híbrido", "Hóspede", "Vértice", "Íntegro", "Círculo", "Vírgula", "Límpido", "Ríspido"};
    String PPalavra7 = SPPalavras7[(int)(Math.random()*SPPalavras7.length)];
    //-----Inglês-----
    String[] SIPalavras3 = {"Dog", "Cat", "Sun", "Cup", "Pen", "Bus", "Bat", "Hat", "Egg", "Jam"};
    String IPalavra3 = (SIPalavras3[(int)(Math.random()*SIPalavras3.length)]);
    String[] SIPalavras4 = {"Fish", "Bird", "Lion", "Ball", "Tree", "Fork", "Hand", "Book", "Moon", "Rain"};
    String IPalavra4 = (SIPalavras4[(int)(Math.random()*SIPalavras4.length)]);
    String[] SIPalavras5 = {"Apple", "Beach", "Chair", "Dance", "Eagle", "Flame", "Grass", "Horse", "Jelly", "Karma"};
    String IPalavra5 = SIPalavras5[(int)(Math.random()*SIPalavras5.length)];
    String[] SIPalavras6 = {"Banana", "Coffee", "Doctor", "Family", "Garden", "Hammer", "Island", "Jacket", "Kitchen", "Laptop"};
    String IPalavra6 = SIPalavras6[(int)(Math.random()*SIPalavras6.length)];
    String[] SIPalavras7 = {"Bicycle", "Chicken", "Diamond", "Elephant", "Firewall", "Gymnast", "Husband", "Justice", "Library", "Monitor"};
    String IPalavra7 = SIPalavras7[(int)(Math.random()*SIPalavras7.length)];
    //-----Francês-----
    String[] SFPalavras3 = {"Oui", "Non", "Air", "Rue", "Sac", "Fin", "Dos", "Mer", "Eau", "Art"};
    String FPalavra3 = (SFPalavras3[(int)(Math.random()*SFPalavras3.length)]);
    String[] SFPalavras4 = {"Joie", "Rose", "Miel", "Ciel", "Noir", "Lune", "Vent", "Rire", "Nuit", "Jour"};
    String FPalavra4 = (SFPalavras4[(int)(Math.random()*SFPalavras4.length)]);
    String[] SFPalavras5 = {"Danse", "Chien", "Amour", "Musée", "Heure", "École", "Frère", "Ombre", "Poche", "Litre"};
    String FPalavra5 = SFPalavras5[(int)(Math.random()*SFPalavras5.length)];
    String[] SFPalavras6 = {"Jardin", "Gagner", "Fleur", "Gâteau", "Chaise", "Chante", "Écrire", "Parler", "Savoir", "Jouets"};
    String FPalavra6 = SFPalavras6[(int)(Math.random()*SFPalavras6.length)];
    String[] SFPalavras7 = {"Bonjour", "Cuisine", "Lumière", "Février", "Grenade", "Heureux", "Honneur", "Lampion", "Musique", "Nuancer"};
    String FPalavra7 = SFPalavras7[(int)(Math.random()*SFPalavras7.length)];

    String PalavraJogo = "";
    String PalavraJogoSA = "";

    int Tentativas = DificuldadeController.Dificuldade - 1;
    int conta_amarelos = 0, conta_cinzas = 0;
    public ArrayList<Utilizadores> Users = new ArrayList<>();

    public void initialize(){

        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/main/java/com/example/projeto/Utilizadores.dat"));
            Users = (ArrayList<Utilizadores>) is.readObject();
        }catch (IOException | ClassNotFoundException exc){
            System.out.println(exc.getMessage());
        }

        System.out.println(Users);

        if(DificuldadeController.Idioma.equals("Português")){
            if(DificuldadeController.Dificuldade == 3){
                Dificuldade3();
                PalavraJogo = PPalavra3;
                PalavraJogoSA = Normalizer.normalize(PPalavra3, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 4){
                Dificuldade4();
                PalavraJogo = PPalavra4;
                PalavraJogoSA = Normalizer.normalize(PPalavra4, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 5){
                Dificuldade5();
                PalavraJogo = PPalavra5;
                PalavraJogoSA = Normalizer.normalize(PPalavra5, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 6){
                Dificuldade6();
                PalavraJogo = PPalavra6;
                PalavraJogoSA = Normalizer.normalize(PPalavra6, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 7){
                Dificuldade7();
                PalavraJogo = PPalavra7;
                PalavraJogoSA = Normalizer.normalize(PPalavra7, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else{
                System.out.println("Erro Dificuldade");
            }
        }else if(DificuldadeController.Idioma.equals("Inglês")){
            if(DificuldadeController.Dificuldade == 3){
                Dificuldade3();
                PalavraJogo = IPalavra3;
                PalavraJogoSA = Normalizer.normalize(IPalavra3, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 4){
                Dificuldade4();
                PalavraJogo = IPalavra4;
                PalavraJogoSA = Normalizer.normalize(IPalavra4, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 5){
                Dificuldade5();
                PalavraJogo = IPalavra5;
                PalavraJogoSA = Normalizer.normalize(IPalavra5, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 6){
                Dificuldade6();
                PalavraJogo = IPalavra6;
                PalavraJogoSA = Normalizer.normalize(IPalavra6, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 7){
                Dificuldade7();
                PalavraJogo = IPalavra7;
                PalavraJogoSA = Normalizer.normalize(IPalavra7, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else{
                System.out.println("Erro Dificuldade");
            }
        }else if(DificuldadeController.Idioma.equals("Francês")){
            if(DificuldadeController.Dificuldade == 3){
                Dificuldade3();
                PalavraJogo = FPalavra3;
                PalavraJogoSA = Normalizer.normalize(FPalavra3, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 4){
                Dificuldade4();
                PalavraJogo = FPalavra4;
                PalavraJogoSA = Normalizer.normalize(FPalavra4, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 5){
                Dificuldade5();
                PalavraJogo = FPalavra5;
                PalavraJogoSA = Normalizer.normalize(FPalavra5, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 6){
                Dificuldade6();
                PalavraJogo = FPalavra6;
                PalavraJogoSA = Normalizer.normalize(FPalavra6, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }else if(DificuldadeController.Dificuldade == 7){
                Dificuldade7();
                PalavraJogo = FPalavra7;
                PalavraJogoSA = Normalizer.normalize(FPalavra7, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos
            }
        }else {
            System.out.println("Erro Dificuldade");
        }

        String temp = "";
        for (int i = 0; i < Users.size(); i++) {
            temp += Users.get(i).getNome() + ": " + Users.get(i).getPontuação() + "\n";
        }
        TALB.setText(temp);
    }

    public void Jogo(){

        Label[] HBox = {L00, L01, L02, L03, L04, L05, L06};
        Label[] HBox1 = {L10, L11, L12, L13, L14, L15, L16};
        Label[] HBox2 = {L20, L21, L22, L23, L24, L25, L26};
        Label[] HBox3 = {L30, L31, L32, L33, L34, L35, L36};
        Label[] HBox4 = {L40, L41, L42, L43, L44, L45, L46};
        Label[] HBox5 = {L50, L51, L52, L53, L54, L55, L56};
        Label[] HBox6 = {L60, L61, L62, L63, L64, L65, L66};

        Label[][] MHBox = {HBox, HBox1, HBox2, HBox3, HBox4, HBox5, HBox6};

        String input = Normalizer.normalize(TFGuess.getText(), Normalizer.Form.NFD).replaceAll("\\p{M}", ""); //Remover Acentos

        if(input.length() != DificuldadeController.Dificuldade){
            LCheck.setStyle("-fx-text-fill: red;");
            LCheck.setText("Palavra Indroduzida Incorreta...");
            TFGuess.setText("");
        }else{
            if(Tentativas >= 0) {
                //INTRODUZ O TEXTO E O STYLE NAS LABELS DO JOGO
                for (int i = 0; i < DificuldadeController.Dificuldade; i++) {
                    for (int j = (DificuldadeController.Dificuldade-1); j > 0; j--) {
                        MHBox[j][i].setText(MHBox[j - 1][i].getText());
                        MHBox[j][i].setStyle(MHBox[j - 1][i].getStyle());
                    }
                }
                //Corre as palavras
                for (int j = 0; j < input.length(); j++) {
                    //substring começa em i e acaba em i+1 de modo a recolher letra a letra
                    String letra = input.substring(j, j + 1);
                    HBox[j].setText(letra);

                    if ((letra.toUpperCase()).equals((PalavraJogoSA.toUpperCase()).substring(j, j + 1))) {
                        HBox[j].setStyle("-fx-background-color: #6aa84f; -fx-background-radius: 10; -fx-border-radius: 10;");
                    } else if ((PalavraJogoSA.toUpperCase()).contains(letra.toUpperCase())){
                        HBox[j].setStyle("-fx-background-color: #f1c232; -fx-background-radius: 10; -fx-border-radius: 10;");
                    } else {
                        HBox[j].setStyle("-fx-background-color: #999999; -fx-background-radius: 10; -fx-border-radius: 10;");
                    }
                }
                //ATUALIZA O Nº DE TENTATIVAS RESTANTES
                LCheck.setStyle("-fx-text-fill: black;");
                LCheck.setText("Tem mais " + Tentativas + " Tentativas.");
                Tentativas--;

                try {
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/main/java/com/example/projeto/Utilizadores.dat"));

                    String temp = "";

                    for (int i = 0; i < Users.size(); i++){

                        if(Users.get(i).getEmail().equals(SignLogController.email)){
                            if ((PalavraJogoSA.toUpperCase()).equals(input.toUpperCase())) {
                                BCheck.setVisible(false);
                                LCheck.setStyle("-fx-text-fill: green;");
                                LPontuação.setStyle("-fx-text-fill: green;");
                                LCheck.setText("Acertou...");
                                LPontuação.setText("+100 Pontos");
                                Users.get(i).setPontuação( Users.get(i).getPontuação() + 100 );
                            }
                            if (Tentativas == (-1) && !(PalavraJogoSA.toUpperCase()).equals(input.toUpperCase())) {
                                BCheck.setVisible(false);
                                LCheck.setStyle("-fx-text-fill: red;");
                                LCheck.setText("A Palavra era: " + PalavraJogo);

                                for (int j = 0; j < input.length(); j++) {

                                    String letra = input.substring(j, j + 1);

                                    if ((PalavraJogoSA.toUpperCase()).contains(letra.toUpperCase())){
                                        conta_amarelos ++;
                                    } else {
                                        conta_cinzas++;
                                    }
                                }

                                if(conta_amarelos >= 0){
                                    LPontuação.setStyle("-fx-text-fill: #f1c232;");
                                    LPontuação.setText("+"+conta_amarelos*10+" Pontos");
                                    Users.get(i).setPontuação( Users.get(i).getPontuação() + (conta_amarelos*10) );
                                }
                                if(conta_cinzas == input.length()){
                                    LPontuação.setStyle("-fx-text-fill: red;");
                                    LPontuação.setText("+0 Pontos");
                                }
                            }
                        }

                        temp += Users.get(i).getNome() + ": " + Users.get(i).getPontuação() + "\n";
                        os.writeObject(Users);
                    }
                    TALB.setText(temp);

                } catch (IOException e) {
                    LCheck.setStyle("-fx-text-fill: red;");
                    LCheck.setText("Erro ao associar Tentativas...");
                }
            }
            TFGuess.setText("");
        }
    }

    //-----------------------------------------------------MUDAR DE JANELAS-----------------------------------------------------
    private Stage stage;
    private Scene scene;
    public void SignLog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignLog.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void Dificuldades(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dificuldade.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //-----------------------------------------------------ALTERAR A TABELA LABELS-----------------------------------------------------
    public void Dificuldade3(){
        L03.setVisible(false); L04.setVisible(false); L05.setVisible(false); L06.setVisible(false);
        L13.setVisible(false); L14.setVisible(false); L15.setVisible(false); L16.setVisible(false);
        L23.setVisible(false); L24.setVisible(false); L25.setVisible(false); L26.setVisible(false);
        L33.setVisible(false); L34.setVisible(false); L35.setVisible(false); L36.setVisible(false);
        L43.setVisible(false); L44.setVisible(false); L45.setVisible(false); L46.setVisible(false);
        L53.setVisible(false); L54.setVisible(false); L55.setVisible(false); L56.setVisible(false);
        L63.setVisible(false); L64.setVisible(false); L65.setVisible(false); L66.setVisible(false);
        L30.setVisible(false); L31.setVisible(false); L32.setVisible(false);
        L40.setVisible(false); L41.setVisible(false); L42.setVisible(false);
        L50.setVisible(false); L51.setVisible(false); L52.setVisible(false);
        L60.setVisible(false); L61.setVisible(false); L62.setVisible(false);
    }
    public void Dificuldade4(){
        L04.setVisible(false); L05.setVisible(false); L06.setVisible(false);
        L14.setVisible(false); L15.setVisible(false); L16.setVisible(false);
        L24.setVisible(false); L25.setVisible(false); L26.setVisible(false);
        L34.setVisible(false); L35.setVisible(false); L36.setVisible(false);
        L44.setVisible(false); L45.setVisible(false); L46.setVisible(false);
        L54.setVisible(false); L55.setVisible(false); L56.setVisible(false);
        L64.setVisible(false); L65.setVisible(false); L66.setVisible(false);
        L40.setVisible(false); L41.setVisible(false); L42.setVisible(false); L43.setVisible(false);
        L50.setVisible(false); L51.setVisible(false); L52.setVisible(false); L53.setVisible(false);
        L60.setVisible(false); L61.setVisible(false); L62.setVisible(false); L63.setVisible(false);
    }
    public void Dificuldade5(){
        L05.setVisible(false); L06.setVisible(false);
        L15.setVisible(false); L16.setVisible(false);
        L25.setVisible(false); L26.setVisible(false);
        L35.setVisible(false); L36.setVisible(false);
        L45.setVisible(false); L46.setVisible(false);
        L55.setVisible(false); L56.setVisible(false);
        L65.setVisible(false); L66.setVisible(false);
        L50.setVisible(false); L51.setVisible(false); L52.setVisible(false); L53.setVisible(false); L54.setVisible(false);
        L60.setVisible(false); L61.setVisible(false); L62.setVisible(false); L63.setVisible(false); L64.setVisible(false);
    }
    public void Dificuldade6(){
        L06.setVisible(false);
        L16.setVisible(false);
        L26.setVisible(false);
        L36.setVisible(false);
        L46.setVisible(false);
        L56.setVisible(false);
        L66.setVisible(false);
        L60.setVisible(false); L61.setVisible(false); L62.setVisible(false); L63.setVisible(false); L64.setVisible(false); L65.setVisible(false);
    }
    public void Dificuldade7(){

    }


    //-----------------------------------------------------SUPLEMENTAR CARREGAR ENTER-----------------------------------------------------
    public void handleButtonKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Jogo();
        }
    }
}
