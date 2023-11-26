package com.company.oop.TaskManagementSystem2.tests.commands.showCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.addCommands.AddMemberToTeamCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewMember;
import commands.showCommands.ShowAllMembersCommand;
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

public class ShowAllMembersCommandTests {
    private Command showAllMembers;
    private TaskManagementRepository repository;
    private final List<Team> teamList = new ArrayList<>();
    private final List<Member> memberList = new ArrayList<>();

    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        this.showAllMembers = new ShowAllMembersCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_ALL_MEMBERS_ACTIVITY + 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showAllMembers.execute(params));
    }

    @Test
    public void should_Show_Member_Activity_When_InputIsValid() {
        Member member = repository.createMember("Gosho");
        repository.addMember(member);
        memberList.add(member);
        repository.login(member);
        Team team = repository.createTeam("TEAM7");
        teamList.add(team);
        repository.addTeam(team);
        AddMemberToTeamCommand memberToTeamCommand = new AddMemberToTeamCommand(repository);
        memberToTeamCommand.execute(List.of(team.getName()));
        repository.logout();

        Member member3 = repository.createMember("Angela");
        repository.addMember(member3);
        memberList.add(member3);
        repository.login(member3);
        AddMemberToTeamCommand memberToTeamCommand2 = new AddMemberToTeamCommand(repository);
        memberToTeamCommand2.execute(List.of(team.getName()));
        repository.logout();

        CreateNewMember createNewMember = new CreateNewMember(repository);
        createNewMember.execute(List.of("Petar"));
        Member member2 = repository.findMemberByUsername("Petar");
        repository.addMember(member2);
        memberList.add(member2);
        repository.login(member2);

        Team team2 = repository.createTeam("TEAM8");
        teamList.add(team2);
        repository.addTeam(team2);

        AddMemberToTeamCommand memberToTeamCommand3 = new AddMemberToTeamCommand(repository);
        memberToTeamCommand3.execute(List.of(team2.getName()));

        StringBuilder sb = new StringBuilder();
        sb.append("All members: ");

        for (Member member1 : memberList) {
            sb.append(member1.getName()).append(", ");
        }


        sb.deleteCharAt(sb.length() - 2);

        List<String> params = List.of(

        );

        Assertions.assertEquals(
                sb.toString().trim()
                , showAllMembers.execute(params)
        );
    }

    @Test
    public void should_ThrowException_When_ThereAreNoMemberInTeam() {
        List<String> params = List.of(

        );
        Team team = repository.createTeam("TEAM9");
        teamList.add(team);
        repository.addTeam(team);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllMembers.execute(params));

    }
}
