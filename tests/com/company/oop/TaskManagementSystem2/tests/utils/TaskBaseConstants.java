package com.company.oop.TaskManagementSystem2.tests.utils;

public class TaskBaseConstants {
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MIN_LENGTH = 10;


    public static final String VALID_TITLE = TestUtilities.getString( TITLE_MIN_LENGTH+ 1);
    public static final String VALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH + 1);
    public static final String INVALID_TITLE = TestUtilities.getString(TITLE_MIN_LENGTH - 1);
    public static final String INVALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH - 1);
}

