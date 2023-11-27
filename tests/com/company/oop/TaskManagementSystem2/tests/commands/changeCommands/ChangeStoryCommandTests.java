package com.company.oop.TaskManagementSystem2.tests.commands.changeCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddMemberToTeamCommand;
import commands.changeCommands.ChangeBugCommand;
import commands.changeCommands.ChangeStoryCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.createCommands.CreateNewBug;
import commands.createCommands.CreateNewStory;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Bug;
import models.contracts.Member;
import models.contracts.Story;
import models.contracts.Team;
import models.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeStoryCommandTests {

    private Command changeStoryCommand;
    private TaskManagementRepository repository;
    String boardName = "BoardOne";


    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        this.changeStoryCommand = new ChangeStoryCommand(repository);

        Member member = repository.createMember("Pesho");
        repository.addMember(member);
        repository.login(member);

        Team team = repository.createTeam("TEAM7");
        repository.addTeam(team);

        AddMemberToTeamCommand memberToTeamCommand = new AddMemberToTeamCommand(repository);
        memberToTeamCommand.execute(List.of(team.getName()));

        CreateNewBoard createNewBoard = new CreateNewBoard(repository);
        createNewBoard.execute(List.of(boardName, team.getName()));

        List<String> param = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW.toString(),
                Size.MEDIUM.toString(),
                "Pesho");
        CreateNewStory createNewStory = new CreateNewStory(repository);
        createNewStory.execute(param);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CHANGE_STORY - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStoryCommand.execute(params));
    }

    @Test
    public void should_Change_Priority_When_InputIsValid(){

        Story story = (Story) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","PRIORITY","HIGH");
        changeStoryCommand.execute(changeParam);

        Assertions.assertEquals(Priority.HIGH,story.getPriority());

    }

    @Test
    public void should_Change_Status_When_InputIsValid(){

        Story story = (Story) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","STATUS","DONE");
        changeStoryCommand.execute(changeParam);

        Assertions.assertEquals(StatusStory.DONE,story.getStatusStory());

    }

    @Test
    public void should_Change_Severity_When_InputIsValid(){

        Story story = (Story) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","SIZE","LARGE");
        changeStoryCommand.execute(changeParam);

        Assertions.assertEquals(Size.LARGE,story.getSize());

    }

}

