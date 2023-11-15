package utils;

import models.contracts.Board;

import java.util.List;

public class ValidationHelpers {

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String message) {
        validateIntRange(stringToValidate.length(), minLength, maxLength, message);
    }

    private static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);

        }
    }

    public static void validateMemberName(List<String> memberNames, String nameToValidate,String message){
        for (int i = 0; i <= memberNames.size(); i++){
            if(memberNames.get(i).equals(nameToValidate)){
                throw new IllegalArgumentException(message);
            }
        }
    }
    public static void validateTeamName(List<String> teamNames, String nameToValidate,String message){
        for (int i = 0; i <= teamNames.size(); i++){
            if(teamNames.get(i).equals(nameToValidate)){
                throw new IllegalArgumentException(message);
            }
        }
    }

    public static void validateBoardName(List<Board> boards, String nameToValidate, String message){
        for ( Board name : boards) {
            if(name.getName().equals(nameToValidate)){
                throw new IllegalArgumentException(message);
            }
        }
    }

    public static void validateArgumentCount (List<String>parameters,int count, String message ){
        if(parameters.size() != count){
            throw new IllegalArgumentException(message);

        }

    }

}
