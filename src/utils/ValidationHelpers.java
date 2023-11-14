package utils;

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

    public static void validateMemberName(List<String> memberNames, String nameToValidate){
        for (int i = 0; i <= memberNames.size(); i++){
            if(memberNames.get(i).equals(nameToValidate)){
                throw new IllegalArgumentException("This name already exist.");
            }
        }
    }
    public static void validateTeamName(List<String> teamNames, String nameToValidate){
        for (int i = 0; i <= teamNames.size(); i++){
            if(teamNames.get(i).equals(nameToValidate)){
                throw new IllegalArgumentException("This name already exist.");
            }
        }
    }


}
