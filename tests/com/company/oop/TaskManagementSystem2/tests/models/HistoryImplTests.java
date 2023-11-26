package com.company.oop.TaskManagementSystem2.tests.models;

import models.HistoryImpl;
import models.contracts.History;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HistoryImplTests {

    private HistoryImpl history;

    @Test
    public void constructor_Should_Throw_When_Description_IsEmpty() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new HistoryImpl(
                        null

                ));
    }

    @Test
    public void constructor_Should_Throw_When_Description_IsEmpty_And_NoParameters() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new HistoryImpl(

                ));
    }

    @Test
    public void constructor_Should_Throw_When_There_Are_NoParameters() {
        // Arrange, Act,
        History history = new HistoryImpl("Test history");
        // Assert
        assertDoesNotThrow(() ->new HistoryImpl(
                        history.getDescription()

                ));
    }
}
