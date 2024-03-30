package pz1.michalczosnyka;

public class Room {
    int number;

    int price;
    String guest = null;

    String desc;

    /**
     * Konstruktor klasy Room przyjmujący numer pokoju
     * @param n - numer pokoju
     */
    public Room(int n){
        number = n;
        if(n>=100 && n<500){
            desc = "pokoj podstawowy";
            price = 100;
        }
        if(n>=500 && n<1000){
            desc = "pokoj komfort";
            price = 300;
        }
        if(n>=1000){
            desc = "pokoj extra komfort";
            price = 500;
        }
    }


    /**
     * Metoda pozwalająca zmodyfikować cenę pokoju
     * @param n - nowa cena
     */
    public void modifyPrice(int n){
        price = n;
    }
    /**
     * Metoda pozwalająca zmodyfikować opis pokoju
     * @param d - nowy opis
     */
    public void modifyDesc(String d){
        this.desc = d;
    }

    /**
     * Metoda zwracająca informacje o pokoju
     */
    public String getInfo(){
        if(guest != null){
            return "opis: " + desc + ", " + "cena:"+" "+price+"zł,"+" "+"gość:"+" "+guest;
        }
        else{
            return "opis: " + desc + ", " + "cena:"+" "+price+"zł";
        }
    }

    /**
     * Metoda pozwalająca zmodyfikować gościa pokoju
     * @param g - nowy gość
     */
    public void modifyGuest(String g){
        this.guest = g;
    }

}
