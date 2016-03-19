package ar.fiuba.tdd.template.tp0;

public class RegExCharIterator {
    private String inputString;
    private int position = 0;

    public RegExCharIterator(String inputString) {
        this.inputString = inputString;
    }

    public RegExChar readNextCharacter() {
        RegExChar next = new RegExChar(inputString.charAt(position));
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