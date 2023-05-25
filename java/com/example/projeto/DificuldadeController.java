package com.example.projeto;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;

public class DificuldadeController {
    @FXML
    private ComboBox<String> CBIdioma;
    @FXML
    private Slider SDificuldade;
    @FXML
    private Label LDificuldade;
    static int Dificuldade = 3;
    static String Idioma = "Português";

    public void initialize(){

        SDificuldade.setValue(3);
        CBIdioma.getItems().addAll("Português", "Inglês", "Francês");

        SDificuldade.valueProperty().addListener((ObservableValue<?extends Number> num, Number ValorAntigo, Number ValorNovo)->{
            LDificuldade.setText(Integer.toString((int)SDificuldade.getValue()));
            Dificuldade = (int) SDificuldade.getValue();
        });

        CBIdioma.getSelectionModel().selectedItemProperty().addListener((ObservableValue<?extends String> observable, String ValorAntigo, String ValorNovo) -> {
            Idioma = ValorNovo;
        });
    }

    //-----------------------------------------------------MUDAR DE JANELAS-----------------------------------------------------
    private Stage stage;
    private Scene scene;
    public void LJogo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Jogo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
