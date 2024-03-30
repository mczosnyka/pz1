package pz1.michalczosnyka;

public class Card{
    Suit suit;
    Rank rank;

    int power;

    Card(Suit s, Rank r){
        this.suit = s;
        this.rank = r;
        this.power = r.getPower();
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Card card = (Card) obj;

        if(!suit.equals(card.suit)) return false;
        return rank.equals(card.rank);
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

}