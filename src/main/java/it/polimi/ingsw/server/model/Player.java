package it.polimi.ingsw.server.model;

import java.io.Serializable;
import java.util.zip.DataFormatException;


public class Player implements Serializable {

    private final String name;
    private final Date birthDate;

    public Player(String name, Date birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }

    public Player(String name, int dayOfBirth, int monthOfBirth, int yearOfBirth) throws DataFormatException{
        this.name = name;
        this.birthDate = new Date(dayOfBirth, monthOfBirth, yearOfBirth);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Player)) return false;
        //faccio casting per evitare problemi
        if( ((Player) obj).name.equals(this.name) ) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Player named " +getName()+ ", born on " +getBirthDate();
    }
}



