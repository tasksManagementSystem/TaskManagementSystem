package commands.listCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.*;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String EMPTY_ERR_MESSAGE = "There is nothing to show.";
    public static final String THERE_ARE_NOT_ANY_EXISTING_TASKS_WITH_THIS_NAME = "There aren't any existing tasks with this name.";

    public SortCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    public String execute(List<String> parameters){
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);

        String taskType = parameters.get(0);
        String sortBy = parameters.get(1);
        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamByMember(member.getName());
        return switch (taskType) {
            case "allTasks" -> sortAllTasks(sortBy, teamOfLoggedInMember);
            case "Bugs" -> sortBug(sortBy, teamOfLoggedInMember);
            case "Stories" -> sortStory(sortBy, teamOfLoggedInMember);
            case "Feedbacks" -> sortFeedback(sortBy, teamOfLoggedInMember);
            case "allTasksWithAssignee" -> sortTasksWithAssignee(sortBy, teamOfLoggedInMember);
            default ->
                    throw new IllegalArgumentException(THERE_ARE_NOT_ANY_EXISTING_TASKS_WITH_THIS_NAME);
        };
    }

    private <T> String sort(List<T> list, Comparator<T> comparator) {
        String result = list.stream()
                .sorted(comparator)
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
        } else {
            return result.trim();
        }
    }

    private String sortAllTasks(String sortBy, Team teamOfLoggedInMember) {
        if (!sortBy.equals("Title")) {
            throw new IllegalArgumentException("Unable to filter this way.");
        }

        List<Task> taskList = new ArrayList<>();
        taskList.addAll(getRepository().getBugList());
        taskList.addAll(getRepository().getFeedbackList());
        taskList.addAll(getRepository().getStoryList());

        StringBuilder sb = new StringBuilder();
        taskList.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .forEach(task -> {
                    sb.append(task.toString());
                    sb.append(System.lineSeparator());
                    sb.append("--------------------");
                    sb.append(System.lineSeparator());
                });

        if (sb.isEmpty()) {
            return EMPTY_ERR_MESSAGE;
        } else {
            return sb.toString().trim();
        }
    }

    private String sortBug(String sortBy, Team teamOfLoggedInMember) {
        List<Bug> bugList = new ArrayList<>();

        for (Board b : teamOfLoggedInMember.getBoards()) {
            bugList.addAll(b.getBugs());
        }
        switch (sortBy) {

            case "Title" -> {
                return sort(bugList, Comparator.comparing(Bug::getTitle));

            }
            case "Status" -> {
                return sort(bugList, Comparator.comparing(Bug::getStatusBug));

            }
            case "Priority" -> {
                return sort(bugList, Comparator.comparing(Bug::getPriority));
            }

            case "Severity" -> {
                return sort(bugList, Comparator.comparing(Bug::getSeverity));
            }
            case "Assignee" -> {
                return sort(bugList, Comparator.comparing(Bug::getAssignee));
            }

            default -> throw new IllegalArgumentException("Unable to filter this way.");
        }
    }

    private String sortStory(String sortBy, Team teamOfLoggedInMember) {
        List<Story> storyList = new ArrayList<>();
        for (Board b : teamOfLoggedInMember.getBoards()) {
            storyList.addAll(b.getStories());
        }

        switch (sortBy) {

            case "Title" -> {
                return sort(storyList, Comparator.comparing(Story::getTitle));
            }
            case "Status" -> {
                return sort(storyList, Comparator.comparing(Story::getStatusStory));
            }
            case "Priority" -> {
                return sort(storyList, Comparator.comparing(Story::getPriority));
            }
            case "Size" -> {
                return sort(storyList, Comparator.comparing(Story::getSize));
            }
            case "Assignee" -> {
                return sort(storyList, Comparator.comparing(Story::getAssignee));
            }
            default -> throw new IllegalArgumentException("Unable to filter this way.");
        }
    }

    private String sortFeedback(String sortBy, Team teamOfLoggedInMember) {

        List<Feedback> feedbackList = new ArrayList<>();
        for (Board b : teamOfLoggedInMember.getBoards()) {
            feedbackList.addAll(b.getFeedbacks());
        }
        switch (sortBy) {

            case "Title" -> {
                return sort(feedbackList, Comparator.comparing(Feedback::getTitle));
            }
            case "Status" -> {
                return sort(feedbackList, Comparator.comparing(Feedback::getStatusFeedback));
            }
            case "Rating" -> {
                return sort(feedbackList, Comparator.comparing(Feedback::getRating));
            }
            default -> throw new IllegalArgumentException("Unable to filter this way.");
        }
    }

    private String sortTasksWithAssignee(String sortBy, Team teamOfLoggedInMember) {
        if (!sortBy.equals("Title")) {
            throw new IllegalArgumentException("Unable to filter this way.");
        }

        List<Story> storyList = new ArrayList<>();
        List<Bug> bugList = new ArrayList<>();

        for (Board b : teamOfLoggedInMember.getBoards()) {
            storyList.addAll(b.getStories());
            bugList.addAll(b.getBugs());
        }

        List<Task> combinedTasks = new ArrayList<>();
        combinedTasks.addAll(storyList);
        combinedTasks.addAll(bugList);

        Collections.sort(combinedTasks, Comparator.comparing(Task::getTitle));

        StringBuilder sb = new StringBuilder();

        for (Task task : combinedTasks) {
            sb.append(task.toString());
            sb.append(System.lineSeparator());
            sb.append("--------------------");
            sb.append(System.lineSeparator());
        }
        if (sb.isEmpty()) {
            return EMPTY_ERR_MESSAGE;
        } else {
            return sb.toString().trim();
        }
    }

}
