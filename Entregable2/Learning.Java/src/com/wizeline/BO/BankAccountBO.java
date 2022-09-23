package com.wizeline.BO;

import com.wizeline.DTO.BankAccountDTO;

import java.time.LocalDate;
import java.util.List;

public interface BankAccountBO {
    BankAccountDTO getAccountDetails(String user, String lastUsage);
    List<BankAccountDTO> getAccounts();
}
