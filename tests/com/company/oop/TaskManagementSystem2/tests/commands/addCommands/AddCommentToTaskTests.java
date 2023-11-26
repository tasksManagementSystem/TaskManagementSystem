package com.company.oop.TaskManagementSystem2.tests.commands.addCommands;

import com.company.oop.TaskManagementSystem2.tests.commands.LoginTests;
import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddCommentToTaskCommand;
import commands.addCommands.AddMemberToTeamCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.createCommands.CreateNewBug;
import commands.createCommands.CreateNewFeedback;
import commands.createCommands.CreateNewStory;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommentToTaskTests {
    private Command addCommentToTask;
    private TaskManagementRepository repository;
    public static final String COMMENT_ADDED_SUCCESSFULLY = "Comment added successfully to task %s by %s.";

    String author = "Gosho";
    String task = "Feedback";
    String boardName = "BoardOne";

    String writeComment = "This one took me a while, but it is fixed now!";


    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        this.addCommentToTask = new AddCommentToTaskCommand(repository);
        Member member = repository.createMember("Gosho");
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
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_ADD_COMMENT - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addCommentToTask.execute(params));
    }

    @Test
    public void should_Add_Comment_To_Feedback_When_InputIsValid() {
        List<String> param = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                String.valueOf(5));

        CreateNewFeedback createNewFeedback = new CreateNewFeedback(repository);
        createNewFeedback.execute(param);


        List<String> params = List.of(
                writeComment,
                author,
                task,
                String.valueOf(1),
                boardName


        );


        Assertions.assertEquals(
                String.format(COMMENT_ADDED_SUCCESSFULLY, task, author)
                , addCommentToTask.execute(params)
        );

    }

    @Test
    public void should_Add_Comment_To_Story_When_InputIsValid() {
        List<String> param = List.of(
                boardName,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW.toString(),
                Size.MEDIUM.toString(),
                "Gosho");

        CreateNewStory createNewStory = new CreateNewStory(repository);
        createNewStory.execute(param);


        List<String> params = List.of(
                writeComment,
                author,
                "Story",
                String.valueOf(1),
                boardName


        );


        Assertions.assertEquals(
                String.format(COMMENT_ADDED_SUCCESSFULLY, "Story", author)
                , addCommentToTask.execute(params)
        );

    }

    @Test
    public void should_Add_Comment_To_Bug_When_InputIsValid() {
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
                writeComment,
                author,
                "Bug",
                String.valueOf(1),
                boardName


        );


        Assertions.assertEquals(
                String.format(COMMENT_ADDED_SUCCESSFULLY, "Bug", author)
                , addCommentToTask.execute(params)
        );

    }

}
