package com.company.oop.TaskManagementSystem2.tests.commands.showCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.contracts.Command;
import commands.createCommands.CreateNewTeam;
import commands.showCommands.ShowAllTeamsCommand;
import commands.showCommands.ShowTeamActivityCommand;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowAllTeamsCommandTests {
    private Command showAllTeamsCommand;

    private final List<Team> teamList = new ArrayList<>();

    @BeforeEach
    public void before() {
        TaskManagementRepository repository = new TaskManagementRepositoryImpl();
        this.showAllTeamsCommand = new ShowAllTeamsCommand(repository);

        Member member = repository.createMember("Gosho");
        repository.login(member);

        Team team = repository.createTeam("TEAM7");
        teamList.add(team);
        repository.addTeam(team);

        CreateNewTeam createNewTeam = new CreateNewTeam(repository);
        createNewTeam.execute(List.of("TEAM8"));
        Team team2 = repository.findTeamByName("TEAM8");
        teamList.add(team2);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_TEAM_ACTIVITY + 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showAllTeamsCommand.execute(params));
    }

    @Test
    public void should_Show_Team_Activity_When_InputIsValid() {
        List<String> params = List.of(

        );

        StringBuilder sb = new StringBuilder();
        sb.append("All teams: ");
        for (Team team1 : teamList) {
            sb.append((team1.getName())).append( ", ");
        }
        sb.deleteCharAt(sb.length() - 2);


        Assertions.assertEquals(
                sb.toString().trim()
                , showAllTeamsCommand.execute(params)
        );
    }
}
