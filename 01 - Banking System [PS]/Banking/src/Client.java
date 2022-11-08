import java.util.ArrayList;
import java.util.List;

public class Client {
    private int age;
    private String name;
    private int IDnumber;
    private String adress;
    private List<BankAccount> accounts;

    private int bankID;

    private int pin;

    public Client(String name, int age, int IDnumber, String adress){
        /***
         Constructor
         ***/
    }

    public void addAccount(BankAccount bankAccount){
        /***
         Add an account to the user
         ***/
    }

    public void removeAccount(BankAccount selectedAccount){
        /***
         Remove an account from the user, think of edge cases. Can you always remove an account?
         ***/
    }

    public int getAge() {
        /***
         Getter
         ***/
    }

    public String getName() {
        /***
         Getter
         ***/
    }

    public List<BankAccount> getAccounts(){
        /***
         Getter
         ***/
    }

    public void addBankID(int bankID) {
        /***
         Setter
         ***/
    }

    public int getBankID(){
        /***
         Getter
         ***/
    }

    public void setPin(int pin){
        /***
         Setter
         ***/
    }

    public int getPin(){
        /***
         Getter
         ***/
    }
}
