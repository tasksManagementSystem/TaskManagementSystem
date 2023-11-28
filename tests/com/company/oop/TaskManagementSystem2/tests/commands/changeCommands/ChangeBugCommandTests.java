package com.company.oop.TaskManagementSystem2.tests.commands.changeCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddMemberToTeamCommand;
import commands.changeCommands.ChangeBugCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.createCommands.CreateNewBug;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Bug;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeBugCommandTests {

    private Command changeBugCommand;
    private TaskManagementRepository repository;
    String boardName = "BoardOne";


    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        this.changeBugCommand = new ChangeBugCommand(repository);

        Member member = repository.createMember("Gosho");
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
                TaskBaseConstants.STEPS_TO_REPRODUCE.toString(),
                Priority.LOW.toString(),
                Severity.CRITICAL.toString(),
                TaskBaseConstants.ASSIGNEE);
        CreateNewBug createNewBug = new CreateNewBug(repository);
        createNewBug.execute(param);

}

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CHANGE_BUG - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeBugCommand.execute(params));
    }

    @Test
    public void should_Change_Priority_When_InputIsValid(){

        Bug bug = (Bug) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","PRIORITY","HIGH");
        changeBugCommand.execute(changeParam);

        Assertions.assertEquals(Priority.HIGH,bug.getPriority());

    }@Test
    public void should_Change_Priority_When_InputIsValidTest(){

        Bug bug = (Bug) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","SIZE","HIGH");


        assertThrows(IllegalArgumentException.class, () -> changeBugCommand.execute(changeParam));

    }

    @Test
    public void should_Change_Status_When_InputIsValid(){

        Bug bug = (Bug) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","STATUS","DONE");
        changeBugCommand.execute(changeParam);

        Assertions.assertEquals(StatusBug.DONE,bug.getStatusBug());

    }

    @Test
    public void should_Change_Severity_When_InputIsValid(){

        Bug bug = (Bug) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","SEVERITY","MINOR");
        changeBugCommand.execute(changeParam);

        Assertions.assertEquals(Severity.MINOR,bug.getSeverity());

    }

}
