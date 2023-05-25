module com.example.projeto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projeto to javafx.fxml;
    exports com.example.projeto;
}