package com.company.oop.TaskManagementSystem2.tests.commands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import commands.LogoutCommand;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.MemberImpl;
import models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LogoutTests {
    private TaskManagementRepositoryImpl repository;
    private LogoutCommand logoutCommand;

    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        logoutCommand = new LogoutCommand(repository);
    }


    @Test
    public void should_Throw_When_UserNotLoggedIn() {

        // Arrange, Act, Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> logoutCommand.execute(new ArrayList<>()));
    }

    @Test
    public void should_LogoutUser() {
        // Arrange
        Member memberToLogIn = new MemberImpl(TaskBaseConstants.VALID_NAME);
        repository.login(memberToLogIn);

        // Act
        logoutCommand.execute(new ArrayList<>());

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.getLoggedInMember());
    }
}
