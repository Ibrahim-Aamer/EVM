package com.example.evm;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.ArrayList;
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

    private int currentCandidate=0;//holds current selected candidate(default 0,invalid)

    @FXML
    void onCastVoteButton(ActionEvent event)
    {

        if(currentCandidate > -1 && !cnic.getCharacters().toString().isEmpty())
        {
            PersistenceHandler db = new OracleDBHandler();

            ArrayList<Candidate> candidates = db.RetrieveCandidates();

            Voter voter = new Voter(cnic.getCharacters().toString());

            String result = db.castVote(voter,candidates.get(currentCandidate));//storing result of casted vote

            System.out.println("Current Candidate = "+ currentCandidate +" cnin = "+ cnic.getCharacters().toString());

            castButtonResult.setText(result);
        }
        else if(currentCandidate == -1)
        {
            castButtonResult.setText("Please Select A Candidate !");
        }
        else if(cnic.getCharacters().toString().isEmpty())
        {
            castButtonResult.setText("CNIC Field cannot be empty !");
        }

        //Updating the listview on each click of a button
        PersistenceHandler db = new OracleDBHandler();
        ListView.getItems().clear();
        ListView.getItems().addAll(EVM.getCandidateList(db.RetrieveCandidates()));



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
        ListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                currentCandidate=ListView.getSelectionModel().getSelectedIndex();//getting current selection

                System.out.println(currentCandidate);
            }
        });

    }

}