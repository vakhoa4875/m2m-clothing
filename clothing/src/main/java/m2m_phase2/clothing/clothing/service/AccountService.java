package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.AccountDto;
import m2m_phase2.clothing.clothing.data.entity.Account;

import java.sql.SQLException;

public interface AccountService {

    Account saveAccount(Account account);

    Account findByusername(String username);

    Account findByemail(String email);


    Account findByuserId(Integer id);

    boolean isDisable(Account account);

//	List<UserM> findAll() throws SQLException;

    Account findByUsernameAndEmail(String username, String email);

    // create new account from admin
    String createAccount(AccountDto accountDto) throws SQLException, NullPointerException;

    public long getTotalAccounts();
    public long getGGAccount();
    public long getDKAccount();
}
