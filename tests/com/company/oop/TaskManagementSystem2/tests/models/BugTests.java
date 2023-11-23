package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.BugImpl;
import models.contracts.Bug;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BugTests {
    @Test
    public void BugImpl_Should_ImplementBugInterface() {
        // Arrange, Act
        BugImpl bug = initializeTestBug();
        // Assert
        Assertions.assertTrue(bug instanceof Bug);
    }

    @Test
    public void constructor_Should_Throw_When_TitleLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        1,
                        TaskBaseConstants.INVALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        List.of("test1;test2;test3"),
                        Priority.LOW,
                        Severity.CRITICAL,
                        "Ivan"));
    }

    @Test
    public void constructor_Should_Throw_When_DescriptionLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        1,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.INVALID_DESCRIPTION,
                        List.of("test1;test2;test3"),
                        Priority.LOW,
                        Severity.CRITICAL,
                        "Ivan"));
    }

    @Test
    public void should_Create_Bug_When_ValidValuesArePassed() {
        // Arrange, Act, Assert
        assertDoesNotThrow(() -> new BugImpl(
                1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                List.of("test1;test2;test3"),
                Priority.LOW,
                Severity.CRITICAL,
                "Ivan"));
    }

    @Test
    public void changePriority_Bug() {
        //Arrange
        BugImpl bug = initializeTestBug();

        // Act
        bug.changePriority(Priority.MEDIUM);
        // Assert
       assertEquals(Priority.MEDIUM,bug.getPriority());

    }
    @Test
    public void changeSeverity_Bug() {
        //Arrange
        BugImpl bug = initializeTestBug();

        // Act
        bug.changeSeverity(Severity.MINOR);
        // Assert
        assertEquals(Severity.MINOR,bug.getSeverity());

    }
    @Test
    public void changeStatus_Bug() {
        //Arrange
        BugImpl bug = initializeTestBug();

        // Act
        bug.changeStatus(StatusBug.DONE);
        // Assert
        assertEquals(StatusBug.DONE,bug.getStatusBug());

    } @Test
    public void changeAssignee_Bug() {
        //Arrange
        BugImpl bug = initializeTestBug();

        // Act
        bug.changeAssignee("Pesho");
        // Assert
        assertEquals("Pesho",bug.getAssignee());

    }
    @Test
    public void getStepOfReproduce_Should_ReturnCopyOfCollection() {
        // Arrange
        BugImpl bug = initializeTestBug();
        List<String> stepOfReproduce = bug.getStepOfReproduce();
        List<String> copyOfStepOfReproduce = bug.getStepOfReproduce();

        // Act, Assert
        Assertions.assertNotSame(stepOfReproduce, copyOfStepOfReproduce);
    }
    private static BugImpl initializeTestBug() {
        return new BugImpl(
                1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                List.of("test1;test2;test3"),
                Priority.LOW,
                Severity.CRITICAL,
                "Ivan");

    }
}

