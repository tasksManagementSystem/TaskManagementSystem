package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.MemberImpl;
import models.TeamImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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



}
