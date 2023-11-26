package com.company.oop.TaskManagementSystem2.tests.commands.assignCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddMemberToTeamCommand;
import commands.assignCommands.AssignBugCommand;
import commands.assignCommands.AssignStoryCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.createCommands.CreateNewBug;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.Priority;
import models.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignBugTests {
    public static final String TASK_REASSIGNED_SUCCESSFULLY = "Assignee of bug with ID %d successfully reassigned from %s to %s!";
    private Command assignBugCommand;
    private TaskManagementRepository repository;
    String boardName = "BoardOne";
    int id = 1;
    String newAssign = "Petar";

    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        this.assignBugCommand = new AssignBugCommand(repository);

        Member member = repository.createMember("Gosho");
        repository.addMember(member);
        repository.login(member);

        Team team = repository.createTeam("TEAM7");
        repository.addTeam(team);

        AddMemberToTeamCommand memberToTeamCommand = new AddMemberToTeamCommand(repository);
        memberToTeamCommand.execute(List.of(team.getName()));

        CreateNewBoard createNewBoard = new CreateNewBoard(repository);
        createNewBoard.execute(List.of(boardName, team.getName()));
        repository.logout();

        Member member2 = repository.createMember("Petar");
        repository.addMember(member2);
        repository.login(member2);
        AddMemberToTeamCommand memberToTeamCommand2 = new AddMemberToTeamCommand(repository);
        memberToTeamCommand2.execute(List.of(team.getName()));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ASSIGN_BUG - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> assignBugCommand.execute(params));
    }

    @Test
    public void should_Assign_Bug_When_InputIsValid() {
        List<String> param = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS_TO_REPRODUCE.toString(),
                Priority.LOW.toString(),
                Severity.CRITICAL.toString(),
                "Gosho");
        CreateNewBug createNewBug = new CreateNewBug(repository);
        createNewBug.execute(param);

        List<String> params = List.of(
                String.valueOf(id),
                newAssign
        );
        Assertions.assertEquals(
                String.format(TASK_REASSIGNED_SUCCESSFULLY, id, "Gosho", newAssign)
                , assignBugCommand.execute(params)
        );

    }

    @Test
    public void should_ThrowException_When_AssigneeNotPartOfTeam() {
        List<String> param = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS_TO_REPRODUCE.toString(),
                Priority.LOW.toString(),
                Severity.CRITICAL.toString(),
                "Gosho");
        CreateNewBug createNewBug = new CreateNewBug(repository);
        createNewBug.execute(param);
        List<String> params = List.of(
                String.valueOf(id),
                "Stoyan");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignBugCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_BugNotPartOfTeam() {
        List<String> params = List.of(
                String.valueOf(id),
                "Stoyan");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignBugCommand.execute(params));
    }
}
