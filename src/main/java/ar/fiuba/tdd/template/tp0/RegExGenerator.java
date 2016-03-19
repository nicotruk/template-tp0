package ar.fiuba.tdd.template.tp0;

import java.text.CharacterIterator;
import java.util.*;

public class RegExGenerator {
    RegExChar previousChar = null;
    StringBuilder charSet = new StringBuilder();

    public List<String> generate(String regEx, int numberOfResults) {
        ArrayList<String> result = new ArrayList<>();
        if (regEx != null) {
            for (int i = 0; i < numberOfResults; i++) {
                result.add(generateOneMatchingString(regEx));
            }
        }
        return result;
    }

    private String generateOneMatchingString(String regEx) {
        resetVariables();
        RegExCharIterator iterator = new RegExCharIterator(regEx);
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            RegExChar character = iterator.readNextCharacter();
            if (SpecialCharacter.isSpecialCharacter(character.printLiteral())) {
                if (charSet.length() == 0) {
                    if (previousChar != null) {
                        charSet.append(previousChar.printLiteral());
                        previousChar = null;
                    }
                }
                SpecialCharacter specialCharacter = new SpecialCharacter(character.printLiteral());
                result.append(specialCharacter.operate(charSet, iterator));
            } else {
                appendPreviousIfNotNull(result);
                previousChar = character;
            }
        }
        appendPreviousIfNotNull(result);
        return result.toString();
    }

    private void appendPreviousIfNotNull(StringBuilder stringToAppend) {
        appendPreviousCharIfNotNull(stringToAppend);
        appendOneIfCharSetNotEmpty(stringToAppend);
    }

    private void appendOneIfCharSetNotEmpty(StringBuilder stringToAppend) {
        if (charSet.length() != 0) {
            Random random = new Random();
            Integer randomCharacterPosition = random.nextInt(charSet.length());
            Character result = new RegExChar(charSet.toString().charAt(randomCharacterPosition)).printLiteral();
            stringToAppend.append(result);
            charSet = new StringBuilder();
        }
    }

    private void appendPreviousCharIfNotNull(StringBuilder stringToAppend) {
        if (previousChar != null) {
            stringToAppend.append(previousChar.printLiteral());
            previousChar = null;
        }
    }

    private void resetVariables() {
        previousChar = null;
        charSet = new StringBuilder();
    }
}