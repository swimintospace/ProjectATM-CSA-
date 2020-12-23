/**
 * The Customer class brings the customer into existence and creates a personalPIN which will act as authentication in order for the client to have access to the ATM.
 */
public class Customer 
{
    //instance variables
    private String holderName;
    private String personalPIN;
    private String status;

    /**
     * Creates an instance of Customer given a name and a personal PIN.
     * @param name The name of the account holder
     * @param pin The personal PIN of the account holder; used for authentication.
     */
    public Customer (String name, String pin) 
    {
        this.holderName = name;
        this.personalPIN = pin;
    }   

    /**
     * A getter method that gives a printable String of the account holder's name.
     * @return name of the holder as a printable string.
     */
    public String getHolderName()
    {
        return holderName;
    }

    /**
     * A getter method to gives the personal PIN of the account holder as a printable String.
     * @return personal PIN as a printable string.
     */
    public String getPIN()
    {
        return personalPIN;
    }

    /**
     * A setter method that can mutate the instance variable personalPIN
     * @param newPIN the new PIN that will be the value of personalPIN
     */
    public void updatePIN(String newPIN)
    {
        this.personalPIN = newPIN;
        status = "success";
    }

    /**
     * A boolean that is used to check if the given String toCheckPin to check if both PINs match.
     * @param toCheckPIN the String that is to be checked to see if it is equal to personalPIN
     * @return true if toCheckPIN and personalPIN are equal.
     */
    public boolean checkPIN(String toCheckPIN)
    {
        return toCheckPIN.equals(personalPIN);
    }
}
