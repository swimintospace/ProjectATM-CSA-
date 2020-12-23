/** 
 * The ATM class manages all the front end interactions of the client and their bank accounts.
 */
import java.util.Scanner;

public class ATM 
{
    //instance variables 
    private Customer customer;
    private Account account;
    private Scanner scan;
    private int exitCondition = 0;

    //static variables
    private static final String SEPERATORSTAR  = "********************************************************";
    private static int transactionID = 10000;

    /**
     * Constructor for the ATM Class 
     * The first two objects are null because they will be initialized in start().
     */
    public ATM()
    {
        customer = null;
        account = null;
        scan = new Scanner(System.in);
    }
    
    /**
     * Starts the ATM program; the only public method.
     */
    public void start()
    {
        welcomeCustomer();
        mainMenu();
    }

    /**
     * Welcomes the client to the ATM program.
     * Creates a customer object and account object.
     */
    private void welcomeCustomer()
    {
        System.out.println("Welcome to TECH ATM!");
        System.out.println("Please print your name!");
        String name = scan.nextLine();
        System.out.println("To keep your bank accounts secure, we use a pin for security.");
        System.out.println("Enter a 4 digit pin: ");
        String customerPIN = scan.nextLine();
        customer = new Customer (name, customerPIN);
        account = new Account(customer);
    }

    /**
     * Provides the client with choices of actions given that the client has entered the right PIN.
     * The choice is sent to processChoice() for parsing.
     * This method will loop until the client chooses to exit.
     */
    private void mainMenu()
    {
        while (exitCondition != 1)
        {
            System.out.println("To proceed please enter your PIN");
            String pinCHECK = scan.nextLine();
            if(customer.checkPIN(pinCHECK))
            {
                System.out.println(SEPERATORSTAR);
                System.out.println("Good day " + customer.getHolderName() + "!");
                System.out.println("Choose from the options below to perform an action.");
                System.out.println("Enter a number of an action you want to do to proceed.");
                System.out.println("1. Withdraw money");
                System.out.println("2. Deposit money");
                System.out.println("3. Transfer money between accounts");
                System.out.println("4. Get account balances");
                System.out.println("5. Change PIN");
                System.out.println("6. Exit");
                String choice = scan.nextLine();
                processChoice(choice);
            }
            
        }
    }

    /**
    * Takes the choice received from the menu and calls the appropriate method to carry out the instructions.
    * @param choice The action to process.
    */
    private void processChoice(String choice)
    {
        if(choice.equals("1"))
        {
            withdrawalProcess();
        }
        else if(choice.equals("2"))
        {
            depositProcess();
        }
        else if(choice.equals("3"))
        {
            transferProcess();
        }
        else if(choice.equals("4"))
        {
            System.out.println(account);
        }
        else if(choice.equals("5"))
        {
            changePIN();
        }
        else if(choice.equals("6"))
        {
            System.out.println("It was a pleasure to serve you. Have a nice day!");
            exitCondition = 1;
        }
        else 
        {
            System.out.println("Invalid option, try again.");
        }
    }

  
    /**
     * The frontend for the withdrawal process. 
     * The printReciept() method will print a reciept reporting if the transaction was successful or not, along with the transactionType and ID
     */
    private void withdrawalProcess()
    {
        System.out.println(SEPERATORSTAR);
        System.out.println("Press enter to proceed!");
        scan.nextLine();
        System.out.println("From which account would you like to withdraw? Checkings or savings? (c/s)");
        String choice = scan.nextLine();
        if(choice.equals("c"))
        {
            account.withdraw("c");
            if(account.getStatus().equals("success"))
            {
                printReciept("WithdrawCheckings", account.withdrawalAmtInt(), "success");
                System.out.println(account);
            }
            else if(account.getStatus().equals("error"))
            {
                printReciept("WithdrawCheckings", account.withdrawalAmtInt(), "error");
            }
        }
        else if(choice.equals("s"))
        {
            account.withdraw("s");
            if(account.getStatus().equals("success"))
            {
                printReciept("WithdrawSavings", account.withdrawalAmtInt(), "success");
                System.out.println(account);
            }
            else if(account.getStatus().equals("error"))
            {
                printReciept("WithdrawSavings", account.withdrawalAmtInt(), "error");
                System.out.println(account);
            }
        } 
    }

