package com.company.oop.TaskManagementSystem2.tests.models;


import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.BugImpl;
import models.StoryImpl;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.Size;
import models.enums.StatusStory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoryImplTests {

    private StoryImpl testStory;

    @BeforeEach
    public void setupStory() {
        testStory = new StoryImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW,
                Size.SMALL,
                TaskBaseConstants.ASSIGNEE

        );
    }

    @Test
    public void should_Create_Story_When_ValidValuesArePassed() {
        assertAll(
                () -> assertEquals(1, testStory.getId()),
                () -> assertEquals(TaskBaseConstants.VALID_TITLE, testStory.getTitle()),
                () -> assertEquals(TaskBaseConstants.VALID_DESCRIPTION, testStory.getDescription()),
                () -> assertEquals(Priority.LOW, testStory.getPriority()),
                () -> assertEquals(Size.SMALL, testStory.getSize()),
                () -> assertEquals(TaskBaseConstants.ASSIGNEE, testStory.getAssignee()),
                () -> assertEquals(StatusStory.NOT_DONE, testStory.getStatusStory())
        );
    }

    @Test
    public void should_Change_Assignee_When_New_Assignee_Is_Passed() {
        testStory.changeAssignee("newTestAssgnee");
        Assertions.assertEquals("newTestAssgnee", testStory.getAssignee());
    }

    @Test
    public void should_Change_Status_When_New_Status_Is_Passed() {
        testStory.changeStatusStory(StatusStory.DONE);
        Assertions.assertEquals(StatusStory.DONE, testStory.getStatusStory());
    }

    @Test
    public void should_Change_Priority_When_New_Priority_Is_Passed() {
        testStory.changePriority(Priority.HIGH);
        Assertions.assertEquals(Priority.HIGH, testStory.getPriority());
    }

    @Test
    public void should_Change_Size_When_New_Size_Is_Passed() {
        testStory.changeSize(Size.LARGE);
        Assertions.assertEquals(Size.LARGE, testStory.getSize());
    }
    @Test
    public void Should_Change_Status_To_NotAssign_When_There_Is_NoAssign() {
        // Arrange, Act, Assert
        testStory.changeAssignee(null);
        assertDoesNotThrow(() -> new StoryImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW,
                Size.SMALL,
                null));
    }

    @Test
    public void constructor_Should_Throw_When_TitleLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.INVALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        Priority.LOW,
                        Size.SMALL,
                        TaskBaseConstants.ASSIGNEE

                ));
    }

    @Test
    public void constructor_Should_Throw_When_DescriptionLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StoryImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.INVALID_DESCRIPTION,
                        Priority.LOW,
                        Size.SMALL,
                        TaskBaseConstants.ASSIGNEE));
    }
}
