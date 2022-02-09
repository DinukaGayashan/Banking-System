import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Bank {
    /**
     * ArrayList of all the accounts of the bank
     */
    ArrayList <Bank_Account> accounts = new ArrayList<>();

    /**
     * creates a bank object and reads all the account details and stores in the ArrayList
     */
    Bank(){
        try(BufferedReader file= new BufferedReader(new FileReader("Accounts/Account_Details.txt"))){
            String line= file.readLine();
            while((line=file.readLine())!=null){
                String[] data= line.split(" ");
                Bank_Account acc= new Bank_Account(Integer.parseInt(data[0]),data[1],Double.parseDouble(data[2]));
                accounts.add(acc);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * takes inputs and creates a bank account
     */
    public void create_account(){
        System.out.print("\nCreate account\n\n");
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter account holder name: ");
        String nm= sc.nextLine();
        String name = nm.replace(' ', '_');
        System.out.print("Enter initial amount to deposit: ");
        double amount= sc.nextDouble();
        int last_acc_no=1000000000;
        if(accounts.size()>0){
            last_acc_no=accounts.get(accounts.size()-1).get_acc_no();
        }
        Bank_Account acc= new Bank_Account(last_acc_no+1,name,amount);
        acc.create_account();
        accounts.add(acc);
        sc.close();
        System.out.print("Account successfully created\n");
    }

    /**
     * takes and checks inputs and closes accounts
     */
    public void close_account(){
        System.out.print("\nClose account\n\n");
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter account number: ");
        int acc_no= sc.nextInt();
        sc.close();
        for(int i=0;i<accounts.size();i++){
            if(acc_no==accounts.get(i).get_acc_no()){
                accounts.get(i).delete_account();
                System.out.print("Account successfully closed\n");
                return;
            }
        }
        System.out.print("Invalid account number entered\n");
    }

    /**
     * takes and checks inputs and withdraws money from accounts
     */
    public void withdraw_money(){
        System.out.print("\nWithdraw money\n\n");
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter account number: ");
        int acc_no= sc.nextInt();
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).get_acc_no()==acc_no){
                System.out.print("Enter amount to withdraw: ");
                double amount= sc.nextDouble();
                if(accounts.get(i).get_balance()>amount){
                    accounts.get(i).withdraw_money(amount);
                    sc.close();
                    System.out.print("Withdraw successfully finished\n");
                    return;
                }
                sc.close();
                System.out.print("Invalid money amount entered\n");
                return;
            }
        }
        sc.close();
        System.out.print("Invalid account number entered\n");
    }

    /**
     * takes and checks inputs and deposits money to accounts
     */
    public void deposit_money(){
        System.out.print("\nDeposit money\n\n");
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter account number: ");
        int acc_no= sc.nextInt();
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).get_acc_no()==acc_no){
                System.out.print("Enter amount to deposit: ");
                double amount= sc.nextDouble();
                accounts.get(i).deposit_money(amount);
                sc.close();
                System.out.print("Deposit successfully finished\n");
                return;
            }
        }
        sc.close();
        System.out.print("Invalid account number entered\n");
    }

    /**
     * takes and checks inputs and shows account balance
     */
    public void request_balance(){
        System.out.print("\nView balance\n\n");
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter account number: ");
        int acc_no= sc.nextInt();
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).get_acc_no()==acc_no){
                System.out.print("Account balance: "+String.valueOf(accounts.get(i).get_balance()));
                sc.close();
                return;
            }
        }
        sc.close();
        System.out.print("Invalid account number entered\n");
    }

    /**
     * takes and checks inputs and do transfer and receive of money between accounts
     */
    public void transfer_money(){
        System.out.print("\nTransfer money\n\n");
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter account number to transfer: ");
        int trans= sc.nextInt();
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).get_acc_no()==trans){
                System.out.print("Enter account number to receive: ");
                int rec= sc.nextInt();
                for(int j=0;j<accounts.size();j++){
                    if(accounts.get(j).get_acc_no()==rec){
                        System.out.print("Enter amount to transfer: ");
                        double amount= sc.nextDouble();
                        if(accounts.get(i).get_balance()>amount){
                            accounts.get(i).transfer_money(amount);
                            accounts.get(j).receive_money(amount);
                            sc.close();
                            System.out.print("Transfer successfully finished\n");
                            return;
                        }
                        sc.close();
                        System.out.print("Invalid money amount entered\n");
                        return;
                    }
                }
                sc.close();
                System.out.print("Invalid account number entered\n");
                return;
            }
        }
        sc.close();
        System.out.print("Invalid account number entered\n");
    }

    /**
     * writes all account details to a text file to read
     */
    public void write_account_details(){
        try(FileWriter file= new FileWriter("Accounts/Account_Details.txt")){
            file.write("Account_Number Account_Holder_Name Account_Balance\n");
            for(int i=0;i<accounts.size();i++){
                file.write(accounts.get(i).get_account_details()+"\n");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
