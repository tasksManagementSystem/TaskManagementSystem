package com.company.oop.TaskManagementSystem2.tests.utils;

import commands.createCommands.*;
import commands.showCommands.ShowMemberActivityCommand;
import models.TaskImpl;

import java.util.List;

public class TaskBaseConstants {

    public static final int VALID_ID = 1;
    public static final String VALID_TITLE = TestUtilities.getString(TaskImpl.TITLE_MIN_LENGTH + 1);
    public static final String INVALID_TITLE = TestUtilities.getString(TaskImpl.TITLE_MIN_LENGTH - 1);
    public static final String VALID_DESCRIPTION = TestUtilities.getString(TaskImpl.DESCRIPTION_MIN_LENGTH + 1);
    public static final String INVALID_DESCRIPTION = TestUtilities.getString(TaskImpl.DESCRIPTION_MIN_LENGTH - 1);


    //bug
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_BUG = CreateNewBug.EXPECTED_NUMBER_OF_ARGUMENTS;
    public static final String STEPS_TO_REPRODUCE_TEST = "Open the application, Click Log In, The application freezes!";
    public static final List<String> STEPS_TO_REPRODUCE = List.of("Open the application, Click Log In, The application freezes!");
    public static final String ASSIGNEE = "Gosho";


    //story
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_Story = CreateNewStory.EXPECTED_NUMBER_OF_ARGUMENTS;


    //feedback
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_FEEDBACK = CreateNewFeedback.EXPECTED_NUMBER_OF_ARGUMENTS;
    public static final int RATING = 12;

    //member
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_MEMBER = CreateNewMember.EXPECTED_NUMBER_OF_ARGUMENTS;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FOR_SHOW_MEMBER_ACTIVITY = ShowMemberActivityCommand.EXPECT_NUMBER_OF_ARGUMENTS;

    //team
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FOR_CREATE_NEW_TEAM = CreateNewTeam.EXPECTED_NUMBER_OF_ARGUMENTS;
    public static final int NAME_MIN_LENGTH = 5;
    public static final String VALID_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);

}

