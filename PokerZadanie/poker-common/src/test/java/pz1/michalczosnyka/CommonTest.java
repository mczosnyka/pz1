package pz1.michalczosnyka;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class CommonTest {

    @Test
    void testPlayerDefaultConstructor() {
        Player player = new Player();
        assertEquals(0, player.hand.size());
    }

    @Test
    void testPlayerAddToHand(){
        Player player = new Player();
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(0, player.hand.size());
        player.addToHand(card);
        assertEquals(1, player.hand.size());
        Card card2 = new Card(Suit.SPADES, Rank.JACK);
        player.addToHand(card2);
        assertEquals(2, player.hand.size());
    }

    @Test
    void testPlayerHandToString(){
        Player player = new Player();
        Card card = new Card(Suit.DIAMONDS, Rank.TEN);
        player.addToHand(card);
        String s = player.handToString();
        assertEquals("TEN of DIAMONDS ", s);
    }

    @Test
    void testPlayerSetCombo(){
        Player player = new Player();
        Combination c = Combination.POKER;
        player.setCombo(c);
        assertEquals(Combination.POKER, player.combo);
    }

    @Test
    void testPlayerResetCombo(){
        Player player = new Player();
        Combination c = Combination.STRAIGHT;
        player.setCombo(c);
        assertEquals(Combination.STRAIGHT, player.combo);
        player.resetCombo();
        assertNull(player.combo);
    }

    @Test
    void testPlayerComboToString(){
        Player player = new Player();
        Combination c = Combination.STRAIGHT;
        player.setCombo(c);
        assertEquals("STRAIGHT", player.comboToString());
    }

    @Test
    void testPlayerSetScore(){
        Player player = new Player();
        player.setScore(10);
        assertEquals(10, player.score);
    }

    @Test
    void testPlayerRemoveFromHand(){
        Player player = new Player();
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(0, player.hand.size());
        player.addToHand(card);
        assertEquals(1, player.hand.size());
        Card card2 = new Card(Suit.DIAMONDS, Rank.JACK);
        player.addToHand(card2);
        assertEquals(2, player.hand.size());
        player.removeFromHand(card2);
        assertEquals(1, player.hand.size());
    }

    @Test
    void testPlayerDiscard(){
        Player player = new Player();
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(0, player.hand.size());
        player.addToHand(card);
        assertEquals(1, player.hand.size());
        Card card2 = new Card(Suit.DIAMONDS, Rank.JACK);
        player.addToHand(card2);
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        player.discard(ints);
        assertEquals(1, player.hand.size());
    }

    @Test
    void testRankGetPower(){
        assertEquals(14, Rank.ACE.getPower());
    }

    @Test
    void testDeckDefaultConstructor(){
        Deck d = new Deck();
        assertEquals(52, d.cards.size());
    }

    @Test
    void testDeckShuffle(){
        Deck d = new Deck();
        Deck d2 = new Deck();
        d.shuffle();
        assertNotEquals(d, d2);
    }

    @Test
    void testDeckRemoveTopCard(){
        Deck d = new Deck();
        assertEquals(52, d.cards.size());
        d.removetopCard();
        assertEquals(51, d.cards.size());
    }

    @Test
    void testDeckGetCard(){
        Deck d = new Deck();
        Card c = new Card(Suit.DIAMONDS, Rank.TWO);
        assertEquals(c, d.getCard(0));
        assertNull(d.getCard(-1));
    }

    @Test
    void testCheckerConstructor(){
        Player player = new Player();
        Checker c = new Checker(player);
        assertEquals(player, c.player);
    }

    @Test
    void testCheckerCheckEngine(){
        Player player1 = new Player();
        Checker checker1 = new Checker(player1);
        player1.addToHand(new Card(Suit.DIAMONDS, Rank.TWO));
        player1.addToHand(new Card(Suit.DIAMONDS, Rank.THREE));
        player1.addToHand(new Card(Suit.DIAMONDS, Rank.FOUR));
        player1.addToHand(new Card(Suit.DIAMONDS, Rank.FIVE));
        player1.addToHand(new Card(Suit.DIAMONDS, Rank.SIX));
        checker1.checkEngine();
        assertEquals(Combination.POKER, player1.combo);
        assertEquals(6, player1.score);
        Player player2 = new Player();
        Checker checker2 = new Checker(player2);
        player2.addToHand(new Card(Suit.DIAMONDS, Rank.SEVEN));
        player2.addToHand(new Card(Suit.DIAMONDS, Rank.THREE));
        player2.addToHand(new Card(Suit.SPADES, Rank.FOUR));
        player2.addToHand(new Card(Suit.HEARTS, Rank.FIVE));
        player2.addToHand(new Card(Suit.DIAMONDS, Rank.SIX));
        checker2.checkEngine();
        assertEquals(Combination.STRAIGHT, player2.combo);
        assertEquals(7, player2.score);
        Player player3 = new Player();
        Checker checker3 = new Checker(player3);
        player3.addToHand(new Card(Suit.DIAMONDS, Rank.SEVEN));
        player3.addToHand(new Card(Suit.DIAMONDS, Rank.THREE));
        player3.addToHand(new Card(Suit.SPADES, Rank.SEVEN));
        player3.addToHand(new Card(Suit.HEARTS, Rank.SEVEN));
        player3.addToHand(new Card(Suit.CLUBS, Rank.SEVEN));
        checker3.checkEngine();
        assertEquals(Combination.QUAD, player3.combo);
        assertEquals(7, player3.score);
        Player player4 = new Player();
        Checker checker4 = new Checker(player4);
        player4.addToHand(new Card(Suit.DIAMONDS, Rank.SEVEN));
        player4.addToHand(new Card(Suit.DIAMONDS, Rank.THREE));
        player4.addToHand(new Card(Suit.SPADES, Rank.SEVEN));
        player4.addToHand(new Card(Suit.HEARTS, Rank.JACK));
        player4.addToHand(new Card(Suit.CLUBS, Rank.SEVEN));
        checker4.checkEngine();
        assertEquals(Combination.THREE, player4.combo);
        assertEquals(7, player4.score);
        Player player5 = new Player();
        Checker checker5 = new Checker(player5);
        player5.addToHand(new Card(Suit.DIAMONDS, Rank.SEVEN));
        player5.addToHand(new Card(Suit.DIAMONDS, Rank.THREE));
        player5.addToHand(new Card(Suit.SPADES, Rank.SEVEN));
        player5.addToHand(new Card(Suit.HEARTS, Rank.THREE));
        player5.addToHand(new Card(Suit.CLUBS, Rank.SEVEN));
        checker5.checkEngine();
        assertEquals(Combination.FULL, player5.combo);
        assertEquals(7, player5.score);
        Player player6 = new Player();
        Checker checker6 = new Checker(player6);
        player6.addToHand(new Card(Suit.SPADES, Rank.SEVEN));
        player6.addToHand(new Card(Suit.SPADES, Rank.THREE));
        player6.addToHand(new Card(Suit.SPADES, Rank.ACE));
        player6.addToHand(new Card(Suit.SPADES, Rank.JACK));
        player6.addToHand(new Card(Suit.SPADES, Rank.QUEEN));
        checker6.checkEngine();
        assertEquals(Combination.COLOR, player6.combo);
        assertEquals(14, player6.score);
        Player player7 = new Player();
        Checker checker7 = new Checker(player7);
        player7.addToHand(new Card(Suit.HEARTS, Rank.ACE));
        player7.addToHand(new Card(Suit.CLUBS, Rank.JACK));
        player7.addToHand(new Card(Suit.SPADES, Rank.ACE));
        player7.addToHand(new Card(Suit.DIAMONDS, Rank.JACK));
        player7.addToHand(new Card(Suit.SPADES, Rank.QUEEN));
        checker7.checkEngine();
        assertEquals(Combination.TWO_PAIRS, player7.combo);
        assertEquals(14, player7.score);
        Player player8 = new Player();
        Checker checker8 = new Checker(player8);
        player8.addToHand(new Card(Suit.HEARTS, Rank.JACK));
        player8.addToHand(new Card(Suit.CLUBS, Rank.JACK));
        player8.addToHand(new Card(Suit.SPADES, Rank.TWO));
        player8.addToHand(new Card(Suit.DIAMONDS, Rank.SIX));
        player8.addToHand(new Card(Suit.SPADES, Rank.QUEEN));
        checker8.checkEngine();
        assertEquals(Combination.PAIR, player8.combo);
        assertEquals(11, player8.score);
        Player player9 = new Player();
        Checker checker9 = new Checker(player9);
        player9.addToHand(new Card(Suit.HEARTS, Rank.THREE));
        player9.addToHand(new Card(Suit.CLUBS, Rank.JACK));
        player9.addToHand(new Card(Suit.SPADES, Rank.TWO));
        player9.addToHand(new Card(Suit.DIAMONDS, Rank.SIX));
        player9.addToHand(new Card(Suit.SPADES, Rank.QUEEN));
        checker9.checkEngine();
        assertEquals(Combination.HIGHEST_CARD, player9.combo);
        assertEquals(12, player9.score);
    }

    @Test
    void testCardEquals(){
        Card c1 = new Card(Suit.SPADES, Rank.ACE);
        Card c2 = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(c1, c2);
    }




}


