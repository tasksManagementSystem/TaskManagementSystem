package com.company.oop.TaskManagementSystem2.tests.models;

import com.company.oop.TaskManagementSystem2.tests.utils.TaskBaseConstants;
import models.MemberImpl;
import models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MemberImplTests {
    private Member member;

    @BeforeEach
    public void setupBug() {
        member = new MemberImpl(TaskBaseConstants.VALID_NAME);
    }

    @Test
    public void MemberImpl_Should_ImplementMemberInterface() {
        // Arrange, Act, Assert
        Assertions.assertTrue(member instanceof Member);
    }

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        // Arrange, Act, Assert
        Assertions.assertEquals(TaskBaseConstants.VALID_NAME, member.getName());
    }

    @Test
    public void constructor_Should_InitializeActivityHistoryList_When_ArgumentsAreValid() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(member.getActivityHistory());
    }


}
