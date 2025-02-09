package duke.commands;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

//CHECKSTYLE.OFF: CustomImportOrder
import java.time.LocalDateTime;
//CHECKSTYLE.ON: CustomImportOrder
/**
 * The {@code AddCommand} class represents a command to add tasks to a task list.
 * It can handle different types of tasks, including ToDo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {

    // Fields
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;
    private String type;

    /**
     * Constructs an {@code AddCommand} object for adding a ToDo task.
     *
     * @param description The description of the ToDo task.
     */
    public AddCommand(String description) {
        assert description != null : "Description cannot be null";
        this.description = description;
        this.type = "todo";
    }

    /**
     * Constructs an {@code AddCommand} object for adding a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline of the task (date and time).
     */
    public AddCommand(String description, LocalDateTime by) {
        assert description != null : "Description cannot be null";
        assert by != null : "Deadline cannot be null";
        this.description = description;
        this.type = "deadline";
        this.to = by;
    }

    /**
     * Constructs an {@code AddCommand} object for adding an Event task.
     *
     * @param description The description of the Event task.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public AddCommand(String description, LocalDateTime from, LocalDateTime to) {
        assert description != null : "Description cannot be null";
        assert from != null : "Start date and time cannot be null";
        assert to != null : "End date and time cannot be null";
        this.description = description;
        this.type = "event";
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the AddCommand by creating the specified task and adding it to the task list.
     *
     * @param tasks   The task list to which the task should be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data.
     */
    @Override
    public String runCommand(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
        switch (this.type) {
        case "todo":
            task = new ToDo(this.description);
            tasks.add(task);
            storage.updateFile(tasks);
            return ui.todoMessage(task);
        case "deadline":
            task = new Deadline(this.description, this.to);
            tasks.add(task);
            storage.updateFile(tasks);
            return ui.deadlineMessage(task);
        case "event":
            task = new Event(this.description, this.from, this.to);
            tasks.add(task);
            storage.updateFile(tasks);
            return ui.eventMessage(task);
        default:
            return ui.showErrorMessage("idk what u saying.");
        }
    }
}
