package com.example.evm;

import java.sql.*;
import java.util.ArrayList;

public class OracleDBHandler extends PersistenceHandler
{

    @Override
    ArrayList<Candidate> RetrieveCandidates()
    {
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();

        try{

            //  System.out.println(getJDBCVersion());
            //  System.out.println("SHFBSD");
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loaded Successfully!");
            //step2 create the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//DESKTOP-QB4O65F:1521/xe",
                    "system","tiger");
            System.out.println("Connection Established!");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("\n" +
                    "SELECT system.candidates.id,system.candidates.name, system.party.name as pname,system.party.symbol,system.candidates.votes\n" +
                    "FROM system.candidates\n" +
                    "INNER JOIN system.party ON system.candidates.party_id=system.party.id");


            while ( rs.next() ) {
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String party = rs.getString("pname");
                String symbol = rs.getString("symbol");
                int votes = rs.getInt("votes");

                Candidate temp = new Candidate(id,name,party,symbol,votes);
                candidates.add(temp);//adding new candidate into array list

                System.out.println(id+" "+name+" "+party+" "+symbol+" "+votes);
            }
            con.close();

        }catch(Exception e){
            System.err.println("Retrieval Unsuccessful! ");
            System.err.println(e.getMessage());
        }

        return candidates;
    }





    @Override
    void castVote(Voter voter, Candidate candidate)
    {


        //If voter is valid then candidates votes will be incremented
        if(VoteValidity(voter))
        {
            candidate.incVotes();//incrementing votes of


            try{

                //  System.out.println(getJDBCVersion());
                //  System.out.println("SHFBSD");
                //step1 load the driver class
                Class.forName("oracle.jdbc.driver.OracleDriver");
                System.out.println("Driver Loaded Successfully!");
                //step2 create the connection object
                Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@//DESKTOP-QB4O65F:1521/xe",
                        "system","tiger");
                System.out.println("Connection Established!");

                String sql = "UPDATE system.candidates  SET votes = ? WHERE id = ?";

                //candidate.incVotes();//incrementing votes of
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1,candidate.getVotes());
                statement.setInt(2,candidate.getID());

                int rowsUpdated = statement.executeUpdate();
                if(rowsUpdated > 0)
                {
                    System.out.println("Vote was added in DB");
                    AddVoterToDB(voter);
                }
                else
                {
                    System.out.println("Vote was not added in DB");
                }

                con.close();

            }catch(Exception e){
                System.err.println("Retrieval Unsuccessful! ");
                System.err.println(e.getMessage());
            }
        }
    }

    void AddVoterToDB(Voter voter)
    {
        try{

            //  System.out.println(getJDBCVersion());
            //  System.out.println("SHFBSD");
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loaded Successfully!");
            //step2 create the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//DESKTOP-QB4O65F:1521/xe",
                    "system","tiger");
            System.out.println("Connection Established!");


            String sql = "INSERT INTO system.voters (cnic) VALUES (?)";
            //System.out.println("SHFBSD");
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, voter.getCnic());

            int rowInserted = statement.executeUpdate();

            if(rowInserted> 0){
                System.out.println("Inserted into Oracle");
            }

        }catch(Exception e){
            System.out.println("Exception Thrown");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    boolean VoteValidity(Voter voter)
    {
        try{

            //  System.out.println(getJDBCVersion());
            //  System.out.println("SHFBSD");
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loaded Successfully!");
            //step2 create the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//DESKTOP-QB4O65F:1521/xe",
                    "system","tiger");
            System.out.println("Connection Established!");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cnic FROM system.voters");


            while ( rs.next() ) {
                String cnic = rs.getString("cnic");

                if(cnic.equals(voter.getCnic()))//if cnic found return false
                {
                    return false;//vote has been casted before by this voter
                }

                //System.out.println(id+" "+name+" "+party+" "+symbol+" "+votes);
            }
            con.close();

        }catch(Exception e){
            System.err.println("Retrieval Unsuccessful! ");
            System.err.println(e.getMessage());
        }

        return true;//it means voter has never casted vote

    }
}
