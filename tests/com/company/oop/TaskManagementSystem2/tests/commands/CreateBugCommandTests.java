package com.company.oop.TaskManagementSystem2.tests.commands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddMemberToTeamCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.createCommands.CreateNewBug;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.BugImpl;
import models.contracts.Bug;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.Priority;
import models.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateBugCommandTests {


    private Command createBugCommand;
    private TaskManagementRepository repository;

    private Member member;
    private Team team;
    private String boardName = "BoardOne";

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementRepositoryImpl();
        this.createBugCommand = new CreateNewBug(repository);

        member=repository.createMember("Gosho");
        repository.addMember(member);
        repository.login(member);

        team=repository.createTeam("TEAM7");
        repository.addTeam(team);

        AddMemberToTeamCommand memberToTeamCommand= new AddMemberToTeamCommand(repository);
        memberToTeamCommand.execute(List.of(team.getName()));

        CreateNewBoard createNewBoard=new CreateNewBoard(repository);
        createNewBoard.execute(List.of(boardName, team.getName()));
    }
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_BUG - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> createBugCommand.execute(params));
    }



    @Test
    public void should_CreateBug_When_InputIsValid() {
        // Act
        List<String> params = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS_TO_REPRODUCE.toString(),
                Priority.LOW.toString(),
                Severity.CRITICAL.toString(),
                TaskBaseConstants.ASSIGNEE);

        // Act
        String bugCreatedMessage=createBugCommand.execute(params);

      Bug bug= (Bug) repository.getTaskList().get(0);


        List<String> steps= bug.getStepOfReproduce();

        Assertions.assertEquals(TaskBaseConstants.VALID_TITLE, bug.getTitle());
        Assertions.assertEquals(TaskBaseConstants.VALID_DESCRIPTION,bug.getDescription());
        Assertions.assertEquals(TaskBaseConstants.STEPS_TO_REPRODUCE_TEST,bug.getStepOfReproduce().toString().replaceAll("[\\[\\]]",""));
        Assertions.assertEquals(Priority.LOW,bug.getPriority());
        Assertions.assertEquals(Severity.CRITICAL,bug.getSeverity());
        Assertions.assertEquals(TaskBaseConstants.ASSIGNEE,bug.getAssignee());

    }




}
