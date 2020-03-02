package duke.parser;

import java.util.Scanner;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.Exception.DukeException;

/**
 * Represents a Scanner object that is created from the user's input/response with
 * the program. This class handles the user response and tells the program what commands to execute.
 * A parser object corresponds to user's response e.g, 'todo return book'
 */
public class Parser {

    public static Scanner userInput;
    public static Ui ui;
    public static TaskList taskList;

    /**
     * Constructor for the Parser class
     * @param taskList a pointer/reference to the tasklist
     */
    public Parser(TaskList taskList){
        userInput = new Scanner(System.in);
        this.ui = new Ui(taskList);
        this.taskList = taskList;
    }


    /**
     * Returns a trimmed String representation of the response from the user
     * @return trimmed String user response.
     */
    public String getUserResponse(){
        String userResponse = userInput.nextLine();
        return userResponse.trim();
    }

    /**
     * Function that parses through the user response and tells the program which
     * command it should execute.
     * @param userResponse trimmed String of user response
     */
    public void doUserCommand(String userResponse){
        String action = userResponse.split(" ")[0];
        String restOfUserInput = userResponse.replace(action, "").trim();
        ui.printLineSeparator();
        try {
            if(action.equals("bye")){
                ui.goodbye();
            } else if(action.equals("list")){
                taskList.listTasks();
            } else if(action.equals("done")){
                taskList.done(restOfUserInput);
            } else if(action.equals("todo")){
                taskList.todo(restOfUserInput);
            } else if(action.equals("deadline")){
                taskList.deadline(restOfUserInput);
            } else if(action.equals("event")){
                taskList.event(restOfUserInput);
            } else if(action.equals("delete")){
                taskList.delete(restOfUserInput);
            } else if(action.equals("find")){
                taskList.find(restOfUserInput);
            } else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            ui.invalidCommand();
        }
        ui.printLineSeparator();
    }

}

