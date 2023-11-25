package commands.listCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.*;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class FilterCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String EMPTY_ERR_MESSAGE = "There is nothing to show.";
    public static final String THERE_ARE_NOT_ANY_EXISTING_TASKS_WITH_THIS_NAME = "There aren't any existing tasks with this name.";


    public FilterCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        String taskType = parameters.get(0);
        String filterBy = parameters.get(1);
        String pattern = parameters.get(2);

        return switch (taskType) {
            case "allTasks" -> filterAllTask(pattern);
            case "Bugs" -> filterBug(filterBy, pattern);
            case "Stories" -> filterStory(filterBy, pattern);
            case "Feedbacks" -> filterFeedback(pattern);
            case "Tasks" -> filterTask(filterBy, pattern);
            default ->
                    throw new IllegalArgumentException(THERE_ARE_NOT_ANY_EXISTING_TASKS_WITH_THIS_NAME);
        };
    }

    private String filterAllTask(String pattern) {
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(getRepository().getBugList());
        taskList.addAll(getRepository().getFeedbackList());
        taskList.addAll(getRepository().getStoryList());
        return listMatchingTitle(pattern, taskList);
    }

    private String listMatchingTitle(String pattern, List<Task> taskList) {
        String result = taskList.stream()
                .filter(task -> task.getTitle().contains(pattern))
                .collect(StringBuilder::new,
                        (stringBuilder, task) -> {
                            stringBuilder.append(task.toString());
                            stringBuilder.append(System.lineSeparator());
                            stringBuilder.append("--------------------");
                            stringBuilder.append(System.lineSeparator());
                        },
                        StringBuilder::append).toString();
        if (result.isEmpty()) {
            return EMPTY_ERR_MESSAGE;
        }
        else {
            return result.trim();
        }
    }

    private String filterBug(String filterBy, String pattern) {
        List<Bug> bugList = new ArrayList<>();
        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamByMember(member.getName());
        for (Board b : teamOfLoggedInMember.getBoards()) {
            bugList.addAll(b.getBugs());
        }
        switch (filterBy) {
            case "Status" -> {
                return listMatchingStatus(pattern, bugList);
            }
            case "Assignee" -> {
                return listMatchingAssignee(pattern, bugList);
            }
            case "StatusAndAssignee" -> {
                String[] filterParams = pattern.split("/");
                return listMatchingAssigneeAndStatus(bugList, filterParams);
            }
            default -> throw new IllegalArgumentException("Unable to filter this way.");
        }
    }

    private <T extends Task> String listMatchingStatus(String pattern, List<T> genericList) {
        String result = genericList.stream()
                .filter(taskType -> taskType.getStatusBug() == null || taskType.getStatusBug().toString().equals(pattern))
                .filter(taskType -> taskType.getStatusStory() == null || taskType.getStatusStory().toString().equals(pattern))
                .filter(taskType -> taskType.getStatusFeedback() == null || taskType.getStatusFeedback().toString().equals(pattern))
                .collect(StringBuilder::new,
                        (stringBuilder, taskType) -> {
                            stringBuilder.append(taskType);
                            stringBuilder.append(System.lineSeparator());
                            stringBuilder.append("--------------------");
                            stringBuilder.append(System.lineSeparator());
                        },
                        StringBuilder::append).toString();
        if (result.isEmpty()) {
            return EMPTY_ERR_MESSAGE;
        }
        else {
            return result.trim();
        }
    }


    private <T extends TaskInfo> String listMatchingAssignee(String pattern, List<T> genericList) {
        String result = genericList.stream()
                .filter(taskType -> taskType.getAssignee().equals(pattern))
                .collect(StringBuilder::new,
                        ((stringBuilder, taskType) -> {
                            stringBuilder.append(taskType);
                            stringBuilder.append(System.lineSeparator());
                            stringBuilder.append("--------------------");
                            stringBuilder.append(System.lineSeparator());
                        }), StringBuilder::append).toString();
        if (result.isEmpty()) {
            return EMPTY_ERR_MESSAGE;
        }
        else {
            return result.trim();
        }
    }

    private <T extends Task & TaskInfo> String listMatchingAssigneeAndStatus(List<T> genericList, String[] filterParams) {
        String result = genericList.stream()
                .filter(taskType -> taskType.getAssignee().equals(filterParams[1]))
                .filter(taskType -> taskType.getStatusBug() == null || taskType.getStatusBug().toString().equals(filterParams[0]))
                .filter(taskType -> taskType.getStatusStory() == null || taskType.getStatusStory().toString().equals(filterParams[0]))
                .filter(taskType -> taskType.getStatusFeedback() == null || taskType.getStatusFeedback().toString().equals(filterParams[0]))
                .collect(StringBuilder::new,
                        (stringBuilder, taskType) -> {
                            stringBuilder.append(taskType);
                            stringBuilder.append(System.lineSeparator());
                            stringBuilder.append("--------------------");
                            stringBuilder.append(System.lineSeparator());
                        },
                        StringBuilder::append).toString();
        if (result.isEmpty()) {
            return EMPTY_ERR_MESSAGE;
        }
        else {
            return result.trim();
        }
    }

    private String filterFeedback(String pattern) {
        List<Feedback> feedbackList = new ArrayList<>();
        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamByMember(member.getName());
        for (Board b : teamOfLoggedInMember.getBoards()) {
            feedbackList.addAll(b.getFeedbacks());
        }

        return listMatchingStatus(pattern, feedbackList);

    }

    private String filterStory(String filterBy, String pattern) {
        List<Story> storyList = new ArrayList<>();
        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamByMember(member.getName());
        for (Board b : teamOfLoggedInMember.getBoards()) {
            storyList.addAll(b.getStories());
        }
        switch (filterBy) {
            case "Status" -> {
                return listMatchingStatus(pattern, storyList);
            }
            case "Assignee" -> {
                return listMatchingAssignee(pattern, storyList);
            }
            case "StatusAndAssignee" -> {
                String[] filterParams = pattern.split("/");
                return listMatchingAssigneeAndStatus(storyList, filterParams);
            }
            default -> throw new IllegalArgumentException("Unable to filter this way.");
        }
    }

    private String filterTask(String filterBy, String pattern) {

        List<Story> storyList = new ArrayList<>();
        List<Bug> bugList = new ArrayList<>();
        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamByMember(member.getName());


        for (Board b : teamOfLoggedInMember.getBoards()) {
            storyList.addAll(b.getStories());
            bugList.addAll(b.getBugs());
        }

        StringBuilder sb = new StringBuilder();
        String storyString;
        String bugString;

        switch (filterBy) {
            case "Status":
                storyString = listMatchingStatus(pattern, storyList);
                bugString = listMatchingStatus(pattern, bugList);
                return printTaskFilterResult(sb, storyString, bugString);
            case "Assignee":
                storyString = listMatchingAssignee(pattern, storyList);
                bugString = listMatchingAssignee(pattern, bugList);
                return printTaskFilterResult(sb, storyString, bugString);
            case "StatusAndAssignee":
                String[] filterParams = pattern.split("/");
                storyString = listMatchingAssigneeAndStatus(storyList, filterParams);
                bugString = listMatchingAssigneeAndStatus(bugList, filterParams);
                return printTaskFilterResult(sb, storyString, bugString);
            default:
                throw new IllegalArgumentException("Unable to filter this way.");
        }
    }

    private static String printTaskFilterResult(StringBuilder sb, String storyString, String bugString) {
        if (!storyString.equals(bugString)){
            return sb.append(storyString).append(System.lineSeparator()).append(bugString).toString();
        } else {
            return sb.append(storyString).toString();
        }
    }



}