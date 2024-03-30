package pz1.michalczosnyka;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Klasa {@code Main} rozpoczyna dzialanie programu.
 */

public class Main {
    /**
     * Glowna metoda klasy main, ktora czyta nasz input, tworzy biblioteke, a takze watki pisarzy i czytelnikow oraz wywoluje je.
     * @param args domyslny parametr klasy main
     */
    public static void main(String[] args) {
        Library library = new Library();
        Scanner s = new Scanner(System.in);
        int readers_number = 10;
        int writers_number = 3;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Podaj liczbe czytelnikow (0 jeśli domyslna): ");
                int a = s.nextInt();
                if (a != 0) {
                    readers_number = a;
                }
                validInput = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Blad! Podana wartosc musi byc liczba.");
                s.next();
            }
            }
        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Podaj liczbę pisarzy (0 jeśli domyslna): ");
                int a = s.nextInt();
                if (a != 0) {
                    writers_number = a;
                }
                validInput = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Blad! Podana wartosc musi byc liczba.");
                s.next();
            }
        }
        for(int i = 0; i<writers_number; i++){
            Writer writer = new Writer(library, i+1);
            writer.start();
        }
        for(int i = 0; i<readers_number; i++){
            Reader reader = new Reader(library, i+1);
            reader.start();
        }
    }

}
