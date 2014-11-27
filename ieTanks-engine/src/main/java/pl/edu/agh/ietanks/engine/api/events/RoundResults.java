package pl.edu.agh.ietanks.engine.api.events;

import java.util.List;

public class RoundResults {

    private boolean gameFinished;
    private List<Event> eventsList;

    public boolean isGameFinished(){
        return gameFinished;
    }
    
    public List<Event> getRoundEvents(){
        return eventsList; 
    }

    public RoundResults(List<Event> roundEvents){
        this.eventsList = roundEvents;
        this.gameFinished = false;
    }

    public RoundResults(){
        this.gameFinished = true;
    }
}
