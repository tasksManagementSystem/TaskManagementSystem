package com.company.oop.TaskManagementSystem2.tests.createCommands;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import commands.contracts.Command;
import commands.createCommands.CreateNewMember;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateMemberCommandTests {

    private Command createMemberCommand;
    private TaskManagementRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementRepositoryImpl();
        this.createMemberCommand = new CreateNewMember(repository);


    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(TaskBaseConstants.EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_MEMBER - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> createMemberCommand.execute(params));
    }


    @Test
    public void should_CreateMember_When_InputIsValid() {
        // Act
        String name = "Maria";
        List<String> params = List.of(
                name);

        // Act
        createMemberCommand.execute(params);
        Member member = repository.findMemberByUsername(name);

        //Assert

        Assertions.assertEquals("Maria", member.getName());

    }

}
