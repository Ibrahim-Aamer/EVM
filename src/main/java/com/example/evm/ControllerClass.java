package com.example.evm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerClass implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CastVoteButton;

    @FXML
    private ListView<String> ListView;

    @FXML
    private Text castButtonResult;

    @FXML
    private TextField cnic;

    @FXML
    void onCastVoteButton(ActionEvent event)
    {

    }

    @FXML
    void initialize() {
        assert CastVoteButton != null : "fx:id=\"CastVoteButton\" was not injected: check your FXML file 'login.fxml'.";
        assert ListView != null : "fx:id=\"ListView\" was not injected: check your FXML file 'login.fxml'.";
        assert castButtonResult != null : "fx:id=\"castButtonResult\" was not injected: check your FXML file 'login.fxml'.";
        assert cnic != null : "fx:id=\"cnic\" was not injected: check your FXML file 'login.fxml'.";

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        PersistenceHandler db = new OracleDBHandler();
        ListView.getItems().addAll(EVM.getCandidateList(db.RetrieveCandidates()));

    }

}