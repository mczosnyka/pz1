package pz1.michalczosnyka;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ServerTest {
    @Test
    void testGameDefaultConstructor(){
        Game g = new Game();
        assertNotNull(g.deck);
        assertNotNull(g.playerClients);
    }

    @Test
    void testGameDistribute(){
        Game g = new Game();
        g.players.add(new Player());
        g.players.add(new Player());
        g.distribute();
        assertEquals(5, g.players.get(0).hand.size());
        assertEquals(5, g.players.get(1).hand.size());
    }

    @Test
    void testGameResults() throws IOException {
        Game g = new Game();
        g.players.add(new Player());
        g.players.add(new Player());
        g.players.get(0).setCombo(Combination.POKER);
        g.players.get(0).setScore(11);
        g.players.get(1).setCombo(Combination.STRAIGHT);
        g.players.get(1).setScore(13);
        g.results();
        assertEquals(g.players.get(0), g.win);
        g.players.get(0).setCombo(Combination.STRAIGHT);
        g.results();
        assertEquals(g.players.get(1), g.win);
    }


}
