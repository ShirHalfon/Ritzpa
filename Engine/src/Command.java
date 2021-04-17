import java.util.ArrayList;

public class Command {

    public class ReadingANewFileCommand implements ICommand{

        public Client client;

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public ReadingANewFileCommand(Client client) {
            this.client = client;
        }

        @Override
        public void execute(IInputObject inputObject) throws Exception {
            client.ReadingANewFile(inputObject);
        }
    }

    public class ShowAllStocksCommand implements ICommand{
        public Client client;

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public ShowAllStocksCommand(Client client) {
            this.client = client;
        }

        @Override
        public void execute(IInputObject inputObject){
            client.ShowAllStocks(inputObject);
        }
    }

    public class ShowSingleStockCommand implements ICommand{
        public Client client;

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public ShowSingleStockCommand(Client client) {
            this.client = client;
        }

        @Override
        public void execute(IInputObject inputObject){
            client.ShowSingleStock(inputObject);
        }
    }
    public class OrderActionCommand implements ICommand{
        public Client client;

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public OrderActionCommand(Client client) {
            this.client = client;
        }

        @Override
        public void execute(IInputObject inputObject) throws Exception {
            client.OrderAction(inputObject);
        }
    }
    public class ShowOrdersForAllStocksCommand implements ICommand{
        public Client client;

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public ShowOrdersForAllStocksCommand(Client client) {
            this.client = client;
        }

        @Override
        public void execute(IInputObject inputObject){
            client.ShowOrdersForAllStocks(inputObject);
        }
    }
    public class ExitCommand implements ICommand {
        public Client client;

        public ExitCommand(Client client) {
            this.client = client;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        @Override
        public void execute(IInputObject inputObject) throws Exception {
            client.Exit(inputObject);
        }
    }
}
