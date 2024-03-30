package pz1.michalczosnyka;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Save extends Command{

    /**
     * Konstruktor klasy Save przyjmujący hotel
     * @param h - hotel
     */
    public Save(Hotel h){
        commandName = "save";
        hotel = h;
    }
    /**
     * Metoda Execute wykonująca komendę Save
     */
    @Override
    public void Execute(){
        try (Writer outputFile = new FileWriter("hotelOutput.csv")) {
            CSVPrinter printer = new CSVPrinter(outputFile, CSVFormat.DEFAULT);
            for (Integer key: hotel.hotel.keys()){
                printer.printRecord(key, hotel.hotel.get(key).desc, hotel.hotel.get(key).price, hotel.hotel.get(key).guest);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
