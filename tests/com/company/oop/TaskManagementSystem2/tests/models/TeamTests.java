package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.BoardImpl;
import models.MemberImpl;
import models.TeamImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTests {

    private TeamImpl team;
    @BeforeEach
    public void setupTeam(){
       team = new TeamImpl(
               TaskBaseConstants.VALID_NAME
        );
    }
    @Test
    public void should_Return_TeamName_When_ValidValuesArePassed(){
         Assertions.assertEquals(TaskBaseConstants.VALID_NAME, team.getName());
    }

    @Test
    public void should_Add_MemberToTeam_When_MemberIsPassed(){
        MemberImpl testMember = new MemberImpl(
                TaskBaseConstants.VALID_NAME
        );
        team.addMembers(testMember);
        Assertions.assertEquals(team.getMembers().size(), 1);
    }

    @Test
    public void should_Add_BoardToTeam_When_BoardIsPassed(){
        BoardImpl testBoard = new BoardImpl(
                TaskBaseConstants.VALID_NAME
        );
        team.addBoards(testBoard);
        Assertions.assertEquals(team.getBoards().size(), 1);
    }


}
