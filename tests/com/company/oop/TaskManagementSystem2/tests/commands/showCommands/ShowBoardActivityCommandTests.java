package com.company.oop.TaskManagementSystem2.tests.commands.showCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.showCommands.ShowBoardActivityCommand;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.History;
import models.contracts.Member;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowBoardActivityCommandTests {
    private Command showBoardActivity;
    private Member member;

    private final List<Board> boardList = new ArrayList<>();

    @BeforeEach
    public void before() {
        TaskManagementRepository repository = new TaskManagementRepositoryImpl();
        this.showBoardActivity = new ShowBoardActivityCommand(repository);

        member = repository.createMember("Gosho");
        repository.login(member);

        Team team = repository.createTeam("TEAM7");
        repository.addTeam(team);

        String boardName = "BoardOne";
        Board board = repository.createBoard(boardName);
        repository.addBoard(board);
        boardList.add(board);
        board.addHistory("Test activity");

        CreateNewBoard createNewBoard = new CreateNewBoard(repository);
        createNewBoard.execute(List.of("BoardTwo", team.getName()));
        Board board2 = repository.findBoardByName("BoardTwo");
        boardList.add(board2);
        board2.addHistory("Test activity");
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_BOARD_ACTIVITY + 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showBoardActivity.execute(params));
    }

    @Test
    public void should_Show_Board_Activity_When_InputIsValid() {
        List<String> params = List.of(

        );
        StringBuilder sb = new StringBuilder();
        for (Board board : boardList) {
            sb.append(String.format("Board %s activity:", board.getName()));
            sb.append(System.lineSeparator());
            List<History> histories = board.getActivityHistory();
            for (History history : histories) {
                sb.append(history);
                sb.append(System.lineSeparator());
            }
        }


        Assertions.assertEquals(
                sb.toString()
                , showBoardActivity.execute(params)
        );
    }

}
