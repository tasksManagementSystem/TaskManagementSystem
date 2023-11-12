package models;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;

import java.util.List;

public class TeamImpl implements Team {

    private String name;
    private List<Member> members;
    private List<Board> boards;

    public TeamImpl(String name, List<Member> members, List<Board> boards) {
        this.name = name;
        this.members = members;
        this.boards = boards;
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
}
