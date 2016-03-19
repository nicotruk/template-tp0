package ar.fiuba.tdd.template.tp0;

import java.util.*;

public class RegExGenerator {
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
        charSet.delete(0, charSet.length());
        CharIterator iterator = new CharIterator(regEx);
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            Character character = iterator.readNextCharacter();
            if (SpecialCharacter.isSpecialCharacter(character)) {
                SpecialCharacter specialCharacter = new SpecialCharacter(character);
                result.append(specialCharacter.operate(charSet, iterator));
            } else {
                appendOneIfCharSetNotEmpty(result);
                charSet.delete(0, charSet.length());
                charSet.append(character);
            }
        }
        appendOneIfCharSetNotEmpty(result);
        charSet.delete(0, charSet.length());
        return result.toString();
    }

    private void appendOneIfCharSetNotEmpty(StringBuilder stringToAppend) {
        if (charSet.length() != 0) {
            Random random = new Random();
            Integer randomCharacterPosition = random.nextInt(charSet.length());
            Character result = charSet.charAt(randomCharacterPosition);
            stringToAppend.append(result);
        }
    }
}