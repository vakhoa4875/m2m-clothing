package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.AccountGGDTO;
import m2m_phase2.clothing.clothing.data.entity.AccountGGE;

import java.sql.SQLException;

public interface AccountGGService {

    AccountGGE saveAccountGG(AccountGGDTO accountGGDTO) throws SQLException;

    AccountGGE findByEmailGG(AccountGGDTO accountGGDTO) throws  SQLException;
}
