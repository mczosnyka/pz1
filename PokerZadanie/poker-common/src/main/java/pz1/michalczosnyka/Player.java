package pz1.michalczosnyka;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player{
    Map<Card, Integer> hand;

    Combination combo;

    int score;
    Player(){
        hand = new HashMap<>();
    }


    void addToHand(Card c){
        int counter = 1;
        for (Map.Entry<Card, Integer> entry : hand.entrySet()){
            if(c.rank.getPower() == entry.getKey().rank.getPower()){
                counter += 1;
            }
        }
        hand.put(c, counter);
    }


    String handToString(){
        String s = "";
        for (Map.Entry<Card, Integer> entry : hand.entrySet())
            s += (entry.getKey().toString() + " ");
        return s;
    }

    String comboToString(){
        return combo.toString();
    }


    void setCombo(Combination c){
        this.combo = c;
    }

    void resetCombo(){
        this.combo = null;
    }

    void setScore(int n){
        this.score = n;
    }


    void removeFromHand(Card card){
        hand.remove(card);
    }

    void discard(ArrayList<Integer> n){
        ArrayList<Card> list = new ArrayList<Card>(hand.keySet());
        for(int i:n){
            removeFromHand(list.get(i-1));
        }
    }
}
