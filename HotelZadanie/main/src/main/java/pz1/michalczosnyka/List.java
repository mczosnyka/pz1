package pz1.michalczosnyka;

public class List extends Command{
    /**
     * Konstruktor klasy List przyjmujący hotel
     * @param h - hotel
     */
    public List(Hotel h){
        commandName = "list";
        hotel = h;
    }
    /**
     * Metoda Execute wykonująca komendę list
     */
    @Override
    public void Execute(){
        for (Integer key: hotel.hotel.keys()) {
            System.out.println("Pokój" + " " + key + ", " + hotel.hotel.get(key).getInfo());
        }
    }
}
