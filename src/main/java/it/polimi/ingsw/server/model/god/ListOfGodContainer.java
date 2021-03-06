package it.polimi.ingsw.server.model.god;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class is used to contain the Deck and is used to serialize and deserialize the effects of gods from Json file
 */
public class ListOfGodContainer implements Serializable {

    private final String nameOfDeck;

    private final ArrayList<God> godArrayList;

    private final String stringOfGods;


    public String getNameOfDeck() {
        return nameOfDeck;
    }

    public ArrayList<God> getGodArrayList() {
        return godArrayList;
    }

    public String getStringOfGods() {
        return stringOfGods;
    }



    public ListOfGodContainer(String nameOfDeck, ArrayList<God> godArrayList, String stringOfGods) {

        this.nameOfDeck = nameOfDeck;
        this.godArrayList = godArrayList;
        this.stringOfGods = stringOfGods;
    }


}
