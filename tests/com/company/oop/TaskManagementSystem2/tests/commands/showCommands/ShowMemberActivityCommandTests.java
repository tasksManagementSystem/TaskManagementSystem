package com.company.oop.TaskManagementSystem2.tests.commands.showCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.contracts.Command;
import commands.showCommands.ShowMemberActivityCommand;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.History;
import models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowMemberActivityCommandTests {
    private Command showMemberActivity;
    private TaskManagementRepository repository;

    private Member member;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementRepositoryImpl();
        this.showMemberActivity = new ShowMemberActivityCommand(repository);

        member.addHistory("Test activity");

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_MEMBER_ACTIVITY - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showMemberActivity.execute(params));
    }

    @Test
    public void should_Show_Member_Activity_When_InputIsValid() {
        List<String> params = List.of(
                member.getName()
        );

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Member %s activity:", member.getName()));
        sb.append(System.lineSeparator());
        List<History> histories = member.getActivityHistory();
        for (History history : histories) {
            sb.append(history);
            sb.append(System.lineSeparator());
        }

        Assertions.assertEquals(
                sb.toString()
                , showMemberActivity.execute(params)
        );
    }
}
