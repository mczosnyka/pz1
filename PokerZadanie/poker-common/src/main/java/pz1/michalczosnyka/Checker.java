package pz1.michalczosnyka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Checker {

    Player player;

    Checker(Player p){
        this.player = p;
    }

    void checkEngine(){
        if(calculatePoker()!=0 && player.combo==null){
            player.setCombo(Combination.POKER);
            player.setScore(calculatePoker());
        }
        else if(calculateQuad()!=0 && player.combo==null){
            player.setCombo(Combination.QUAD);
            player.setScore(calculateQuad());
        }
        else if(calculateFull()!=0 && player.combo==null){
            player.setCombo(Combination.FULL);
            player.setScore(calculateFull());
        }
        else if(calculateColor()!=0 && player.combo==null){
            player.setCombo(Combination.COLOR);
            player.setScore(calculateColor());
        }
        else if(calculateStraight()!=0 && player.combo==null){
            player.setCombo(Combination.STRAIGHT);
            player.setScore(calculateStraight());
        }
        else if(calculateThree()!=0 && player.combo==null){
            player.setCombo(Combination.THREE);
            player.setScore(calculateThree());
        }
        else if(calculateTwoPairs()!=0 && player.combo==null){
            player.setCombo(Combination.TWO_PAIRS);
            player.setScore(calculateTwoPairs());
        }
        else if(calculatePair()!=0 && player.combo==null){
            player.setCombo(Combination.PAIR);
            player.setScore(calculatePair());
        }
        else if(calculateHighestCard()!=0 && player.combo==null){
            player.setCombo(Combination.HIGHEST_CARD);
            player.setScore(calculateHighestCard());
        }

    }

    int calculateHighestCard() {
        Card hc = player.hand.entrySet().iterator().next().getKey();
        for (Map.Entry<Card, Integer> entry : player.hand.entrySet())
            if(entry.getKey().power > hc.power){
                hc = entry.getKey();
            }
        return hc.power;
    }

    int calculatePair() {
        if(player.hand.containsValue(2)){
            for (Map.Entry<Card, Integer> entry : player.hand.entrySet())
                if(entry.getValue() == 2){
                    return (entry.getKey().power);
                }
        }
        return 0;
    }

    int calculateTwoPairs(){
        int ret = 0;
        int count = 0;
        for (Integer value : player.hand.values()) {
            if (value == 2) {
                count++;
            }
        }
        if(count==2){
            for (Map.Entry<Card, Integer> entry : player.hand.entrySet())
                if(entry.getValue() == 2){
                    if(entry.getKey().power > ret){
                        ret = entry.getKey().power;
                    }
                }
        }
        return ret;
    }

    int calculateThree(){
        int ret = 0;
        if(player.hand.containsValue(3)){
            for (Map.Entry<Card, Integer> entry : player.hand.entrySet())
                if(entry.getValue() == 3){
                    return (entry.getKey().power);
                }
        }
        return ret;
    }

    int calculateQuad(){
        int ret = 0;
        if(player.hand.containsValue(4)){
            for (Map.Entry<Card, Integer> entry : player.hand.entrySet())
                if(entry.getValue() == 4){
                    return (entry.getKey().power);
                }
        }
        return ret;
    }

    int calculateColor(){
        Card hc = player.hand.entrySet().iterator().next().getKey();
        for (Map.Entry<Card, Integer> entry : player.hand.entrySet())
            if(entry.getKey().suit != hc.suit){
                return 0;
            }
            else{
                if(entry.getKey().power > hc.power){
                    hc = entry.getKey();
                }
            }
        return hc.power;
    }

    int calculateFull(){
        int ret = 0;
        int count = 0;
        for (Integer value : player.hand.values()) {
            if (value == 2) {
                count++;
            }
        }
        if (count==2 && player.hand.containsValue(3)) {
            for (Map.Entry<Card, Integer> entry : player.hand.entrySet())
                if(entry.getValue() == 3){
                    ret = entry.getKey().power;
                }
        }
        return ret;
    }

    int calculateStraight(){
        boolean ret;
        List<Integer> arr = new ArrayList<Integer>();
        for (Map.Entry<Card, Integer> entry : player.hand.entrySet()){
            arr.add(entry.getKey().power);
        }
        ret = straightCheck(arr);
        if(arr.contains(14) && !ret){
            arr.set(arr.indexOf(14), 1);
            ret = straightCheck(arr);
        }
        if(ret){
            return calculateHighestCard();
        }
        else{
            return 0;
        }
    }

    int calculatePoker(){
        if(calculateColor() != 0 && calculateStraight() != 0){
            return calculateHighestCard();
        }
        else{
            return 0;
        }
    }

    boolean straightCheck(List<Integer> arr){
        Collections.sort(arr);
        for(int i = arr.size()-1; i>0; i--){
            if((arr.get(i) - arr.get(i - 1))!=1){
                break;
            }
            if (i == 1) {
                return true;
            }
        }
        return false;
    }





}
