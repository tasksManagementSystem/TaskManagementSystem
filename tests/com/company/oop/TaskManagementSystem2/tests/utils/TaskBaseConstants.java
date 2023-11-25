package com.company.oop.TaskManagementSystem2.tests.utils;

import commands.createCommands.CreateNewBug;
import commands.showCommands.ShowMemberActivityCommand;
import models.TaskImpl;

import java.util.List;

public class TaskBaseConstants {

    public static final int VALID_ID = 1;
    //bug
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_BUG = CreateNewBug.EXPECTED_NUMBER_OF_ARGUMENTS;

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_MEMBER_ACTIVITY = ShowMemberActivityCommand.EXPECT_NUMBER_OF_ARGUMENTS;


    public static final String VALID_TITLE = TestUtilities.getString(TaskImpl.TITLE_MIN_LENGTH + 1);
    public static final String VALID_DESCRIPTION = TestUtilities.getString(TaskImpl.DESCRIPTION_MIN_LENGTH + 1);
    public static final String STEPS_TO_REPRODUCE_TEST ="Open the application, Click Log In, The application freezes!";
    public static final List <String> STEPS_TO_REPRODUCE = List.of("Open the application, Click Log In, The application freezes!");
    public static final String VALID_PRIORITY = "LOW";
    public static final String VALID_SEVERITY = "CRITICAL";
    public static final String ASSIGNEE = "Gosho";
    //story
    //feedback



    public static final int NAME_MIN_LENGTH = 5;

    public static final int RATING=12;


    public static final String VALID_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);
    public static final String INVALID_TITLE = TestUtilities.getString(TaskImpl.TITLE_MIN_LENGTH - 1);
    public static final String INVALID_DESCRIPTION = TestUtilities.getString(TaskImpl.DESCRIPTION_MIN_LENGTH - 1);
    public static final String INVALID_NAME = TestUtilities.getString(NAME_MIN_LENGTH - 1);

}

