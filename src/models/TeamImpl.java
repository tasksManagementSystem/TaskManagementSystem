package models;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {

    private String name;
    private List<Member> members;
    private List<Board> boards;

    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
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
        this.name = name;
    }


    public void addMembers(Member member) {
        members.add(member);
    }

    @Override
    public String toString() {
        return "TeamImpl{" +
                "name='" + name + '\'' +
                ", members=" + members +
                ", boards=" + boards +
                '}';
    }
}
