import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Bank_Account {
    /**
     *holds the bank account number
     */
    private int acc_no;
    /**
     * holds the account holder name
     */
    private String holder_name;
    /**
     * holds the account balance
     */
    private double balance;

    /**
     * creates Bank_Account objects and sets member attributes
     * @param no account number
     * @param name account holder name
     * @param bal account balance
     */
    Bank_Account(int no, String name, double bal){
        this.acc_no=no;
        this.holder_name=name;
        this.balance=bal;
    }

    /**
     * creating a text file for the new account
     */
    public void create_account(){
        try(FileWriter file= new FileWriter("Accounts/"+String.valueOf(acc_no)+".txt")){
            file.write("______________________________________________________\n");
            file.write("Account number: "+String.valueOf(acc_no)+"\n"+"Holder name: "+holder_name+"\n");
            file.write("______________________________________________________\n");
            file.write("Task and amount\t\tBalance\n");
            file.write("______________________________________________________\n");
            file.append("\nDeposit   "+String.valueOf(balance)+"\t\t"+String.valueOf(balance));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * deletes an account data and edits its text file
     */
    public void delete_account(){
        this.withdraw_money(balance);
        this.balance=0;
        try(FileWriter file= new FileWriter("Accounts/"+String.valueOf(acc_no)+".txt",true)){
            file.append("\n\n______________________________________________________\nAccount closed");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * edits the balance of the account and updates text file
     * @param amount withdrawing amount
     */
    public void withdraw_money(double amount){
        this.balance-=amount;
        try(FileWriter file= new FileWriter("Accounts/"+String.valueOf(acc_no)+".txt",true)){
            file.append("\nWithdraw  "+String.valueOf(amount)+"\t\t"+String.valueOf(balance));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * edits the balance of the account and updates text file
     * @param amount depositing amount
     */
    public void deposit_money(double amount){
        this.balance+=amount;
        try(FileWriter file= new FileWriter("Accounts/"+String.valueOf(acc_no)+".txt",true)){
            file.append("\nDeposit   "+String.valueOf(amount)+"\t\t"+String.valueOf(balance));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * returns the account number
     * @return the account number
     */
    public int get_acc_no(){
        return this.acc_no;
    }

    /**
     * returns the account balance
     * @return the account balance
     */
    public double get_balance(){
        return this.balance;
    }

    /**
     * edits the balance of the account and updates text file
     * @param amount transferring amount
     */
    public void transfer_money(double amount){
        this.balance-=amount;
        try(FileWriter file= new FileWriter("Accounts/"+String.valueOf(acc_no)+".txt",true)){
            file.append("\nTransfer  "+String.valueOf(amount)+"\t\t"+String.valueOf(balance));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * edits the balance of the account and updates text file
     * @param amount receiving amount
     */
    public void receive_money(double amount){
        this.balance+=amount;
        try(FileWriter file= new FileWriter("Accounts/"+String.valueOf(acc_no)+".txt",true)){
            file.append("\nReceive   "+String.valueOf(amount)+"\t\t"+String.valueOf(balance));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * returns a string of account details
     * @return a space separated account details string
     */
    public String get_account_details(){
        return (String.valueOf(acc_no)+" "+holder_name+" "+String.valueOf(balance));
    }
}
