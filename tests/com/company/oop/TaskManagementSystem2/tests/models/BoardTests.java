package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.BoardImpl;
import models.BugImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
//    @Test
//    public void construct_Should_ThrowException_When_BoardNameIsInvalid() {
//        // Arrange, Act, Assert
//        assertThrows(IllegalArgumentException.class, () -> new BoardImpl(TaskBaseConstants.INVALID_NAME));
//    }

    @Test
    public void construct_Should_CreateCategory_When_NameIsValid() {
        // Arrange, Act, Assert
        assertDoesNotThrow(() -> new BoardImpl(TaskBaseConstants.VALID_NAME));
    }

    @Test
    public void construct_Should_InitializeNewListOfActivityHistory_When_CategoryIsCreated() {
        // Arrange, Act
        BoardImpl board = initializeTestBoard();

        // Assert
        assertNotNull(board.getActivityHistory());
    }

    @Test
    public void construct_Should_InitializeNewListOfActivityBugs_When_CategoryIsCreated() {
        // Arrange, Act
        BoardImpl board = initializeTestBoard();

        // Assert
        assertNotNull(board.getBugs());
    }

    @Test
    public void construct_Should_InitializeNewListOfFeedbacks_When_CategoryIsCreated() {
        // Arrange, Act
        BoardImpl board = initializeTestBoard();

        // Assert
        assertNotNull(board.getFeedbacks());
    }

    @Test
    public void construct_Should_InitializeNewListOfStories_When_CategoryIsCreated() {
        // Arrange, Act
        BoardImpl board = initializeTestBoard();

        // Assert
        assertNotNull(board.getStories());
    }

    @Test
    public void add_Should_addBugToList() {
        //Arrange
        BoardImpl board = initializeTestBoard();
        BugImpl bug = BugTests.initializeTestBug();
        // Act
        board.addBug(bug);
        // Assert
        Assertions.assertEquals(1, board.getBugs().size());

    }
//    @Test
//    public void add_Should_addStoryToList() {
//        //Arrange
//        BoardImpl board = initializeTestBoard();
//        Story story=StoryTests.initializeTestBug();
//        // Act
//        board.addStory(story);
//        // Assert
//        Assertions.assertEquals(1,board.getStories().size());
//
//    }
//    @Test
//    public void add_Should_addFeedbackToList() {
//        //Arrange
//        BoardImpl board = initializeTestBoard();
//        Feedback feedback=FeedbackTests.initializeTestBug();
//        // Act
//        board.addFeedback(feedback);
//        // Assert
//        Assertions.assertEquals(1,board.getFeedbacks().size());
//
//    }

    public static BoardImpl initializeTestBoard() {
        return new BoardImpl(TaskBaseConstants.VALID_NAME);
    }

}
