package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Match;
import it.polimi.ingsw.server.observers.Observer;
import it.polimi.ingsw.server.utils.gameMessages;

import it.polimi.ingsw.server.view.playerMove;

public class Controller implements Observer<playerMove> {

    private final Match match;
    private int turnStep = 0;
    private int placeStep = 0;
    private int gameStep = 0;
    private boolean gameEnded = false;

    public Controller (Match match){
        this.match = match;
    }


    private synchronized void performTurn(playerMove message){


        //if the player who gave input is not currentplayer, returns
        if(!match.isPlayerTurn(message.getPlayer())){
            //eventuale notifica alla view
            return;
        }

        if(turnStep == 0 ){
            if (match.getCurrentPlayer().getPersonalTurn().selectWorker(match.getGameboard(), message)) {
                turnStep = 1;
            }
        }
        if (turnStep == 1) {
            if (match.getCurrentPlayer().getPersonalTurn().move(match.getGameboard(), message)) {
                turnStep = 2;
            }
        }
        if (turnStep ==2) {
            if (match.getCurrentPlayer().getPersonalTurn().build(match.getGameboard(), message)) {
                turnStep = 0;
                match.updateTurn();
            }
        }

    }

    private synchronized void performPlace(playerMove message) {
        int row = message.getRow();
        int column = message.getColumn();
        //if the player is not the current one, doesn't consider the input given
        if (!match.isPlayerTurn(message.getPlayer())) {
            return;
        }

        if (placeStep == 0) {
            if (match.getCurrentPlayer().getPersonalTurn().placeWorker(match.getGameboard(), message, "A")) {
                placeStep = 1;
            }
        }
        if (placeStep == 1){
            if (match.getCurrentPlayer().getPersonalTurn().placeWorker(match.getGameboard(), message, "B")) {
                placeStep = 0;
                match.updateTurn();
                gameStep++;
            }
        }

    }


    @Override
    public void update(playerMove message) {

        if (gameStep >= 0 && gameStep <2){
            //faccio la selezione dei god
            gameStep++;
        }

        if (gameStep>2 && gameStep <=4) {
            performPlace(message);
        }
        if (gameStep == 5) {
            //itero sui turni finché qualcuno non perde, non si incrementa più gameStep
            performTurn(message);
        }

        //se qualcuno ha vinto lo dichiaro

    }
}
