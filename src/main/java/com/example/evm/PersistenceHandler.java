package com.example.evm;

import java.util.ArrayList;

public abstract class PersistenceHandler
{

    abstract ArrayList<Candidate> RetrieveCandidates();
    abstract void castVote(Voter voter,Candidate candidate);
}
