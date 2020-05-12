public class Utils {

    public static Account[] sort(Account[] accounts){
        for(int i = 0; i<accounts.length;i++){
            for(int j = 0; j<accounts.length-1;j++){
                if(accounts[j].getBalance() > accounts[j+1].getBalance()){
                    Account tmp = accounts[j];
                    accounts[j] = accounts[j+1];
                    accounts[j+1] = tmp;
                }
            }
        }
        return accounts;
    }
}
