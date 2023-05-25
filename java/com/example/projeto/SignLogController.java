package com.example.projeto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;



public class SignLogController {
    int check = 0;
    //--------------------JANELA SIGNLOG-----------------------
    @FXML
    private Label LSAviso;
    @FXML
    private Label LSLAviso;
    //Login
    @FXML
    private TextField TFSLEmail;
    @FXML
    private PasswordField TFSLPassword;
    //Signup
    @FXML
    private TextField TFSNome;
    @FXML
    private TextField TFSEmail;
    @FXML
    private PasswordField TFSPassword;
    public ArrayList<Utilizadores> Users = new ArrayList<>();

    static String email = "";

    //--------------------SIGNUP------------------------
    public void SignUp(ActionEvent event){

        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/main/java/com/example/projeto/Utilizadores.dat"));
            Users = (ArrayList<Utilizadores>) is.readObject();
        }catch (IOException | ClassNotFoundException exc){
            System.out.println(exc.getMessage());
        }

        String Nome = TFSNome.getText();
        String Email = TFSEmail.getText();
        String Password = TFSPassword.getText();
        int Pontuação = 0;

        for(int i = 0; i<Users.size(); i++){
            if(Email.equals(Users.get(i).getEmail())){
                check=1;
            }
        }
        if(check == 1){
            LSAviso.setStyle("-fx-text-fill: red;");
            LSAviso.setText("Conta já criada anteriormente...");
            TFSNome.setText("");
            TFSEmail.setText("");
            TFSPassword.setText("");
            check = 0;
        }else{

            Utilizadores utilizador = new Utilizadores(Nome, Email, Password, Pontuação);
            Users.add(utilizador);

            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/main/java/com/example/projeto/Utilizadores.dat"));
                os.writeObject(Users);
                LSAviso.setStyle("-fx-text-fill: black;");
                LSAviso.setText("Conta criada com sucesso...");
                SignLog(event);
            } catch (IOException e) {
                LSAviso.setStyle("-fx-text-fill: red;");
                LSAviso.setText("Erro ao criar Conta...");
            }

            TFSNome.setText("");
            TFSEmail.setText("");
            TFSPassword.setText("");
        }
    }

    //--------------------LOGIN----------------------
    public void Login(ActionEvent event) throws IOException {

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/main/java/com/example/projeto/Utilizadores.dat"));
            Users = (ArrayList<Utilizadores>) is.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
        }

        String Email = TFSLEmail.getText();
        String Password = TFSLPassword.getText();

        for (int i = 0; i < Users.size(); i++) {
            if (Users.get(i).getEmail().equals(Email) && Users.get(i).getPassword().equals(Password)) {
                email = Users.get(i).getEmail();
                Parent root = FXMLLoader.load(getClass().getResource("Dificuldade.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                check = 1;
            }
        }

        if (check == 1 || Users.size() == 0) {
            LSLAviso.setStyle("-fx-text-fill: red;");
            LSLAviso.setText("Utilizador não Encontrado ou Inexistente...");
            check = 0;
        }

        TFSLEmail.setText("");
        TFSLPassword.setText("");
    }
    //-----------------------------------------------------MUDAR DE JANELAS-----------------------------------------------------
    private Stage stage;
    private Scene scene;
    private Parent parent;
    public void SignLog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignLog.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void Signup(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void Ajuda(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Ajuda.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}