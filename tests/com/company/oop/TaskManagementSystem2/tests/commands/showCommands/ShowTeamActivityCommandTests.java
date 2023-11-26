package com.company.oop.TaskManagementSystem2.tests.commands.showCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.BaseCommand;
import commands.addCommands.AddMemberToTeamCommand;
import commands.contracts.Command;
import commands.showCommands.ShowTeamActivityCommand;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.History;
import models.contracts.Member;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowTeamActivityCommandTests {
    private Command showTeamActivity;

    private final List<Team> teamList = new ArrayList<>();

    @BeforeEach
    public void before() {
        TaskManagementRepository repository = new TaskManagementRepositoryImpl();
        this.showTeamActivity = new ShowTeamActivityCommand(repository);

        Member member = repository.createMember("Gosho");
        repository.addMember(member);
        repository.login(member);

        Team team = repository.createTeam("TEAM7");
        teamList.add(team);
        repository.addTeam(team);

        AddMemberToTeamCommand memberToTeamCommand = new AddMemberToTeamCommand(repository);
        memberToTeamCommand.execute(List.of(team.getName()));
        member.addHistory("Test activity");
        repository.logout();

        Member member2 = repository.createMember("Pesho");
        repository.addMember(member2);
        repository.login(member2);

        team = repository.createTeam("TEAM8");
        teamList.add(team);
        repository.addTeam(team);

        AddMemberToTeamCommand memberToTeamCommand2 = new AddMemberToTeamCommand(repository);
        memberToTeamCommand2.execute(List.of(team.getName()));

        member.addHistory("Test activity");

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_TEAM_ACTIVITY + 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showTeamActivity.execute(params));
    }


    @Test
    public void should_Show_Team_Activity_When_InputIsValid() {
        List<String> params = List.of(

        );

        StringBuilder sb = new StringBuilder();
        for (Team team : teamList) {
            sb.append(String.format("Team %s activity:", team.getName()));
            sb.append(System.lineSeparator());
            List<Member> memberList = team.getMembers();
            for (Member member : memberList) {
                sb.append(String.format("Member: %s", member.getName()));
                sb.append(System.lineSeparator());
                List<History> histories = member.getActivityHistory();
                for (History history : histories) {
                    sb.append(history);
                    sb.append(System.lineSeparator());
                }

            }
            sb.append(System.lineSeparator());


        }
        Assertions.assertEquals(
                sb.toString()
                , showTeamActivity.execute(params)
        );
    }
}


