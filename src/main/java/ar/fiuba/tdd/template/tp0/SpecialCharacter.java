package ar.fiuba.tdd.template.tp0;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpecialCharacter {
    private static final int ASCII_PRINTABLE_MAX = 127;
    private static final int ASCII_PRINTABLE_MIN = 32;
    private static Map<Character, OperativeCharacter> specialCharacters = new HashMap<>();
    private OperativeCharacter operativeCharacter;

    static {
        OperativeCharacter questionMarkCharacter =
                (StringBuilder charSet, RegExCharIterator nextCharIterator) -> { return generateSequence(charSet, 0, 1); };
        specialCharacters.put('?', questionMarkCharacter);
        OperativeCharacter asteriskCharacter =
                (StringBuilder charSet, RegExCharIterator nextCharIterator) -> { return generateSequence(charSet, 0, 10); };
        specialCharacters.put('*', asteriskCharacter);
        OperativeCharacter plusCharacter =
                (StringBuilder charSet, RegExCharIterator nextCharIterator) -> { return generateSequence(charSet, 1, 10); };
        specialCharacters.put('+', plusCharacter);
        OperativeCharacter dotCharacter =
                (StringBuilder charSet, RegExCharIterator nextCharIterator) -> { return getRandom(); };
        specialCharacters.put('.', dotCharacter);
        OperativeCharacter backslashCharacter =
                (StringBuilder charSet, RegExCharIterator nextCharIterator) -> { return escapeNextCharacter(charSet, nextCharIterator); };
        specialCharacters.put('\\', backslashCharacter);
        OperativeCharacter bracketCharacter =
                (StringBuilder charSet, RegExCharIterator nextCharIterator) -> { return catchGroup(']', charSet, nextCharIterator); };
        specialCharacters.put('[', bracketCharacter);
    }

    public SpecialCharacter(Character character) {
        for (Character specialCharacter : specialCharacters.keySet()) {
            if (character.equals(specialCharacter)) {
                operativeCharacter = specialCharacters.get(specialCharacter);
            }
        }
    }

    interface OperativeCharacter {
        String operate(StringBuilder charSet, RegExCharIterator nextCharIterator);
    }

    public static Boolean isSpecialCharacter(Character characterToEvaluate) {
        if (specialCharacters.containsKey(characterToEvaluate)) {
            return true;
        } else {
            return false;
        }
    }

    public String operate(StringBuilder charSet, RegExCharIterator nextCharIterator) {
        return operativeCharacter.operate(charSet, nextCharIterator);
    }

    private static String generateSequence(StringBuilder charSet, Integer minQuantity, Integer maxQuantity) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        Integer quantity = random.nextInt(maxQuantity - minQuantity + 1) + minQuantity;
        for (int i = 0; i < quantity; i++) {
            Integer randomCharacterPosition = random.nextInt(charSet.length());
            RegExChar chosenCharacter = new RegExChar(charSet.charAt(randomCharacterPosition));
            result.append(chosenCharacter.printLiteral());
        }
        charSet.delete(0, charSet.length());
        return result.toString();
    }

    public static String getRandom() {
        Random random = new Random();
        int randomCharacterPosition = random.nextInt(ASCII_PRINTABLE_MAX - ASCII_PRINTABLE_MIN + 1) + ASCII_PRINTABLE_MIN;
        StringBuilder result = new StringBuilder();
        result.append((char) randomCharacterPosition);
        return result.toString();
    }

    public static String escapeNextCharacter(StringBuilder charSet, RegExCharIterator nextCharIterator) {
        StringBuilder result = new StringBuilder();
        result.append(nextCharIterator.readNextCharacter().printLiteral());
        charSet.delete(0, charSet.length());
        return result.toString();
    }

    public static String catchGroup(Character endGroupCharacter, StringBuilder charSet, RegExCharIterator nextCharIterator) {
        Character character = nextCharIterator.readNextCharacter().printLiteral();
        while (!character.equals(endGroupCharacter)) {
            charSet.append(character);
            character = nextCharIterator.readNextCharacter().printLiteral();
        }
        return "";
    }
}
