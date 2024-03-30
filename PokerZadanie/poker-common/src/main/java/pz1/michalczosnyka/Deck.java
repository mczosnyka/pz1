package pz1.michalczosnyka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck{
    ArrayList<Card> cards;
    static final int NUM_CARDS = (Rank.values().length * Suit.values().length);
    static final int HAND_SIZE = 5;


    public Deck(){
        int index = 0;
        cards = new ArrayList<Card>(NUM_CARDS);
        for(Suit x : Suit.values()){
            for(Rank z : Rank.values()){
                cards.add(new Card(x,z));
                index++;
            }
        }
    }




    void shuffle(){
        Collections.shuffle(cards, new Random());
    }

    Card getCard(int ind){
        if (ind >= 0 && ind < NUM_CARDS) {
            return cards.get(ind);
        } else {
            return null;
        }
    }

    void removetopCard(){
        cards.remove(0);
    }

}
