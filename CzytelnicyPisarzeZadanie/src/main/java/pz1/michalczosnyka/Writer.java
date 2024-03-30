package pz1.michalczosnyka;

/**
 * Klasa {@code Writer} odpowiedzialna jest za watek pisarza.
 * Dziedziczy po klasie {@link Thread}.
 */


public class Writer extends Thread{
    Library library;
    int id;

    /**
     * Konstruktor tworzacy nowy obiekt klasy {@code Writer}.
     *
     * @param library_ Biblioteka, w ktorej będzie odbywal sie caly proces.
     * @param id_ Id pisarza.
     */
    Writer(Library library_, int id_){
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
                library.requestWrite();
                System.out.printf("Pisarz numer %d wszedl do czytelni. \n", id);
                Thread.sleep(5000);
                library.finishWrite();
                System.out.printf("Pisarz numer %d opuscil czytelnie. \n", id);
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

        }
    }
}
