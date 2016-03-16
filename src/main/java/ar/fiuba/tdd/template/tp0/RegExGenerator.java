package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.quantitycharacters.AsteriskCharacter;
import ar.fiuba.tdd.template.tp0.quantitycharacters.PlusCharacter;
import ar.fiuba.tdd.template.tp0.quantitycharacters.QuestionMarkCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegExGenerator {
    //private int maxLength;
    private QuantityCharacter quantityCharacter;
    private static Map<Character, QuantityCharacter> quantityCharacters = new HashMap<>();

    static {
        quantityCharacters.put('?', new QuestionMarkCharacter());
        quantityCharacters.put('*', new AsteriskCharacter());
        quantityCharacters.put('+', new PlusCharacter());
    }

    public RegExGenerator(/*int maxLength*/) {
        //this.maxLength = maxLength;
    }

    public List<String> generate(String regEx, int numberOfResults) {
        ArrayList<String> result = new ArrayList<>();
        if(regEx!=null) {
            for(int i=0;i<numberOfResults;i++){
                result.add(generateOneMatchingString(regEx));
            }
        }
        return result;
    }

    private String generateOneMatchingString(String regEx) {
        int position = 0;
        StringBuilder result = new StringBuilder();
        StringBuilder charSet = new StringBuilder();
        Character previousChar = null;
        while (position < regEx.length()){
            Character character = regEx.charAt(position);
            if (quantityCharacters.containsKey(character)){
                quantityCharacter = quantityCharacters.get(character);
                quantityCharacter.setCharSet(charSet.toString());
                result.append(quantityCharacter.generateSequence(charSet.toString()));
            } else if(EscapeCharacters.isEscapeCharacter(character)) {
                result.append(regEx.charAt(position+1));
                position+=1;
            } else if(GroupCharacters.isGroupCharacter(character)) {
                Character endCharacter = GroupCharacters.getEndCharacterOf(character);
                Character nextCharacter = regEx.charAt(++position);
                while(!nextCharacter.equals(endCharacter)){
                    charSet.append(nextCharacter);
                    nextCharacter = regEx.charAt(++position);
                }
            } else {
                if(previousChar!=null) {
                    result.append(previousChar);
                }
                previousChar = character;
                charSet.append(character);
            }
            position+=1;
        }
        if(previousChar!=null) {
            result.append(previousChar);
        }
        return result.toString();
    }
}