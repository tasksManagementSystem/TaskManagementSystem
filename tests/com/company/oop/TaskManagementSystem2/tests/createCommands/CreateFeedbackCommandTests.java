package com.company.oop.TaskManagementSystem2.tests.createCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddMemberToTeamCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.createCommands.CreateNewFeedback;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Feedback;
import models.contracts.Member;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateFeedbackCommandTests {

    private Command createFeedbackCommand;
    private TaskManagementRepository repository;

    private final String boardName = "BoardOne";

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementRepositoryImpl();
        this.createFeedbackCommand = new CreateNewFeedback(repository);

        Member member = repository.createMember("Gosho");
        repository.addMember(member);
        repository.login(member);

        Team team = repository.createTeam("TEAM7");
        repository.addTeam(team);

        AddMemberToTeamCommand memberToTeamCommand = new AddMemberToTeamCommand(repository);
        memberToTeamCommand.execute(List.of(team.getName()));

        CreateNewBoard createNewBoard = new CreateNewBoard(repository);
        createNewBoard.execute(List.of(boardName, team.getName()));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_FEEDBACK - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> createFeedbackCommand.execute(params));
    }


    @Test
    public void should_CreateFeedback_When_InputIsValid() {
        // Act
        List<String> params = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                String.valueOf(5));

        // Act
        createFeedbackCommand.execute(params);

        Feedback feedback = (Feedback) repository.getTaskList().get(0);

        //Assert

        Assertions.assertEquals(TaskBaseConstants.VALID_TITLE, feedback.getTitle());
        Assertions.assertEquals(TaskBaseConstants.VALID_DESCRIPTION, feedback.getDescription());
        Assertions.assertEquals("5", String.valueOf(feedback.getRating()));

    }


    @Test
    public void should_ThrowException_When_BoardNotPartOfTeam() {
        List<String> params = List.of(
                "BoardTwo",
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                String.valueOf(5));

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createFeedbackCommand.execute(params));
    }
}
