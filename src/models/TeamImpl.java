package models;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {

    public static final String INVALID_NAME_MESSAGE = "Name should be between %d and %d symbols.";
    public static final int MAX_LENGTH = 15;
    public static final int MIN_LENGTH = 5;

    private String name;
    private List<Member> members;
    private List<Board> boards;

    public TeamImpl(String name, List<Member> members, List<Board> boards) {
        this.name = name;
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

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, MIN_LENGTH, MAX_LENGTH, INVALID_NAME_MESSAGE);
        this.name = name;
    }

    public void setMembers(List<Member> members) {
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
