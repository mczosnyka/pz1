package pz1.michalczosnyka;

import java.util.Objects;
import java.util.Scanner;

public class Checkin extends Command{
    int roomNumber;
    Scanner s;
    String guest;
    /**
     * Konstruktor klasy Checkin przyjmujący hotel i scanner
     * @param h - hotel
     * @param scan - scanner
     */
    public Checkin(Hotel h, Scanner scan){
        commandName = "Checkin";
        hotel = h;
        s = scan;
    }
    /**
     * Metoda Execute wykonująca komendę checkin
     */
    @Override
    public void Execute(){
        setRoomNumber();
        if(hotel.hotel.get(roomNumber)==null){
            System.out.println("Nie mamy takiego pokoju!");
        }
        else if(hotel.hotel.get(roomNumber).guest != null){
            System.out.println("Ten pokój jest już zajęty");
        }
        else if(hotel.hotel.get(roomNumber).guest == null){
            setGuest();
            hotel.hotel.get(roomNumber).modifyGuest(guest);
            System.out.println("Gość zameldowany!");
        }

    }
    /**
     * Metoda setRoomNumber która pozwala ustawić roomNumber na podany numer pokoju
     */
    public void setRoomNumber(){
        System.out.println("Podaj numer pokoju");
        roomNumber = s.nextInt();
        s.nextLine();
    }
    /**
     * Metoda setRoomNumber która pozwala ustawić guest na podaną nazwę gościa
     */
    public void setGuest(){
        System.out.println("Podaj nazwisko gościa");
        guest = s.nextLine();
    }
}
