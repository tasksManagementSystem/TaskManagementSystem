package utils;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Task;
import models.contracts.Team;

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

    public static void validateMemberName(List<Member> memberNames, String nameToValidate, String message){
        for ( Member name : memberNames) {
            if(name.getName().equals(nameToValidate)){
                throw new IllegalArgumentException(message);
            }
        }
    }
    public static void validateTeamName(List<Team> teamNames, String nameToValidate, String message){
        for ( Team name : teamNames) {
            if(name.getName().equals(nameToValidate)){
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
