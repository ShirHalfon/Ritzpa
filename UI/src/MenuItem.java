import javax.swing.*;

public class MenuItem {

    public String tabName;
    public ICommand command;
    public IInputObject objectForCommand;

    public MenuItem(String tabName, ICommand command, IInputObject objectForCommand) {
        this.tabName = tabName;
        this.command = command;
        this.objectForCommand = objectForCommand;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public IInputObject getObjectForCommand() {
        return objectForCommand;
    }

    public void setObjectForCommand(IInputObject objectForCommand) {
        this.objectForCommand = objectForCommand;
    }

    public void selected() throws Exception {
        if(command != null){
            command.execute(objectForCommand);
        }
    }
}
