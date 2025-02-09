package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
/**
 * The `UnmarkCommand` class represents a command to unmark a specific task as done in the task list.
 * When executed, it unmarks the specified task and displays a message to the user.
 */
public class UnmarkCommand extends Command {
    /**
     * The task number to be unmarked.
     */
    protected int taskNumber;

    /**
     * Constructs an `UnmarkCommand` object with the task number to be unmarked.
     *
     * @param taskNumber The task number to identify the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        assert taskNumber > 0 : "Task number must be a positive integer";
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the `UnmarkCommand` by unmarking the specified task and displaying a message to the user.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface responsible for displaying messages.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public String runCommand(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber - 1);
        task.unMark();
        storage.updateFile(tasks);
        return ui.unmarkMessage(task);
    }
}