    /**
     * The frontend for the deposit process.
     * The printReciept() method will print a reciept reporting if the transaction was successful or not, along with the transactionType and ID
     */
    private void depositProcess()
    {
        System.out.println(SEPERATORSTAR);
        System.out.println("Press enter to proceed!");
        scan.nextLine();
        System.out.println("Which account do you want to deposit into? Checkings or savings? (c/s)");
        String choice = scan.nextLine();
        System.out.println("Enter the amount you want to deposit: ");
        double depositAmt = scan.nextDouble();
        if(choice.equals("c"))
        {
            account.deposit(depositAmt, "c");
            printReciept("DepositCheckings", depositAmt, "success");
            System.out.println(account);
        }
        else if(choice.equals("s"))
        {
            account.deposit(depositAmt, "s");
            printReciept("DepositSavings", depositAmt, "success");
            System.out.println(account);
        }
    }

    /**
     * The frontend for the transfer process.
     * The printReciept() method will print a reciept reporting if the transaction was successful or not, along with the transactionType and ID
     */
    private void transferProcess()
    {
        System.out.println(SEPERATORSTAR);
        System.out.println("Press enter to proceed!");
        scan.nextLine();
        System.out.println("Would you like to: (1/2) ");
        System.out.println("1. Transfer from savings to checkings?");
        System.out.println("2. Transfer from checkings to savings?");
        String choice = scan.nextLine();
        System.out.println("How much would you like to transfer?");
        double transferAmt = scan.nextDouble();

        if(choice.equals("1"))
        {
            account.transfer("s", transferAmt);
            if(account.getStatus().equals("success"))
            {
                printReciept("TransferfromSavings", transferAmt, "success");
                System.out.println(account);
            }
            else if(account.getStatus().equals("error"))
            {
                printReciept("TransferfromSavings", transferAmt, "error");
                System.out.println(account);
            }
        }
        else if(choice.equals("2"))
        {
            account.transfer("c", transferAmt);
            if(account.getStatus().equals("success"))
            {
                printReciept("TransferfromCheckings", transferAmt, "success");
                System.out.println(account);
            }
            else if(account.getStatus().equals("error"))
            {
                printReciept("TransferfromCheckings", transferAmt, "error");
                System.out.println(account);
            }
        }
    }

    /**
     * The front end for the client to change their personalPIN.
     * The printReciept() method will print a reciept reporting if the action was successful or not, along with the transactionType and ID.
     */
    private void changePIN()
    {
        System.out.println(SEPERATORSTAR);
        scan.nextLine();
        System.out.println("Enter a new 4 digit number to update your pin!");
        String newPIN = scan.nextLine();
        customer.updatePIN(newPIN);
        System.out.println("Your new pin is: " + customer.getPIN());
        printReciept("UpdatedPIN", 0, "success");
        System.out.println(account);
    }

    /**
     * This static method is used to print a report of every transaction that takes places. 
     * @param transactionType The type of transaction/action that took place (deposit, withdrawal, transfer, updating personalPIN)
     * @param amount The amount that is being either taken or added. If this method is being called for updating personalPIN, amount is just shown as 0.
     * @param status The status of the transaction, reports through front end if the transaction was successful or not. 
     */
    private static void printReciept(String transactionType, double amount, String status)
    {
        
        transactionID++;
        System.out.println(SEPERATORSTAR);
        System.out.println("Transaction ID: " + transactionID);
        if(status.equals("success"))
        {
            System.out.println("Transaction Status: Success");
            if(transactionType.equals("WithdrawCheckings"))
            {
                System.out.println("Withdrawed " + amount + " from checkings.");
            }
            else if(transactionType.equals("WithdrawSavings"))
            {
                System.out.println("Withdrawed " + amount + " from savings.");
            }
            else if(transactionType.equals("DepositCheckings"))
            {
                System.out.println("Deposited " + amount + " into checkings.");
            }
            else if(transactionType.equals("DepositSavings"))
            {
                System.out.println("Deposited " + amount + " into savings.");
            }
            else if(transactionType.equals("TransferfromCheckings"))
            {
                System.out.println("Transferred " + amount + " from checkings into savings.");
            }
            else if(transactionType.equals("TransferfromSavings"))
            {
                System.out.println("Transferred " + amount + " from savings into checkings.");
            }
            else if(transactionType.equals("UpdatedPIN"))
            {
                System.out.println("Changed PIN.");
            }
            else
            {
                System.out.println("Action could not be recorded.");
            }
        }
        else if(status.equals("error"))
        {
            System.out.println("Transaction Status: Failed");
            if(transactionType.equals("WithdrawCheckings") || transactionType.equals("WithdrawSavings"))
            {
                System.out.println("Withdrawal failed. You did not have enough to withdraw.");
            }
            else if(transactionType.equals("TransferfromCheckings") || transactionType.equals("TransferfromSavings"))
            {
                System.out.println("Transfer failed. You did not have enough to transfer.");
            }
            else 
            {
                System.out.println("Action could not be recorded.");
            }
        }
        else 
        {
            System.out.println("Transaction Status: Unknown");
        }
       

    }
   

}



