import java.util.Scanner;

/**
 * Main function to drive the code
 */
public class Main {
    public static void main(String[] args){

        boolean run=true;
        while(run){
            Bank bank= new Bank();

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("Banking system\n");
            System.out.print("--------------\n\n");
            System.out.print("1.Create an account\n2.Close an account\n3.Withdraw money\n4.Deposit money\n5.Request balance\n6.Transfer money\n7.Exit\n");
            System.out.print("Choose an option:");

            Scanner sc= new Scanner(System.in);
            int input=sc.nextInt();

            if(input==1){
                bank.create_account();
            }
            else if(input==2){
                bank.close_account();
            }
            else if(input==3){
                bank.withdraw_money();
            }
            else if(input==4){
                bank.deposit_money();
            }
            else if(input==5){
                bank.request_balance();
            }
            else if(input==6){
                bank.transfer_money();
            }
            else if (input==7){
                run=false;
            }
            else{
                System.out.print("Invalid input");
            }

            System.out.println("\nPress 'Enter' to continue...");
            sc.nextLine();
            System.out.print("\n\n");
            bank.write_account_details();
        }
    }
}
