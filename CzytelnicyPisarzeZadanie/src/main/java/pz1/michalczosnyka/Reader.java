package pz1.michalczosnyka;

/**
 * Klasa {@code Reader} odpowiedzialna jest za watek czytelnika.
 * Dziedziczy po klasie {@link Thread}.
 */

public class Reader extends Thread{
    Library library;
    int id;

    /**
     * Konstruktor tworzacy nowy obiekt klasy {@code Reader}.
     *
     * @param library_ Biblioteka, w ktorej będzie odbywal sie caly proces.
     * @param id_ Id czytelnika.
     */
    Reader(Library library_, int id_){
        library = library_;
        id = id_;
    }

    /**
     * Metoda run odpowiedzialna za dzialanie watku.
     * W nieskonczonej petli nastepuje proba wejscia pisarza do biblioteki, symulacja pisania, a nastepnie opuszczanie biblioteki.
     */
    @Override
    public void run(){
        while(true){
            try {
                library.requestRead();
                System.out.printf("Czytelnik numer %d wszedl do czytelni. \n", id);
                Thread.sleep(3000);
                library.finishRead();
                System.out.printf("Czytelnik numer %d opuscil czytelnie. \n", id);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

        }
    }
}
