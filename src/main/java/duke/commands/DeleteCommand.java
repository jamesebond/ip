package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand class represents a command to delete a specific task from the task list.
 * It takes the task number as an argument to identify the task to be deleted.
 */
public class DeleteCommand extends Command {

    /**
     * The task number to be deleted.
     */
    private int taskNumber;

    /**
     * Constructs a DeleteCommand object with the task number to be deleted.
     *
     * @param taskNumber The task number to identify the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        assert taskNumber > 0 : "Task number must be a positive integer";
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteCommand by deleting the specified task from the task list.
     * It then displays a message to inform the user about the deletion.
     *
     * @param taskList The task list from which the task is to be deleted.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage component for reading or writing data.
     */
    @Override
    public String runCommand(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.get(taskNumber - 1);
        taskList.delete(taskNumber - 1);
        int len = taskList.totalTaskCount();
        storage.updateFile(taskList);
        return ui.showDeleteMessage(task, len);
    }
}
