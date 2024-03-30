package pz1.michalczosnyka;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class MainTest {

    @Test
    void testHotelDefaultConstructor() {
        Hotel hotel = new Hotel();
        assertTrue(hotel.hotel.contains(101), "Brak pokoju o numerze 101");
        for (Integer key: hotel.hotel.keys()) {
            if(key>=100 &&key<500){
                assertEquals(100, hotel.hotel.get(key).price);
                assertEquals("pokoj podstawowy", hotel.hotel.get(key).desc);
            }
            if(key>=500 && key<1000){
                assertEquals(300, hotel.hotel.get(key).price);
                assertEquals("pokoj komfort", hotel.hotel.get(key).desc);
            }
            if(key>=1000){
                assertEquals(500, hotel.hotel.get(key).price);
                assertEquals("pokoj extra komfort", hotel.hotel.get(key).desc);
            }
        }
    }

    @Test
    void testHotelFileConstructor() {
        String filePath = "C:\\Projekty\\JavaProjects\\HotelZadanie\\differentHotelInput.csv";
        Hotel hotel = new Hotel(filePath);
        assertTrue(hotel.hotel.contains(101));
        assertEquals(100, hotel.hotel.get(1000).price);
    }

    @Test
    void testHotelFileConstructorNoFileError(){
            String nonExistentFilePath = "farfetched.csv";
            assertThrows(RuntimeException.class, () -> {
                new Hotel(nonExistentFilePath);
            });
        }

    @Test
    void testRoomIntConstructor() {
        Room r1 = new Room(17);
        assertEquals(17, r1.number);
        assertNull(r1.desc, "Domyślnie pokoje w hotelu mają numery od 100 do 1009");
        assertEquals(0, r1.price,  "Domyślnie pokoje w hotelu mają numery od 100 do 1009");
        Room r2 = new Room(803);
        assertEquals(803, r2.number);
        assertEquals("pokoj komfort", r2.desc);
        assertEquals(300, r2.price);
    }

    @Test
    void testModifyPrice(){
        Room r1 = new Room(45);
        r1.modifyPrice(999999);
        assertEquals(999999, r1.price);
    }

    @Test
    void testRoomModifyDesc(){
        Room r1 = new Room(402);
        String newer = "przewspaniały pokój";
        r1.modifyDesc("przewspaniały pokój");
        assertEquals(r1.desc, "przewspaniały pokój");
    }

    @Test
    void testRoomModifyGuest(){
        Room r1 = new Room(607);
        String newer = "Maciej Pralka";
        String newer2 = "Jurek Ogórek";
        r1.modifyGuest(newer);
        assertEquals("Maciej Pralka", r1.guest);
        r1.modifyGuest(newer2);
        assertEquals("Jurek Ogórek",r1.guest);
    }

    @Test
    void testRoomGetInfo(){
        Room r1 = new Room(107);
        assertEquals("opis: pokoj podstawowy, cena: 100zł", r1.getInfo());
        String newer = "Radosław Mikrofalnik";
        r1.modifyGuest(newer);
        assertEquals("opis: pokoj podstawowy, cena: 100zł, gość: Radosław Mikrofalnik", r1.getInfo());
    }

    @Test
    void testCheckInConstructor(){
        Scanner scanner = new Scanner(System.in);
        Hotel h = new Hotel();
        Checkin nc = new Checkin(h, scanner);
        assertEquals("Checkin", nc.commandName);
        assertEquals(nc.hotel, h);
        assertEquals(nc.s, scanner);
    }


    @Test
    void testCheckinSetRoomNumber() {
        String input = "123\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkin nc = new Checkin(h, scanner);
            nc.setRoomNumber();
            assertEquals(123, nc.roomNumber);
        }
    }


    @Test
    void testCheckinSetGuest() {
        String input = "Joanna Wanna\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkin nc = new Checkin(h, scanner);
            nc.setGuest();
            assertEquals("Joanna Wanna", nc.guest);
        }
    }

    @Test
    void testCheckinExecuteProperInput() {
        String input = "304\nMarysia Odrysia\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkin nc = new Checkin(h, scanner);
            nc.Execute();
            assertEquals("Marysia Odrysia", nc.guest);
            assertEquals(304, nc.roomNumber);
        }



    }

    @Test
    void testCheckinExecuteAlreadyOccupied() {
        String input = "304\nMarysia Odrysia\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkin nc = new Checkin(h, scanner);
            nc.Execute();
            assertEquals("Marysia Odrysia", nc.guest);
            assertEquals(304, nc.roomNumber);
        }
        String input2 = "304\nAnia Bania\n";
        InputStream inputStream2 = new ByteArrayInputStream(input2.getBytes());
        try (Scanner scanner = new Scanner(inputStream2)) {
            Checkin nc = new Checkin(h, scanner);
            nc.Execute();
            assertNull(nc.guest);
            assertEquals(304, nc.roomNumber);
        }




    }
    @Test
    void testCheckinExecuteInvalidNumber() {
        String input = "42\nSzymon Mimon\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkin nc = new Checkin(h, scanner);
            nc.Execute();
            assertNull(nc.guest);
        }
    }

    @Test
    void testExitConstructor(){
        Hotel h = new Hotel();
        Exit ne = new Exit(h);
        assertEquals("exit", ne.commandName);
        assertEquals(h, ne.hotel);
    }

    @Test
    void testCheckoutConstructor(){
        Scanner scanner = new Scanner(System.in);
        Hotel h = new Hotel();
        Checkout nc = new Checkout(h, scanner);
        assertEquals("Checkout", nc.commandName);
        assertEquals(nc.hotel, h);
        assertEquals(nc.s, scanner);
    }

    @Test
    void testCheckoutSetRoomNumber() {
        String input = "123\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkout nc = new Checkout(h, scanner);
            nc.setRoomNumber();
            assertEquals(123, nc.roomNumber);
        }
    }

    @Test
    void testCheckoutExecuteProperInput() {
        String input = "304\nMarysia Odrysia\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkin nc = new Checkin(h, scanner);
            nc.Execute();
            assertEquals("Marysia Odrysia", nc.guest);
            assertEquals(304, nc.roomNumber);
        }
        String input2 = "304\n";
        InputStream inputStream2 = new ByteArrayInputStream(input2.getBytes());
        try (Scanner scanner = new Scanner(inputStream2)) {
            Checkout nc = new Checkout(h, scanner);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            nc.Execute();
            assertNull(h.hotel.get(304).guest);
            String expected = "Gość wymeldowany!";
            String capturedOutput = outputStreamCaptor.toString();
            String[] lines = capturedOutput.split(System.lineSeparator());
            String lastLine = lines[lines.length - 1];
            assertEquals(expected, lastLine);
            System.setOut(System.out);
        }
    }

    @Test
    void testCheckoutExecuteInvalidNumber() {
        String input = "42\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkout nc = new Checkout(h, scanner);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            nc.Execute();
            String expected = "Nie mamy takiego pokoju!";
            String capturedOutput = outputStreamCaptor.toString();
            String[] lines = capturedOutput.split(System.lineSeparator());
            String lastLine = lines[lines.length - 1];
            assertEquals(expected, lastLine);
            System.setOut(System.out);
        }
    }

    @Test
    void testCheckoutExecuteNoGuest() {
        String input = "102\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkout nc = new Checkout(h, scanner);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            nc.Execute();
            String expected = "Ten pokój jest wolny";
            String capturedOutput = outputStreamCaptor.toString();
            String[] lines = capturedOutput.split(System.lineSeparator());
            String lastLine = lines[lines.length - 1];
            assertEquals(expected, lastLine);
            System.setOut(System.out);
        }
    }

    @Test
    void testSaveConstructor(){
        Hotel h = new Hotel();
        Save ns = new Save(h);
        assertEquals("save", ns.commandName);
        assertEquals(ns.hotel, h);
    }
    @Test
    void testViewConstructor(){
        Scanner scanner = new Scanner(System.in);
        Hotel h = new Hotel();
        View nv = new View(h, scanner);
        assertEquals("view", nv.commandName);
        assertEquals(nv.hotel, h);
    }
    @Test
    void testListConstructor(){
        Hotel h = new Hotel();
        List nl = new List(h);
        assertEquals("list", nl.commandName);
        assertEquals(nl.hotel, h);
    }

    @Test
    void testListPrintDefaultConstructor(){
        Hotel h = new Hotel();
        List nl = new List(h);
        assertEquals("list", nl.commandName);
        assertEquals(nl.hotel, h);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        nl.Execute();
        String expected = "Pokój" + " " + 700 + ", " + h.hotel.get(700).getInfo();
        String capturedOutput = outputStreamCaptor.toString();
        String[] lines = capturedOutput.split(System.lineSeparator());
        String lastLine = lines[lines.length - 40];
        assertEquals(expected, lastLine);
        System.setOut(System.out);
    }



    @Test
    void testViewPrint(){
        String input = "700\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            View nv = new View(h, scanner);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            nv.Execute();
            String expected = "Pokój" + " " + 700 + ", " + h.hotel.get(700).getInfo();
            String capturedOutput = outputStreamCaptor.toString();
            String[] lines = capturedOutput.split(System.lineSeparator());
            String lastLine = lines[lines.length - 1];
            assertEquals(expected, lastLine);
            System.setOut(System.out);
        }
    }
    @Test
    void testViewInvalidOutput(){
        String input = "83\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            View nv = new View(h, scanner);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            nv.Execute();
            String expected = "Nie mamy takiego pokoju!";
            String capturedOutput = outputStreamCaptor.toString();
            String[] lines = capturedOutput.split(System.lineSeparator());
            String lastLine = lines[lines.length - 1];
            assertEquals(expected, lastLine);
            System.setOut(System.out);
        }
    }

    @Test
    public void testSaveAndLoadRooms() {
        String input = "406\nRobert Bobert\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Hotel h = new Hotel();
        try (Scanner scanner = new Scanner(inputStream)) {
            Checkin nc = new Checkin(h, scanner);
            nc.Execute();
            assertEquals("Robert Bobert", nc.guest);
            assertEquals(406, nc.roomNumber);
        }
        Save ns = new Save(h);
        ns.Execute();
        assertEquals("Robert Bobert", h.hotel.get(406).guest);
    }

}

