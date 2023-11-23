package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.FeedbackImpl;
import models.contracts.Feedback;
import models.enums.StatusFeedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackTest {

    private FeedbackImpl feedback;

    @BeforeEach
    public void setupBug() {
        feedback = new FeedbackImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.RATING
        );
    }

    @Test
    public void FeedbackImpl_Should_ImplementFeedbackInterface() {
        // Arrange, Act, Assert
        Assertions.assertTrue(feedback instanceof Feedback);
    }

    @Test
    public void constructor_Should_Throw_When_TitleLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FeedbackImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.INVALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        TaskBaseConstants.RATING));
    }

    @Test
    public void constructor_Should_Throw_When_DescriptionLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FeedbackImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.INVALID_DESCRIPTION,
                        TaskBaseConstants.RATING));
    }

    @Test
    public void should_Create_Feedback_When_ValidValuesArePassed() {
        // Arrange, Act, Assert
        assertDoesNotThrow(() -> new FeedbackImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.RATING));
    }

    @Test
    public void change_Should_changeStatus_WithNewOne() {
        //Arrange, Act
        feedback.changeStatus(StatusFeedback.DONE);
        // Assert
        assertEquals(StatusFeedback.DONE, feedback.getStatusFeedback());

    }

    @Test
    public void change_Should_changeRating_WithNewOne() {
        //Arrange, Act
        feedback.changeRating(4);
        // Assert
        assertEquals(4, feedback.getRating());

    }


}
