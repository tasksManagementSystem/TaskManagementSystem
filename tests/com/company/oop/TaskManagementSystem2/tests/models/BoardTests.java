package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.BoardImpl;
import models.BugImpl;
import models.FeedbackImpl;
import models.StoryImpl;
import models.contracts.Board;
import models.contracts.Feedback;
import models.contracts.Story;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
    private Board board;

    @BeforeEach
    public void setupBoard() {
        board = new BoardImpl(
                TaskBaseConstants.VALID_NAME);
    }

    @Test
    public void construct_Should_CreateCategory_When_NameIsValid() {
        // Arrange, Act, Assert
        assertDoesNotThrow(() -> new BoardImpl(TaskBaseConstants.VALID_NAME));
    }

    @Test
    public void construct_Should_InitializeNewListOfActivityHistory_When_CategoryIsCreated() {
        // Arrange, Act, Assert
        assertNotNull(board.getActivityHistory());
    }

    @Test
    public void construct_Should_InitializeNewListOfActivityBugs_When_CategoryIsCreated() {
        // Arrange, Act, Assert
        assertNotNull(board.getBugs());
    }

    @Test
    public void construct_Should_InitializeNewListOfFeedbacks_When_CategoryIsCreated() {
        // Arrange, Act, Assert
        assertNotNull(board.getFeedbacks());
    }

    @Test
    public void construct_Should_InitializeNewListOfStories_When_CategoryIsCreated() {
        // Arrange, Act, Assert
        assertNotNull(board.getStories());
    }

    @Test
    public void add_Should_addBugToList() {
        //Arrange
        BugImpl bug = new BugImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS_TO_REPRODUCE,
                Priority.LOW,
                Severity.CRITICAL,
                TaskBaseConstants.ASSIGNEE);
        // Act
        board.addBug(bug);
        // Assert
        Assertions.assertEquals(1, board.getBugs().size());

    }

    @Test
    public void add_Should_addStoryToList() {
        //Arrange

        Story story = new StoryImpl(TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                Priority.LOW,
                Size.SMALL,
                TaskBaseConstants.ASSIGNEE);
        // Act
        board.addStory(story);
        // Assert
        Assertions.assertEquals(1, board.getStories().size());

    }

    @Test
    public void add_Should_addFeedbackToList() {
        //Arrange
        Feedback feedback = new FeedbackImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.RATING);
        // Act
        board.addFeedback(feedback);
        // Assert
        Assertions.assertEquals(1, board.getFeedbacks().size());

    }


}
