package com.company.oop.TaskManagementSystem2.tests.createCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddMemberToTeamCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.createCommands.CreateNewStory;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import models.contracts.Story;
import models.contracts.Team;
import models.enums.Priority;
import models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateStoryCommandTests {

    private Command createStoryCommand;
    private TaskManagementRepository repository;

    private final String boardName = "BoardOne";

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementRepositoryImpl();
        this.createStoryCommand = new CreateNewStory(repository);

        Member member = repository.createMember("Pesho");
        repository.addMember(member);
        repository.login(member);

        Team team = repository.createTeam("TEAM5");
        repository.addTeam(team);

        AddMemberToTeamCommand memberToTeamCommand = new AddMemberToTeamCommand(repository);
        memberToTeamCommand.execute(List.of(team.getName()));

        CreateNewBoard createNewBoard = new CreateNewBoard(repository);
        createNewBoard.execute(List.of(boardName, team.getName()));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_Story - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> createStoryCommand.execute(params));
    }


    @Test
    public void should_CreateStory_When_InputIsValid() {
        // Act
        List<String> params = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW.toString(),
                Size.MEDIUM.toString(),
                "Pesho");

        // Act
        createStoryCommand.execute(params);

        Story story = (Story) repository.getTaskList().get(0);

        //Assert

        Assertions.assertEquals(TaskBaseConstants.VALID_TITLE, story.getTitle());
        Assertions.assertEquals(TaskBaseConstants.VALID_DESCRIPTION, story.getDescription());
        Assertions.assertEquals(Priority.LOW, story.getPriority());
        Assertions.assertEquals(Size.MEDIUM, story.getSize());
        Assertions.assertEquals("Pesho", story.getAssignee());

    }

    @Test
    public void should_ThrowException_When_AssigneeNotPartOfLoggedInMemberTeam() {
        List<String> params = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW.toString(),
                Size.MEDIUM.toString(),
                "Teodor");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createStoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_BoardNotPartOfTeam() {
        List<String> params = List.of(
                "BoardTwo",
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW.toString(),
                Size.MEDIUM.toString(),
                "Pesho");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createStoryCommand.execute(params));
    }
}
