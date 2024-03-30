package pz1.michalczosnyka;
import java.util.ArrayList;
import java.util.Scanner;
import pz1.michalczosnyka.MyMap;



public class Main {
    /**
     * Główna metoda klasy main czytająca nasz input i wywołująca inne klasy
     * @param args domyslny parametr klasy main
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        MyMap<String, Command> commands = new MyMap<>();
        System.out.println("------------WITAMY W HOTELU-----------------");
        System.out.println("Jeśli chcesz otworzyć dane z pliku wpisz (yes/no)");
        String agr = s.nextLine();
        Hotel h = null;
        while(!agr.equals("yes") && !agr.equals("no")){
            System.out.println("wpisz yes lub no");
            agr = s.nextLine();
        }
        if(agr.equals("no")){
            System.out.println("Stworzę autogenerowany hotel.");
            h = new Hotel();
        }
        if(agr.equals("yes")){
            System.out.println("Podaj nazwę pliku wraz z rozszerzeniem CSV (jeśli jest w innym folderze, to ścieżkę do niego)");
            agr = s.nextLine();
            h = new Hotel(agr);
        }
        commands.put("list", new List(h));
        commands.put("checkin", new Checkin(h, s));
        commands.put("checkout", new Checkout(h, s));
        commands.put("view", new View(h,s));
        commands.put("save", new Save(h));
        boolean exited = false;

        System.out.println("---------Oto lista dostępnych komend:-------");
        System.out.println("list - pokaż wszystkie pokoje");
        System.out.println("view - wyświetl informacje o konkretnym pokoju");
        System.out.println("checkin - zamelduj gościa");
        System.out.println("checkout - wymelduj gościa");
        System.out.println("save - zapisz informacje o hotelu do pliku");
        System.out.println("exit - wyjście z programu");
        while(!exited){
            System.out.println("Podaj komendę:");
            String a = s.nextLine().toLowerCase();
            if(a.equals("exit")){
                exited = true;
                Exit e = new Exit(h);
                e.Execute();
            }
            try{commands.get(a).Execute();}
            catch(Exception e){
                System.out.println("Niepoprawna komenda");
            }

            }


        }


    }
