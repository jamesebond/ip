package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
/**
 * The `MarkCommand` class represents a command to mark a specific task as done in the task list.
 * When executed, it marks the specified task as done and displays a message to the user.
 */
public class MarkCommand extends Command {
    /**
     * The task number to be marked as done.
     */
    protected int taskNumber;

    /**
     * Constructs a `MarkCommand` object with the task number to be marked as done.
     *
     * @param taskNumber The task number to identify the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        assert taskNumber > 0 : "Task number must be a positive integer";
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the `MarkCommand` by marking the specified task as done and displaying a message to the user.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface responsible for displaying messages.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public String runCommand(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        storage.updateFile(tasks);
        return ui.markMessage(task);
    }
}
