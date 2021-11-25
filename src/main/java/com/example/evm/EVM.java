package com.example.evm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class EVM extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EVM.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Citizen Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        PersistenceHandler db = new OracleDBHandler();

        ArrayList<Candidate> candidates = db.RetrieveCandidates();

        Voter voter = new Voter("1234");

        db.castVote(voter,candidates.get(0));

        candidates = db.RetrieveCandidates();



        launch();
    }

    static public ArrayList<String> getCandidateList(ArrayList<Candidate> candidates)
    {
        ArrayList<String> arr = new ArrayList<String>();

        for(int c=0 ; c<candidates.size() ;c++)
        {
            arr.add(candidates.get(c).getDetails());
        }
        return arr;
    }
}