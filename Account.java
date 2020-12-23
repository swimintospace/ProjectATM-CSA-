/**
 * This class is responsible for the backend actions to the client's bank account
 * Handles the withdrawal, depositing, and transfer of funds between checkings and savings
 */
import java.util.Scanner;

public class Account 
{
  //final instance variables
  private final String SUCCESS = "success";
  private final String ERROR = "error";

  //instance variables 
  private Customer customer;
  private double checkings;
  private double savings;
  private int getWithdrawalAmt;
  private String status;

  /**
   * Constructs the bank accounts for the client.
   * @param customer The holder of the bank account.
   */
  public Account(Customer customer)
  {
    this.checkings = 0;
    this.savings = 0;
    this.status = " ";
    this.customer = customer;
  }

  /**
   * This prints the information of the two accounts in the Account class properly
   */
  public String toString()
  {

    String str = "********************************************************";
    str += "\nCheckings Account: $" + checkings;
    str += "\nSavings Account: $" + savings;
    return str;
  }
  
  /**
   * getter method
   * gets the status for each transaction
   * @return "success" if the transaction was successful and "error" if the transaction failed.
   */
  public String getStatus()
  {
    return status;
  }

  /**
   * In order to display the amount that was withdrew from an account, this variable is used.
   * @return A printable int showing how much was withdrawn from the specified account.
   */
  public int withdrawalAmtInt()
  {
    return getWithdrawalAmt;
  }

  /**
   * In order to display the balance of the checkings account, this variable is used.
   * @return A printable int showing the amount left in a checkings account.
   */
  public double getCheckings()
  {
    return checkings;
  }

  /**
  * In order to display the balance of the savings account, this variable is used.
  * @return A printable int showing the amount left in a savings amount.
  */
  public double getSavings()
  {
    return savings; 
  }

  /**
   * A method that deposits the amount the client wishes to deposit. This method is strictly backend and manipulates the account balances.
   * @param amount The amount to be deposited.
   * @param acct The account which the amount will be deposited in.
   */
  public void deposit(double amount, String acct)
  {
    if(acct.equals("c"))
    {
      checkings += amount;
    }
    else 
    {
      savings += amount;
    }
  }

  /**
   * A method that withdraws the amount the client wishes to withdraw. This method is strictly backend and manuiplates the account balances.
   * This method calls the cashWithdrawal() method because the client goes through multiple steps and amount is calculated from the dollar bills that the client choses.
   * If the account has less money than what is specified to be withdrawn, status string will change to error and no action will be done.
   * @param acct The account which the amount will be withdrawn from.
   */
  public void withdraw(String acct)
  {
    int withdrawalAmt = cashWithdrawal();
    if(acct.equals("c"))
    {
      if (withdrawalAmt > checkings)
      {
        status = ERROR;
      }
      else 
      {
        getWithdrawalAmt = withdrawalAmt;
        checkings -= withdrawalAmt;
        status = SUCCESS;
      }
      
    }
    else
    {
      if(withdrawalAmt > savings)
      {
        status = ERROR;
      }
      else 
      {
        getWithdrawalAmt = withdrawalAmt;
        savings -= withdrawalAmt;
        status = SUCCESS;
      }
      
    }

  }

  /**
   * A method which transfers the specified amount of money between accounts. 
   * If the account that the money is being taken from has less funds than what is specified, it will change status String to "error" and no action is done.
   * @param from The account which the money is coming from.
   * @param amount The specified amount the client wishes to transfer.
   */
  public void transfer(String from, double amount)
  {
      if(from.equals("c"))
      {
        if(amount > checkings)
        {
          status = ERROR;
        }
        else 
        {
          checkings -= amount;
          savings += amount;
          status = SUCCESS;
        }
      }
      else
      {
        if(amount > savings)
        {
          status = ERROR;
        }
        else
        {
          savings -= amount;
          checkings += amount;
          status = SUCCESS;
        }
      }
  }
  /**
   * A method which the it will take client input to calculate how much they want to withdraw.
   * @return The amount which the client wants to withdraw.
   */
   public static int cashWithdrawal()
   {
      Scanner scan = new Scanner(System.in);
      System.out.println("How many $20 bills do you want to withdraw?");
      int twenties = scan.nextInt();
      System.out.println("How many $5 bills do you want to withdraw?");
      int fives = scan.nextInt();
      return (20 * twenties) + (5 * fives);
    }
}
