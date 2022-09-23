package com.wizeline.BO;

import com.wizeline.DTO.BankAccountDTO;
import com.wizeline.enums.AccountType;
import com.wizeline.enums.Country;
import com.wizeline.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BankAccountBOImpl implements BankAccountBO{

    private Utils utils;

   /* @Override
    public BankAccountDTO getAccountDetails(String user, String lastUsage) {
        return buildBankAccount(user, true, lastUsage);
    }*/

    // Creación de tipo de dato BankAccount
    private BankAccountDTO buildBankAccount(String user, boolean isActive, LocalDateTime lastUsage) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(123L);
        bankAccountDTO.setAccountName("Dummy Account");
        bankAccountDTO.setUser(user);
        bankAccountDTO.setAccountBalance(843.24);
        bankAccountDTO.setAccountType(AccountType.NOMINA);
        bankAccountDTO.setCountry("Mexico");
        bankAccountDTO.setAccountActive(isActive);
        bankAccountDTO.setLastUsage(lastUsage);
        return bankAccountDTO;
    }

    @Override
    public List<BankAccountDTO> getAccounts() {
        List<BankAccountDTO> accountDTOList = new ArrayList<>();
        accountDTOList.add(buildBankAccount("user3@wizeline.com", true, Country.MX, LocalDateTime.now().minusDays(7)));
        accountDTOList.add(buildBankAccount("user1@wizeline.com", false, Country.FR, LocalDateTime.now().minusMonths(2)));
        accountDTOList.add(buildBankAccount("user2@wizeline.com" ,false, Country.US, LocalDateTime.now().minusYears(4)));
        return accountDTOList;
    }
    @Override
    public BankAccountDTO getAccountDetails(String user, String lastUsage) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage =  LocalDate.parse(lastUsage, dateFormatter);
        return buildBankAccount(user, true, Country.MX, usage.atStartOfDay()); // Agregar código de pais
    }

    // Creación de tipo de dato BankAccount con la ayuda de la clase Utils.java
    private BankAccountDTO buildBankAccount(String user, boolean isActive, Country country, LocalDateTime lastUsage) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        // bankAccountDTO.setAccountNumber(randomAcountNumber());
        bankAccountDTO.setAccountNumber(utils.randomAcountNumber());
        bankAccountDTO.setAccountName("Dummy Account ".concat(utils.randomInt()));
        bankAccountDTO.setUser(user);
        bankAccountDTO.setAccountBalance(utils.randomBalance());
        bankAccountDTO.setAccountType(utils.pickRandomAccountType());
        bankAccountDTO.setCountry(utils.getCountry(country));
        bankAccountDTO.getLastUsage(); // <- Se invoca al metodo de acceso get() para obtener la fecha actual
        bankAccountDTO.setCreationDate(lastUsage);
        bankAccountDTO.setAccountActive(isActive);
        return bankAccountDTO;
    }
}
