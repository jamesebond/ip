package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to search for tasks containing a specified keyword.
 * When executed, it searches through the list of tasks and displays tasks whose descriptions
 * contain the specified keyword (case-insensitive).
 */
public class FindCommand extends Command {

    /**
     * The keyword to search for in task descriptions.
     */
    private String keyword;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "keyword cannot be null";
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by searching for tasks in the provided TaskList whose descriptions
     * contain the specified keyword. The search is case-insensitive.
     * The results are displayed using the provided Ui and may be stored using the provided Storage
     * (if applicable).
     *
     * @param taskList The TaskList containing the tasks to search through.
     * @param ui       The Ui used to display search results.
     * @param storage  The Storage used to potentially store the search results.
     */
    @Override
    public String runCommand(TaskList taskList, Ui ui, Storage storage) {
        // Create a new TaskList to store search results
        TaskList results = new TaskList();

        // Iterate through the task list and add tasks containing the keyword to the results
        for (Task task : taskList.getToDos()) {
            boolean isMatch = task.getTaskDescription().toLowerCase().contains(keyword.toLowerCase());
            if (isMatch) {
                results.add(task);
            }
        }
        if (results.isEmpty()) {
            return ui.showErrorMessage("Cannot find any matches with your keyword.");
        }
        else {
            // Display the search results using the provided Ui
            return ui.showSearchResults(results);
        }
    }
}

