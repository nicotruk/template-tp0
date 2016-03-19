package ar.fiuba.tdd.template.tp0;

public class CharIterator {
    private String inputString;
    private int position = 0;

    public CharIterator(String inputString) {
        this.inputString = inputString;
    }

    public Character readNextCharacter() {
        Character next = inputString.charAt(position);
        position++;
        return next;
    }

    public Boolean hasNext() {
        if (position < inputString.length()) {
            return true;
        } else {
            return false;
        }
    }

}