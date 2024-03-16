module org.example.javastock_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.javastock_javafx to javafx.fxml;
    exports org.example.javastock_javafx;
}