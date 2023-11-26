package com.company.oop.TaskManagementSystem2.tests.commands.showCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.showCommands.ShowTeamBoardsCommand;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ShowTeamBoardsCommandTests {
    private Command showTeamBoards;
    private Member member;
    private Team team;
    private TaskManagementRepository repository;
    private final List<Board> boardList = new ArrayList<>();

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementRepositoryImpl();
        this.showTeamBoards = new ShowTeamBoardsCommand(repository);

        member = repository.createMember("Gosho");
        repository.login(member);

        team = repository.createTeam("TEAM7");
        repository.addTeam(team);


        CreateNewBoard createNewBoard = new CreateNewBoard(repository);
        createNewBoard.execute(List.of("BoardTwo", team.getName()));
        Board board2 = repository.findBoardByName("BoardTwo");
        boardList.add(board2);

    }
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_TEAM_BOARDS + 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showTeamBoards.execute(params));
    }

    @Test
    public void should_Show_Team_Boards_When_InputIsValid(){
        List<String> params = List.of(
            "TEAM7"
        );

        StringBuilder sb = new StringBuilder();
        for (Board board : boardList) {
            sb.append(String.format("All boards in team %s: ", team.getName()));
            sb.append(board.getName());
        }

        Assertions.assertEquals(
                sb.toString()
                ,showTeamBoards.execute(params)
        );

    }

    @Test
    public void should_Show_Team_Boards_Err_Message_When_No_Boards_Exists_In_Team(){
        List<String> params = List.of(
                "TEAM8"
        );

        team = repository.createTeam("TEAM8");
        repository.addTeam(team);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ShowTeamBoardsCommand.THERE_ARE_NO_BOARDS,team.getName()));
        Assertions.assertEquals(
                sb.toString()
                ,showTeamBoards.execute(params)
        );
    }

}
