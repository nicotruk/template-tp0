package ar.fiuba.tdd.template.tp0;

public enum EscapeCharacters {
    BACKSLASH('\\');

    private Character character;

    EscapeCharacters(Character character) {
        this.character = character;
    }

    private Character getCharacter(){
        return character;
    }

    public static Boolean isEscapeCharacter(Character character) {
        Boolean result = false;
        for(EscapeCharacters escapeCharacter : EscapeCharacters.values()) {
            if(character.equals(escapeCharacter.getCharacter())){
                result = true;
            }
        }
        return result;
    }
}
