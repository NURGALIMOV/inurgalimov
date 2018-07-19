package ru.inurgalimov.bank;

import java.util.*;

public class BankTransfers {
    private Map<User, List<Account>> transfer = new TreeMap<>();

    public void addUser(User user) {
        transfer.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        transfer.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        transfer.get(searchByPassport(passport)).add(account);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        transfer.get(searchByPassport(passport)).remove(account);
    }

    public List<Account> getUserAccounts(String passport) {
        return transfer.get(searchByPassport(passport));
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        User srcUser = searchByPassport(srcPassport);
        User destUser = searchByPassport(destPassport);
        Account srcAccount = searchByRequisitet(srcUser, srcRequisite);
        Account dstAccount = searchByRequisitet(destUser, dstRequisite);
        if (!srcAccount.equals(null) && srcAccount.getValue() >= amount) {
            result = true;
            srcAccount.setValue(srcAccount.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
        }
        return result;
    }

    public User searchByPassport(String passport) {
        User resultUser = null;
        Set<User> key = transfer.keySet();
        for (User u : key) {
            if (u.getPassport().equals(passport)) {
                resultUser = u;
                break;
            }
        }
        return resultUser;
    }

    public Account searchByRequisitet(User user, String requisitet) {
        Account resultAccount = null;
        for (Account account : transfer.get(user)) {
            if (account.getRequisites().equals(requisitet)) {
                resultAccount = account;
            }
        }
        return resultAccount;
    }
}
