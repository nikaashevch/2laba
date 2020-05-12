public class Individual implements Client{
    private String title;
    private Account[] accounts;
    private int size;

    public static final int SIXTEEN = 16;
    public static final int ZERO = 0;

    public Individual(String title) {
        this.title = title;
        this.accounts = new Account[SIXTEEN];
        this.size = ZERO;
    }

    public Individual(int size, String title) {
        this.title = title;
        this.accounts = new Account[size];
        this.size = ZERO;
    }

    public Individual(String title,Account[] accounts) {
        this.title = title;
        this.accounts = accounts;
        this.size = accounts.length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean add(Account account){
        if(size==accounts.length){
            extendArray();
            return false;
        }else{
            hideAdd(account);
            return true;
        }
    }

    private void hideAdd(Account account){
        for(int i = 0; i<accounts.length;i++){
            if(accounts[i]==null){
                accounts[i] = account;
                size++;
                return;
            }
        }
    }

    private void extendArray(){
        Account[] buf = new Account[accounts.length*2];
        System.arraycopy(accounts,0,buf,0,accounts.length);
        accounts = buf;
    }

    public boolean add(Account account,int index){
        if(accounts[index]==null){
            accounts[index] = account;
            return true;
        }else{
            return false;
        }
    }

    public Account get(int index){
        return accounts[index];
    }

    public Account get(String number){
        for(int i = 0; i<size;i++){
            if(accounts[i].checkNumber(number)) return accounts[i];
        }
        return null;
    }

    public boolean hasAccountWithNumber(String number){
        for(int i = 0; i<size;i++){
            if(accounts[i].checkNumber(number)) return true;
        }
        return false;
    }

    public Account set(Account account,int index){
        Account buf = accounts[index];
        accounts[index] = account;
        return buf;
    }

    public Account remove(int index){
        Account buf = accounts[index];
        accounts[index] = null;
        for(int i = index;i<accounts.length-1;i++){
            Account tmp = accounts[i];
            accounts[i] = accounts[i+1];
            accounts[i+1] = tmp;
        }
        accounts[accounts.length] = null;
        size--;
        return buf;
    }

    public Account remove(String number){
        return remove(indexOf(number));
    }

    public int indexOf(String number){
        for(int i = 0; i<size;i++){
            if(accounts[i].getNumber().equals(number)) return i;
        }
        return -1;
    }

    //Возвращает общее число счетов
    public int size(){
        return size;
    }

    //С учётом того что после каждого удаления массив сдвигается, нулов быть не должно
    public Account[] getAccounts(){
        Account[] buf = new Account[size];
        System.arraycopy(accounts,0,buf,0,size);
        return buf;
    }

    public Account[] getSortedAccountsByBalance(){
        return Utils.sort(accounts.clone());
    }

    public double getTotalBalance(){
        double totalBalance = 0;
        for(int i = 0; i<size;i++){
            totalBalance+=accounts[i].getBalance();
        }
        return totalBalance;
    }
}