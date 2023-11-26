package com.company.oop.TaskManagementSystem2.tests.commands.showCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddMemberToTeamCommand;
import commands.contracts.Command;
import commands.showCommands.ShowTeamMembersCommand;
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

public class ShowTeamMemberCommandTests {
    private Command showTeamMember;
    private TaskManagementRepository repository;

    private final List<Member> memberList = new ArrayList<>();
    private Team team;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementRepositoryImpl();
        this.showTeamMember = new ShowTeamMembersCommand(repository);
        team = repository.createTeam("TEAM8");
        repository.addTeam(team);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_TEAM_MEMBERS + 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showTeamMember.execute(params));
    }


    @Test
    public void should_Show_Team_Activity_When_InputIsValid() {
        Member member = repository.createMember("Gosho");
        repository.login(member);
        repository.addMember(member);
        memberList.add(member);
        AddMemberToTeamCommand memberToTeamCommand = new AddMemberToTeamCommand(repository);
        memberToTeamCommand.execute(List.of(team.getName()));
        repository.logout();

        Member member2 = repository.createMember("Pesho");
        repository.login(member2);
        repository.addMember(member2);
        memberList.add(member2);
        AddMemberToTeamCommand memberToTeamCommand2 = new AddMemberToTeamCommand(repository);
        memberToTeamCommand2.execute(List.of(team.getName()));
        repository.logout();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("All members in team %s: ", team.getName()));
        for (Member member1 : memberList) {
            sb.append(member1.getName()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);


        List<String> params = List.of(
                team.getName()

        );
        Assertions.assertEquals(
                sb.toString().trim()
                , showTeamMember.execute(params)
        );
    }

    @Test
    public void should_ThrowException_When_ThereAreNoMemberInTeam() {
        List<String> params = List.of(
                team.getName()

        );
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ShowTeamMembersCommand.THERE_ARE_NO_MEMBERS_IN_S, team.getName()));
        Assertions.assertEquals(
                sb.toString()
                , showTeamMember.execute(params)
        );
    }
}