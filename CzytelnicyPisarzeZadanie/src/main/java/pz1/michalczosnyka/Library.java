package pz1.michalczosnyka;

import java.util.concurrent.Semaphore;

/**
 * Klasa {@code Library} odpowiedzialna jest za implementacje instancji biblioteki.
 * Zawiera metody, dzieki ktorym mozemy obsluzyc caly program.
 */
public class Library {
    final Semaphore readersSemaphore;
    final Semaphore writersSemaphore;
    boolean writerInside;
    int readersInside;
    /**
     * Konstruktor tworzacy nowy obiekt klasy {@code Library}.
     * Jest to konstruktor bezargumentowy, przyjmuje on liczbe pisarzy mogacych naraz przebywac w bibliotece jako 1, a czytelnikow jako 5.
     */
    Library(){
        readersSemaphore = new Semaphore(5);
        writersSemaphore = new Semaphore(1);
        writerInside = false;
        readersInside = 0;
    }

    /**
     * Metoda odpowiedzialna za zazadanie pozwolenia na wejscie do biblioteki jako czytelnik.
     * @throws InterruptedException Jezeli watek czytelnika jest przerwany podczas oczekiwania na miejsce w bibliotece.
     */
    public void requestRead() throws InterruptedException {
        synchronized(this){
            while(writerInside || readersInside >=5){
                wait(300);
            }
            readersSemaphore.acquire();
            readersInside ++;
        }
    }

    /**
     * Metoda odpowiedzialna za zazadanie pozwolenia na wejscie do biblioteki jako pisarz.
     * @throws InterruptedException Jezeli watek pisarza jest przerwany podczas oczekiwania na miejsce w bibliotece.
     */
    public void requestWrite() throws InterruptedException {
        synchronized(this) {
            while(writerInside || readersInside >=1){
                wait(300);
            }
            writersSemaphore.acquire();
            writerInside = true;
        }
    }
    /**
     * Metoda informujaca o zakonczeniu procesu czytania przez czytelnika i zwalniajaca miejsce w bibliotece.
     */
    public void finishRead(){
        synchronized(this){
            readersInside --;
        }
        readersSemaphore.release();
    }
    /**
     * Metoda informujaca o zakonczeniu procesu pisania przez pisarza i zwalniajaca miejsce w bibliotece.
     */
    public void finishWrite(){
        synchronized(this){
            writerInside = false;
        }
        writersSemaphore.release();
    }
}
