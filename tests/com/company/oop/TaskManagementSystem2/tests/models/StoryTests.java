package com.company.oop.TaskManagementSystem2.tests.models;


import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.StoryImpl;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StatusStory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoryTests {

    private StoryImpl testStory;

    @BeforeEach
    public void setupStory(){
        testStory = new StoryImpl(
                1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW,
                Size.SMALL,
                "testAssignee"

        );
    }

    @Test
    public void should_Create_Story_When_ValidValuesArePassed(){
        assertAll(
                () -> assertEquals(1, testStory.getId()),
                () -> assertEquals(TaskBaseConstants.VALID_TITLE, testStory.getTitle()),
                () -> assertEquals(TaskBaseConstants.VALID_DESCRIPTION, testStory.getDescription()),
                () -> assertEquals(Priority.LOW, testStory.getPriority()),
                () -> assertEquals(Size.SMALL, testStory.getSize()),
                () -> assertEquals("testAssignee", testStory.getAssignee()),
                () -> assertEquals(StatusStory.NOT_DONE, testStory.getStatusStory())
        );
    }
    @Test
    public void shoud_Change_Assignee_When_New_Assignee_Is_Passed(){
        testStory.changeAssignee("newTestAssgnee");
        Assertions.assertEquals("newTestAssgnee", testStory.getAssignee());
    }

    @Test
    public void shoud_Change_Status_When_New_Status_Is_Passed(){
        testStory.changeStatus(StatusStory.DONE);
        Assertions.assertEquals(StatusStory.DONE, testStory.getStatusStory());
    }
    @Test
    public void shoud_Change_Priority_When_New_Priority_Is_Passed(){
        testStory.changePriority(Priority.HIGH);
        Assertions.assertEquals(Priority.HIGH, testStory.getPriority());
    }
    @Test
    public void shoud_Change_Size_When_New_Size_Is_Passed(){
        testStory.changeSize(Size.LARGE);
        Assertions.assertEquals(Size.LARGE, testStory.getSize());
    }
}
