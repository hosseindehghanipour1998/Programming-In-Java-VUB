import java.util.ArrayList;
import java.util.List;

public class Bank {
    private int uniqueIDAccounts = 0;

    private int uniqueIDClient = 0;
    private List<Client> clients;
    private Client currentClient = null;

    public Bank() {
	/***
	Constructor
	***/
    }

    public void addNewClient(Client client, int pin) {
	/***
	Add clients to the bank. What do you do when a client has already been added to the bank?
	***/
    }

    public void logOut() {
    	/***
    	Log the current user out of the bank
    	***/
    }

    public void logIn(int id, int pin) {
        /***
    	Log the current user in to the bank. What happens is someone is already logged in?
    	How do you validate if the user is indeed who they claim to be?
    	If the user is unable to provide the correct information, the following sentence should be displayed: 
    	"This user is not know to the bank, please check if you gave the correct ID and PIN!"
    	***/
    }

    public void addAccount(Client client, double amount) {
        /***
    	Add an account, if all input valid in this method?
    	***/
    }

    public void removeAccount(BankAccount toRemove, BankAccount transferAccount) {
        /***
    	Remove an account, the user can transfer the money in the 'toRemove' account to the 'transferAccount'.
    	***/
    }


    public void transfer(BankAccount transferFrom, BankAccount transferTo, double amount) {
        /***
    	Transfer from 'transferFrom' to 'TransferTo' with a given ammount?
    	Can anyone transfer, what with people that are not part of the bank?
    	***/
    }

    public void displayAccounts() {
        /***
    	Give a display to the user what accounts are associated with them.
    	Give info abouth the index (for easy access), the ID and the amount.
    	***/
    }

    public int maxIDClient(){
        /***
    	Getter
    	***/
    }

    public Client getCurrentClient(){
        /***
    	Getter
    	***/
    }

    public List<Client> getClients() {
        /***
	Getter    	
    	***/
    }

}
