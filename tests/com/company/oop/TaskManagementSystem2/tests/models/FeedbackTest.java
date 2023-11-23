package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.BugImpl;
import models.FeedbackImpl;
import models.contracts.Feedback;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusFeedback;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackTest {
    private FeedbackImpl feedback;

    @BeforeEach
    public static FeedbackImpl initializeTestFeedback() {
        return new FeedbackImpl(
                1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                12
        );
    }
    @Test
    public void BugImpl_Should_ImplementFeedbackInterface() {
        // Arrange, Act
        FeedbackImpl feedback = initializeTestFeedback();
        // Assert
        Assertions.assertTrue(feedback instanceof Feedback);
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
    public void should_Create_Feedback_When_ValidValuesArePassed() {
        // Arrange, Act, Assert
        assertDoesNotThrow(() -> new FeedbackImpl(
                1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                10));
    }

    @Test
    public void change_Should_changeStatus_WithNewOne() {
        //Arrange
        FeedbackImpl feedback = initializeTestFeedback();

        // Act
        feedback.changeStatus(StatusFeedback.DONE);
        // Assert
        assertEquals(StatusFeedback.DONE, feedback.getStatusFeedback());

    }
    @Test
    public void change_Should_changeRating_WithNewOne() {
        //Arrange
        FeedbackImpl feedback = initializeTestFeedback();

        // Act
        feedback.changeRating(4);
        // Assert
        assertEquals(4, feedback.getRating());

    }






}
