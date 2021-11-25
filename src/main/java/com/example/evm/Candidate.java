package com.example.evm;

public class Candidate
{
    private int ID;
    private String Name;
    private String partyName;
    private String symbol;
    private int votes;

    public Candidate(int ID, String name, String partyName, String symbol,int votes)
    {
        this.ID = ID;
        Name = name;
        this.partyName = partyName;
        this.symbol = symbol;
        this.votes = votes;
    }

    public String getDetails()
    {
        if(this.ID == 1)
        {
            return (this.ID+"                          "+this.Name +"              "+this.partyName+"                     "+ this.symbol+"                        "+this.votes);
        }else if(this.ID == 2)
        {
            return (this.ID+"                       "+this.Name +"         "+this.partyName+"                 "+ this.symbol+"                      "+this.votes);
        }
        else if(this.ID == 3)
        {
            return (this.ID+"                        "+this.Name +"            "+this.partyName+"                  "+ this.symbol+"                     "+this.votes);
        }
        else if(this.ID == 4)
        {
            return (this.ID+"                      "+this.Name +"        "+this.partyName+"                  "+ this.symbol+"                       "+this.votes);
        }
        else
        {
            return (this.ID+"                          "+this.Name +"            "+this.partyName+"                  "+ this.symbol+"                      "+this.votes);
        }
    }

    public int getVotes() {
        return votes;
    }

    public void incVotes()
    {
        this.votes++;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
