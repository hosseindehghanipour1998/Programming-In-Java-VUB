import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BankTest {
    private int ID = 0;

    private Bank bank;
    private final int NUMBER_OF_CLIENTS = 40;
    private List<Client> clients;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }
    @Before
    public void generateClients(){
        this.clients = new ArrayList<>();
        for (int i = 0; i < this.NUMBER_OF_CLIENTS; i++) {
            String name = "TestName "+i;
            clients.add(new Client(name, i, ID++, "Test street "+i));
        }
    }

    @Before
    public void setupBank(){
        this.bank = new Bank();
        generateClients();
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            Client newClient = this.clients.get(i);
            this.bank.addNewClient(newClient, i);
        }
    }

    @Test
    public void testLogInLogOut() throws IOException {
        System.out.println(bank.getClients());
        for(Client client: bank.getClients()){
            bank.logIn(client.getBankID(), client.getBankID());
            bank.displayAccounts();
            String accounts = output.toString();
            Assert.assertEquals("Login incorrect", false, accounts.isEmpty());
            bank.logOut();
            output.reset();
            bank.displayAccounts();
            accounts = output.toString();
            Assert.assertEquals("Logout incorrect", true, accounts.isEmpty());
            output.reset();
        }
        Client frank = new Client("Frank", 24, 1111, "Frankstreet 99");
        bank.addAccount(frank, 9999);
        bank.logIn(1111, 1000);
        String expected = "This user is not know to the bank, please check if you gave the correct ID and PIN!";
        Assert.assertEquals("Incorrect login with a correct ID but a wrong PIN", expected, output.toString().replace("\n", ""));
        bank.logOut();
        output.reset();
        bank.logIn(9999, 9999);
        Assert.assertEquals("Incorrect login with a correct ID but a wrong PIN", expected, output.toString().replace("\n", ""));
        bank.logOut();
        output.reset();
    }

    @Test
    public void testAdditionAndRemovalAccounts(){
        for(Client client: bank.getClients()){
            bank.addAccount(client, 2750);
            bank.addAccount(client, 12000);
            bank.addAccount(client, 24000);
            bank.logIn(client.getBankID(), client.getBankID());
            bank.addAccount(client, 2541);
            int numberOfAccounts = client.getAccounts().size();
            Assert.assertEquals("Incorrect number of accounts", 4, numberOfAccounts);
            double sumOfAccountsBefore = sumOfAccounts(client);
            bank.removeAccount(client.getAccounts().get(3), null);
            double sumOfAccountsAfter = sumOfAccounts(client);
            Assert.assertEquals("The account was removed with no transfer of the balance. " +
                    "The sum of the balance of all the accounts before removal should be bigger than after removal"
            , true, sumOfAccountsAfter<sumOfAccountsBefore);
            sumOfAccountsBefore = sumOfAccounts(client);
            bank.removeAccount(client.getAccounts().get(2), client.getAccounts().get(1));
            sumOfAccountsAfter = sumOfAccounts(client);
            Assert.assertEquals("The account was removed with a transfer of the balance. " +
                            "The sum of the balance of all the accounts before removal should be the same as after removal"
                    , true, sumOfAccountsAfter==sumOfAccountsBefore);
            int numberOfAccountsBefore = client.getAccounts().size();
            bank.addAccount(client, -2410);
            int numberOfAccountsAfter = client.getAccounts().size();
            Assert.assertEquals("An account was added with a negative starting amount, this should not be possible!", true, numberOfAccountsBefore == numberOfAccountsAfter);
        }
        Client frank = new Client("Frank", 24, 11100011, "Frank street 1");
        bank.addAccount(frank, 2222);
        BankAccount temp = frank.getAccounts().get(0);
        bank.removeAccount(temp, null);
        bank.removeAccount(temp, null);

    }

    @Test
    public void testTransfers(){
        Client frank = new Client("Frank", 24, 1111111, "Frank street 1");
        Client dirk = new Client("Dirk", 24, 125450, "Dirk street 1");
        //bank.addNewClient(Frank, 9999);
        bank.addAccount(frank, 9000);
        bank.addAccount(frank, 12000);
        //bank.addNewClient(Dirk, 8888);
        bank.addAccount(dirk, 8000);
        bank.addAccount(dirk, 24000);
        double totalFrankBefore = sumOfAccounts(frank);
        double totalDirkBefore = sumOfAccounts(dirk);
        bank.transfer(frank.getAccounts().get(0), dirk.getAccounts().get(0), 4000);
        double totalFrankAfter = sumOfAccounts(frank);
        double totalDirkAfter = sumOfAccounts(dirk);
        Assert.assertEquals("It is not possible to do a transfer if one or more of the clients are not member at the bank.", true, totalFrankBefore==totalFrankAfter && totalDirkBefore==totalDirkAfter);

        bank.addNewClient(frank, 9999);
        bank.addNewClient(dirk, 8888);
        totalFrankBefore = sumOfAccounts(frank);
        totalDirkBefore = sumOfAccounts(dirk);
        bank.transfer(frank.getAccounts().get(0), dirk.getAccounts().get(0), 4000);
        totalFrankAfter = sumOfAccounts(frank);
        totalDirkAfter = sumOfAccounts(dirk);
        Assert.assertEquals("A transfer of two clients registered to the bank has failed. The amount that needed to be transferred was less than the minimum of all the accounts.", true, totalFrankBefore!=totalFrankAfter && totalDirkBefore!=totalDirkAfter);

        totalFrankBefore = sumOfAccounts(frank);
        totalDirkBefore = sumOfAccounts(dirk);
        bank.transfer(frank.getAccounts().get(0), dirk.getAccounts().get(0), 120000);
        totalFrankAfter = sumOfAccounts(frank);
        totalDirkAfter = sumOfAccounts(dirk);
        Assert.assertEquals("A transfer of two clients registered to the bank has gone trough. The amount that needed to be transferred was more than the maximum of all the accounts.", true, totalFrankBefore==totalFrankAfter && totalDirkBefore==totalDirkAfter);
    }
    private double sumOfAccounts(Client client) {
        double balance = 0;
        for(BankAccount acc: client.getAccounts()){
            balance += acc.getBalance();
        }
        return balance;
    }
}
