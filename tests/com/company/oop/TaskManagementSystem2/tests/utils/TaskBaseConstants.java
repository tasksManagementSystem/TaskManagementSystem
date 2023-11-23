package com.company.oop.TaskManagementSystem2.tests.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskBaseConstants {
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int NAME_MIN_LENGTH = 5;
    public static final int VALID_ID = 1;
    public static final int RATING=12;

    public static final List<String> STEP_TO_REPRODUCE=List.of("test1;test2;test3");
    public static final String ASSIGNEE="Ivan";

    public static final String VALID_TITLE = TestUtilities.getString( TITLE_MIN_LENGTH+ 1);
    public static final String VALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH + 1);
    public static final String VALID_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);
    public static final String INVALID_TITLE = TestUtilities.getString(TITLE_MIN_LENGTH - 1);
    public static final String INVALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH - 1);
    public static final String INVALID_NAME = TestUtilities.getString(NAME_MIN_LENGTH - 1);
}

