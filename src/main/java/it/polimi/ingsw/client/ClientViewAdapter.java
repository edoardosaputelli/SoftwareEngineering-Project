package it.polimi.ingsw.client;

import it.polimi.ingsw.server.model.BoardPhotography;
import it.polimi.ingsw.server.model.Date;
import it.polimi.ingsw.bothsides.onlinemessages.setupmessages.MenuMessage;
import it.polimi.ingsw.bothsides.onlinemessages.playermove.PlayerMove;

public class ClientViewAdapter {

    private ClientViewAdapter(){
        //hiding the default constructor
    }


    //può essere una classe cli o gui
    private static MenuUserInterface menuUserInterface;
    private static InGameUserInterface inGameUserInterface;






    public static void setTypeInterface(MenuUserInterface menuUi, InGameUserInterface inGameUi){

        menuUserInterface = menuUi;
        inGameUserInterface = inGameUi;
    }

    public static void fromMenuToInGameGui(){

        if(menuUserInterface instanceof MenuGui){

            ((MenuGui) menuUserInterface).setMenuGuiVisible(false);

        }


        if(inGameUserInterface instanceof InGameGui){

            ((InGameGui) inGameUserInterface).setInGameGuiVisible(true);

        }

    }




    public static MenuMessage askForInfoToCreateLobby(String creator){
        return menuUserInterface.askForInfoToCreateLobby(creator);
    }

    public static MenuMessage askForInfoToParticipateLobby(boolean isPublic, String namePlayer) {
        return menuUserInterface.askForInfoToParticipateLobby(isPublic, namePlayer);
    }

    public static String askForName() { return menuUserInterface.askForName(); }

    public static Date askForDate() { return menuUserInterface.askForDate(); }

    public static void printMenuMessage(String message) { menuUserInterface.printMenuMessage(message); }


    public static boolean askBooleanQuestion(String message) { return menuUserInterface.askBooleanQuestion(message); }






    //IN GAME METHODS

    public static PlayerMove askForCoordinates(String message){return inGameUserInterface.askForCoordinates(message);}

    public static PlayerMove askForInGameConfirmation(String message){return inGameUserInterface.askForInGameConfirmation(message);}

    public static PlayerMove askForGodName(String message){return inGameUserInterface.askForGodName(message);}

    public static boolean updateBoard(BoardPhotography boardPhotography){

        if(inGameUserInterface instanceof InGameCli) {
            return ((InGameCli) inGameUserInterface).clientBoardPhotography.updateClientBoardPhotography(boardPhotography);}

        else {

            return ((InGameGui) inGameUserInterface).clientBoardPhotography.updateClientBoardPhotography(boardPhotography);
        }

    }

    public static void showBoard(BoardPhotography boardPhotography){inGameUserInterface.showBoard(boardPhotography);}

    public static void printInGameMessage(String message){ inGameUserInterface.printInGameMessage(message); }

    public static void printSecondaryInGameMessage (String message) {inGameUserInterface.printSecondaryInGameMessage(message);}











}