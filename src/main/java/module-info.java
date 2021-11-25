module com.example.evm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.evm to javafx.fxml;
    exports com.example.evm;
}