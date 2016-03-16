package ar.fiuba.tdd.template.tp0;

public enum GroupCharacters {
    BRACKETS('[',']'), CURLY_BRACKETS('{','}');

    private Character beginCharacter;
    private Character endCharacter;

    GroupCharacters(Character beginCharacter, Character endCharacter){
        this.beginCharacter = beginCharacter;
        this.endCharacter = endCharacter;
    }

    private Character getBeginCharacter(){
        return beginCharacter;
    }

    private Character getEndCharacter() { return endCharacter; }

    public static Character getEndCharacterOf(Character beginCharacter) {
        for(GroupCharacters groupCharacter : GroupCharacters.values()) {
            if(groupCharacter.getBeginCharacter() == beginCharacter) {
                return groupCharacter.getEndCharacter();
            }
        }
        return null;
    }

    public static Boolean isGroupCharacter(Character character) {
        Boolean result = false;
        for(GroupCharacters groupCharacter : GroupCharacters.values()) {
            if(character.equals(groupCharacter.getBeginCharacter())){
                result = true;
            }
        }
        return result;
    }
}
