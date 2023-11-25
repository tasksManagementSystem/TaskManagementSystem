package com.company.oop.TaskManagementSystem2.tests.commands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.BaseCommand;
import commands.LoginCommand;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.MemberImpl;
import models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LoginTests {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private TaskManagementRepository repository;
    private LoginCommand loginCommand;

    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        loginCommand = new LoginCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_LoginUser_When_UserNotLoggedIn() {
        // Arrange
        Member memberToLogIn = new MemberImpl(TaskBaseConstants.VALID_NAME);
        repository.addMember(memberToLogIn);
        List<String> params = List.of(memberToLogIn.getName());

        // Act
        loginCommand.execute(params);

        // Assert
        Assertions.assertEquals(memberToLogIn.getName(), repository.getLoggedInMember().getName());
    }


    @Test
    public void should_Throw_When_UserDoesNotExists() {
        // Arrange
        List<String> params = List.of(
                TaskBaseConstants.VALID_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_Throw_When_UserAlreadyLoggedIn() {
        // Arrange
        Member memberToLogIn = loginInitializedUserToRepository(repository);
        List<String> params = List.of(memberToLogIn.getName());

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_Login_When_InputIsValid() {
        // Arrange
        Member memberToLogin = new MemberImpl(TaskBaseConstants.VALID_NAME);
        repository.addMember(memberToLogin);
        List<String> params = List.of(
                memberToLogin.getName());


        // Act
        loginCommand.execute(params);

        // Assert
        Assertions.assertEquals(repository.getLoggedInMember().getName(), memberToLogin.getName());
    }

    public static Member loginInitializedUserToRepository(TaskManagementRepository repository) {
        Member testMember = new MemberImpl(TaskBaseConstants.VALID_NAME);
        repository.addMember(testMember);
        repository.login(testMember);
        return testMember;
    }


}

