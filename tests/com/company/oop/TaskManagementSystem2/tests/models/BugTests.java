package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.BugImpl;
import models.contracts.Bug;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BugTests {

    private BugImpl bug;

    @BeforeEach
    public void setupBug() {
        bug = new BugImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS_TO_REPRODUCE,
                Priority.LOW,
                Severity.CRITICAL,
                TaskBaseConstants.ASSIGNEE);
    }

    @Test
    public void BugImpl_Should_ImplementBugInterface() {
        // Arrange, Act, Assert
        Assertions.assertTrue(bug instanceof Bug);
    }

    @Test
    public void constructor_Should_Throw_When_TitleLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.INVALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        TaskBaseConstants.STEPS_TO_REPRODUCE,
                        Priority.LOW,
                        Severity.CRITICAL,
                        TaskBaseConstants.ASSIGNEE));
    }

    @Test
    public void constructor_Should_Throw_When_DescriptionLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.INVALID_DESCRIPTION,
                        TaskBaseConstants.STEPS_TO_REPRODUCE,
                        Priority.LOW,
                        Severity.CRITICAL,
                        TaskBaseConstants.ASSIGNEE));
    }

    @Test
    public void should_Create_Bug_When_ValidValuesArePassed() {
        // Arrange, Act, Assert
        assertDoesNotThrow(() -> new BugImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS_TO_REPRODUCE,
                Priority.LOW,
                Severity.CRITICAL,
                TaskBaseConstants.ASSIGNEE));
    }

    @Test
    public void change_Should_changePriority_WithNewOne() {
        //Arrange, Act
        bug.changePriority(Priority.MEDIUM);
        // Assert
        assertEquals(Priority.MEDIUM, bug.getPriority());

    }

    @Test
    public void change_Should_changeSeverity_WithNewOne() {
        //Arrange, Act
        bug.changeSeverity(Severity.MINOR);
        // Assert
        assertEquals(Severity.MINOR, bug.getSeverity());

    }

    @Test
    public void change_Should_changeStatus_WithNewOne() {
        //Arrange, Act
        bug.changeStatusBug(StatusBug.DONE);
        // Assert
        assertEquals(StatusBug.DONE, bug.getStatusBug());

    }

    @Test
    public void change_Should_changeAssignee_WithNewOne() {
        //Arrange, Act
        bug.changeAssignee("Pesho");
        // Assert
        assertEquals("Pesho", bug.getAssignee());

    }

    @Test
    public void getStepOfReproduce_Should_ReturnCopyOfCollection() {
        // Arrange, Act

        bug.getStepOfReproduce().clear();

        // Assert
        Assertions.assertEquals(1, bug.getStepOfReproduce().size());

    }


}

