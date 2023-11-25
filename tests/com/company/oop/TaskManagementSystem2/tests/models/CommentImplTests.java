package com.company.oop.TaskManagementSystem2.tests.models;

import models.CommentImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentImplTests {

    @Test
    public void should_Create_Comment_When_ValidValuesArePassed() {
        // Arrange
        CommentImpl comment = new CommentImpl(

                "This one took me a while, but it is fixed now!",
                "Ivan");


// Act, Assert
        assertAll(
                () -> assertEquals("Ivan", comment.getAuthor()),
                () -> assertEquals("This one took me a while, but it is fixed now!", comment.getComment()));


    }
}
