package models;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {

    public static final String INVALID_NAME_MESSAGE = "Name should be between %d and %d symbols.";
    public static final int NAME_MAX_LENGTH = 15;
    public static final int NAME_MIN_LENGTH = 5;

    static List<String> teamNames;
    private String name;
    private List<Member> members;
    private List<Board> boards;

    public TeamImpl(String name, List<Member> members, List<Board> boards) {
        setName(name);
        this.members = new ArrayList<>(members);
        this.boards = new ArrayList<>(boards);
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Board> getBoards() {
        return boards;
    }


    private void setName(String name) {
        ValidationHelpers.validateMemberName(teamNames,name);
        ValidationHelpers.validateTeamName(teamNames,name);
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,String.format(INVALID_NAME_MESSAGE));
        this.name = name;
        teamNames.add(name);
    }


    private void setMembers(List<Member> members) {
    }

    private void setBoards(List<Board> boards) {

        this.boards = boards;
    }

    public String showAllTeams(){
        StringBuilder allNames = new StringBuilder();
        for (String name :
                teamNames) {
            allNames.append(name + " ");
        }
        return allNames.toString();
    }
}
