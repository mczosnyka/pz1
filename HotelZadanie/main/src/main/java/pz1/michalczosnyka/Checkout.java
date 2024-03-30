package pz1.michalczosnyka;

import java.util.Objects;
import java.util.Scanner;

public class Checkout extends Command{
    int roomNumber;
    Scanner s;
    /**
     * Konstruktor klasy Checkout przyjmujący hotel i scanner
     * @param h - hotel
     * @param scan - scanner
     */
    public Checkout(Hotel h, Scanner scan){
        commandName = "Checkout";
        hotel = h;
        s = scan;
    }
    /**
     * Metoda Execute wykonująca komendę checkout
     */
    @Override
    public void Execute(){
        setRoomNumber();
        if(hotel.hotel.get(roomNumber)==null){
            System.out.println("Nie mamy takiego pokoju!");
        }
        else if(hotel.hotel.get(roomNumber).guest == null){
            System.out.println("Ten pokój jest wolny");
        }
        else if(hotel.hotel.get(roomNumber).guest != null){
            hotel.hotel.get(roomNumber).modifyGuest(null);
            System.out.println("Gość wymeldowany!");
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

}
