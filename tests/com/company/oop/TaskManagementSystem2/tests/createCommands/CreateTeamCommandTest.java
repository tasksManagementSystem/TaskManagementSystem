package com.company.oop.TaskManagementSystem2.tests.createCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.contracts.Command;
import commands.createCommands.CreateNewTeam;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateTeamCommandTest {
    private Command createTeamCommand;
    private TaskManagementRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementRepositoryImpl();
        this.createTeamCommand = new CreateNewTeam(repository);


    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_TEAM - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> createTeamCommand.execute(params));
    }


    @Test
    public void should_CreateTeam_When_InputIsValid() {
        // Act
        String name = "TEAM7";
        List<String> params = List.of(
                name);

        // Act
        createTeamCommand.execute(params);
        Team team = repository.findTeamByName(name);

        //Assert

        Assertions.assertEquals("TEAM7", team.getName());

    }
}
