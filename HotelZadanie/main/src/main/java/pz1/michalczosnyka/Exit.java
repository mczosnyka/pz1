package pz1.michalczosnyka;

public class Exit extends Command{
    /**
     * Konstruktor klasy exit przyjmujący hotel
     * @param h - hotel
     */
    public Exit(Hotel h){
        commandName = "exit";
        hotel = h;
    }
    /**
     * Metoda Execute wykonująca komendę exit
     */
    @Override
    public void Execute(){
        System.out.println("Koniec programu");
        System.exit(0);
    }

}
