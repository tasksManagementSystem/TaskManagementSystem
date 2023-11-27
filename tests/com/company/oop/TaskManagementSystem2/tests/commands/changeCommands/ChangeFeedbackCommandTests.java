
package com.company.oop.TaskManagementSystem2.tests.commands.changeCommands;

        import com.company.oop.TaskManagementSystem2.tests.createCommands.CreateFeedbackCommandTests;
        import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
        import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
        import commands.addCommands.AddMemberToTeamCommand;
        import commands.changeCommands.ChangeBugCommand;
        import commands.changeCommands.ChangeFeedbackCommand;
        import commands.contracts.Command;
        import commands.createCommands.CreateNewBoard;
        import commands.createCommands.CreateNewBug;
        import commands.createCommands.CreateNewFeedback;
        import core.TaskManagementRepositoryImpl;
        import core.contracts.TaskManagementRepository;
        import models.contracts.Bug;
        import models.contracts.Feedback;
        import models.contracts.Member;
        import models.contracts.Team;
        import models.enums.Priority;
        import models.enums.Severity;
        import models.enums.StatusBug;
        import models.enums.StatusFeedback;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeFeedbackCommandTests {

    private Command changeFeedbackCommand;
    private TaskManagementRepository repository;
    String boardName = "BoardOne";


    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        this.changeFeedbackCommand = new ChangeFeedbackCommand(repository);

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
                String.valueOf(5));
        CreateNewFeedback createNewFeedback = new CreateNewFeedback(repository);
        createNewFeedback.execute(param);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CHANGE_FEEDBACK - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeFeedbackCommand.execute(params));
    }

    @Test
    public void should_Change_Status_When_InputIsValid(){

        Feedback feedback = (Feedback) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","STATUS","DONE");
        changeFeedbackCommand.execute(changeParam);

        Assertions.assertEquals(StatusFeedback.DONE,feedback.getStatusFeedback());

    }

    @Test
    public void should_Change_Rating_When_InputIsValid(){

        Feedback feedback = (Feedback) repository.getTaskList().get(0);

        List<String> changeParam = List.of("1","RATING","1");
        changeFeedbackCommand.execute(changeParam);

        Assertions.assertEquals(1,feedback.getRating());

    }

}
