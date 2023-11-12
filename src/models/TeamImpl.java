package models;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;

import java.util.List;

public class TeamImpl implements Team {

    private String name;
    private List<Member> members;
    private List<Board> boards;

}
