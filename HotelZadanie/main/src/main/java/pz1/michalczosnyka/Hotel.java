package pz1.michalczosnyka;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

public class Hotel {
    MyMap<Integer, Room> hotel = new MyMap<>();
    /**
     * Domyślny konstruktor klasy Hotel
     */
    public Hotel(){
        for(int i = 100; i<=1000; i+=100){
            for(int j = 0; j<10; j++){
                Room r = new Room(i+j);
                hotel.put(i+j, r);
            }
        }
    }

    /**
     * Konstruktor klasy Save przyjmujący ścieżkę do pliku csv
     * @param a - ścieżka do pliku CSV z którego chcemy otworzyć hotel
     */
    public Hotel(String a){
        try(Reader inputFile = new FileReader(a)){
            CSVParser parser = new CSVParser(inputFile, CSVFormat.DEFAULT);
            for (CSVRecord record : parser){
                int key = Integer.parseInt(record.get(0));
                Room r = new Room(key);
                r.desc = record.get(1);
                r.price = Integer.parseInt(record.get(2));
                if(Objects.equals(record.get(3), "")){
                    r.guest = null;
                }
                else{
                    r.guest = record.get(3);
                }
                hotel.put(key,r);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
