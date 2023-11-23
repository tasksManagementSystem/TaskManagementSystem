package com.company.oop.TaskManagementSystem2.tests.models;
import com.company.oop.TaskManagementSystem2.tests.utils.TestUtilities;
import models.MemberImpl;

import models.contracts.Member;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class MemberImplTests {
    private static final int NAME_MIN_LENGTH = 5;
    private static final String VALID_MEMBER_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);

    @Test
    public void MemberImpl_Should_ImplementMemberInterface() {
        // Arrange, Act
        MemberImpl member = initializeTestMember();
        // Assert
        Assertions.assertTrue(member instanceof Member);
    }
    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        Member member = initializeTestMember();
        Assertions.assertEquals(VALID_MEMBER_NAME, member.getName());
    }

    @Test
    public void constructor_Should_InitializeActivityHistoryList_When_ArgumentsAreValid() {
        Member member = initializeTestMember();
        Assertions.assertNotNull(member.getActivityHistory());
    }






    public static MemberImpl initializeTestMember() {
        return new MemberImpl(VALID_MEMBER_NAME);
    }
}
