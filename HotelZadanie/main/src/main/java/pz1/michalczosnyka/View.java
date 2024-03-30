package pz1.michalczosnyka;
import java.util.Scanner;

public class View extends Command{
    int roomNumber;
    Scanner s;
    /**
     * Konstruktor klasy View przyjmujący hotel i scanner
     * @param h - hotel
     * @param scan - scanner
     */
    public View(Hotel h, Scanner scan){
        commandName = "view";
        hotel = h;
        s = scan;
    }
    /**
     * Metoda Execute wykonująca komendę View
     */
    @Override
    public void Execute(){
        setRoomNumber();
        if(hotel.hotel.get(roomNumber)!=null){
            System.out.println("Pokój" + " " + roomNumber + ", " + hotel.hotel.get(roomNumber).getInfo());
        }
        else{
            System.out.println("Nie mamy takiego pokoju!");
        }

    }

    void setRoomNumber(){
        System.out.println("Podaj numer pokoju");
        roomNumber = s.nextInt();
        s.nextLine();
    }
}
