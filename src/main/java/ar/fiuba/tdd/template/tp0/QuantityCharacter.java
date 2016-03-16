package ar.fiuba.tdd.template.tp0;

import java.util.List;
import java.util.Random;

public abstract class QuantityCharacter {
    public static final Integer maxAvailable = 10;
    private String character;
    private Integer quantity;
    private List<Character> charSet;
    private Random random = new Random();

    public QuantityCharacter(String character, Integer minQuantity, Integer maxQuantity){
        this.character = character;
        this.quantity = random.nextInt(maxQuantity-minQuantity+1)+minQuantity;
    }

    public void addToSet(Character character) {
        charSet.add(character);
    }

    public String getCharacter() {
        return character;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<Character> getCharSet() {
        return charSet;
    }

    public String generateSequence(String charSet) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<getQuantity(); i++) {
            Integer randomCharacterPosition = random.nextInt(getCharSet().size());
            result.append(getCharSet().get(randomCharacterPosition));
        }
        return result.toString();
    }
}
