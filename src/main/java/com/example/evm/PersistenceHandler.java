package com.example.evm;

import java.util.ArrayList;

public abstract class PersistenceHandler
{

    abstract ArrayList<Candidate> RetrieveCandidates();
    abstract String castVote(Voter voter,Candidate candidate);
}
