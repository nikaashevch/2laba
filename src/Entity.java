public class Entity implements Client {
    private String title;
    private node head = new node(null, null);
    private int size = 0;


    public Entity(String title) {
        this.title = title;
    }

    public Entity(String title, Account[] accounts) {
        this.title = title;
        for (Account account : accounts) {
            add(account);
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    //В целом это нормально, объект в памяти есть? - есть. К нему токо надо обратиться правильно
    //Но госпади, из-за желания уменьшить количество доп переменных
    //"связывание" выглядит почти как в порно
    @Override
    public boolean add(Account account) {
        if (size == 0) {
            head.next = new node(head, account);
            head.next.next = head;
            head.prev = head.next;
        } else {
            node oldPrev = head.prev;
            head.prev = new node(oldPrev, account);
            head.prev.next = head;
            oldPrev.next = head.prev;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(Account account, int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        if (size == 0 || size < index) return add(account);
        else {
            //Такс, вот тут нюанс, голова как бы есть
            //но вот надо ли её при проходе считать
            //Это большой вопрос, поэтому тут так будет
            //Голова есть но мы про это забудем
            //И след элемент после головы будет нулевым #profit
            node buf = head.next;
            for (int i = 0; i < index - 1; i++) {
                buf = buf.next;
            }
            node prev = buf;
            node next = buf.next;
            prev.next = new node(prev, account);
            prev.next.next = next;//Просто чистое порно
            next.prev = prev.next;
            size++;
            return true;//Госпади, как же я хочу вставить в код картинку, надеюсь когда нибудь такое прикрутят
        }
    }

    @Override
    public Account get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        node buf = head.next;
        for (int i = 0; i < index; i++) {
            buf = buf.next;
        }
        return buf.value;
    }

    @Override
    public Account get(String number) {
        node buf = head.next;
        while (buf.value != null) {
            if (buf.value.checkNumber(number)) return buf.value;
            buf = buf.next;
        }
        return null;
    }

    @Override
    public boolean hasAccountWithNumber(String number) {
        node buf = head.next;
        while (buf.value != null) {
            if (buf.value.checkNumber(number)) return true;
            buf = buf.next;
        }
        return false;
    }

    @Override
    public Account set(Account account, int index) {
        if (index < 0 || size <= index) throw new IndexOutOfBoundsException();
        node buf = head.next;
        for (int i = 0; i < index; i++) {
            buf = buf.next;
        }
        Account tmp = buf.value;
        buf.value = account;
        return tmp;
    }

    @Override
    public Account remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (size == 1) {
            Account buf = head.next.value;
            head = new node(null, null);
            size--;
            return buf;
        } else {
            node buf = head.next;
            for (int i = 0; i < index; i++) {
                buf = buf.next;
            }
            Account tmp = buf.value;
            node prev = buf.prev;
            node next = buf.next;
            prev.next = next;
            next.prev = prev;
            size--;
            return tmp;
        }
    }

    @Override
    public Account remove(String number) {
        node buf = head.next;
        while (buf.value != null) {
            if (buf.value.checkNumber(number)) {
                Account tmp = buf.value;
                if (buf == head) {
                    head = new node(null, null);
                } else {
                    node prev = buf.prev;
                    node next = buf.next;
                    prev.next = next;
                    next.prev = prev;
                }
                size--;
                return tmp;
            }
        }
        return null;
    }

    @Override
    public int indexOf(String number) {
        node buf = head.next;
        int index = 0;
        while (buf.value != null) {
            if (buf.value.checkNumber(number)) return index;
            buf = buf.next;
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Account[] getAccounts() {
        Account[] buf = new Account[size];
        node tmp = head.next;
        for (int i = 0; i < size; i++) {
            buf[i] = tmp.value;
            tmp = tmp.next;
        }
        return buf;
    }

    @Override
    public Account[] getSortedAccountsByBalance() {
        return Utils.sort(getAccounts().clone());
    }

    @Override
    public double getTotalBalance() {
        double totalBalance = 0;
        node buf = head.next;
        while (buf.value != null) {
            totalBalance += buf.value.getBalance();
            buf = buf.next;
        }
        return totalBalance;
    }
}

class node {
    node next;
    node prev;
    Account value;

    node(node prev, Account value) {
        this.prev = prev;
        this.value = value;
    }
}
