package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.AccountGGDTO;
import m2m_phase2.clothing.clothing.data.entity.AccountGGE;
import m2m_phase2.clothing.clothing.repository.AccountGGRepo;
import m2m_phase2.clothing.clothing.service.AccountGGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AccountGGServiceImpl implements AccountGGService {

    @Autowired
    AccountGGRepo repo;

    @Override
    public AccountGGE saveAccountGG(AccountGGDTO accountGGDTO) throws SQLException {
        if(findByEmailGG(accountGGDTO) != null){
            return null;
        }
        AccountGGE accountGGE = AccountGGDTO.converAccountggdtoToAccountgge(accountGGDTO);
        return repo.save(accountGGE);
    }

    @Override
    public AccountGGE findByEmailGG(AccountGGDTO accountGGDTO) throws SQLException {
        return repo.findByemailGG(accountGGDTO.getEmailGG());
    }
}
