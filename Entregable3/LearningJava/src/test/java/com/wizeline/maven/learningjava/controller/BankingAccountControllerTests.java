package com.wizeline.maven.learningjava.controller;

import com.wizeline.maven.learningjava.enums.AccountType;
import com.wizeline.maven.learningjava.model.BankAccountDTO;
import com.wizeline.maven.learningjava.model.Post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class BankingAccountControllerTests {

    private static final Logger LOGGER = Logger.getLogger(BankingAccountControllerTests.class.getName());

    int data = 1;
    private int codigoServ = 0;
    private static final String SUCCESS_CODE = "OK000";
    Long datoInicial = (long) 1;

    private String fecha = null ;
    private String password = null;
    private String user = null;

    @Autowired
    BankingAccountController bankingAccountController;

    @Mock
    private BankAccountDTO bankAccountDTO;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private ResponseEntity<List<BankAccountDTO>> listResponseEntity;

    @Mock
    private ResponseEntity<String> lisResponseEntityDelete;
    
    @Mock
    private ResponseEntity<Map<String, List<BankAccountDTO>>> responseByType;
    
    @Mock
    private ResponseEntity<Post> externalUserResponse;
    

    @BeforeEach
    void antesPrueba() {
        data = 0;
        LOGGER.info("Antes de cada prueba " + data);
        if (user == null & password == null & fecha == null) {
            user = "user2@wizeline.com";
            password = "pAss1#";
            fecha = "07-10-2022";
            bankAccountDTO.setUserName("kevin.castillo@gmail.com");
            bankAccountDTO.setAccountType(AccountType.NOMINA);
            bankAccountDTO.setAccountActive(true);
            bankAccountDTO.setAccountBalance(7000.6223565952);
            bankAccountDTO.setAccountName("Dumy account Kevin");
            bankAccountDTO.setAccountNumber(12312124);
        }
    }

    @Test
    @DisplayName("Prueba servicio Get /getUserAccount")
    public void pruebaUserAccount() {
        LOGGER.info("Inicia prueba de Servicio rest /getUserAccount: " + responseEntity.getStatusCode());
        responseEntity = bankingAccountController.getUserAccount(user, password, fecha);
        LOGGER.info("Respuesta servicio: " + responseEntity.getStatusCode());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Prueba servicio Get /getUserAccount - ERROR")
    public void pruebaUserAccountError() {
        fecha = "2022/07/10";
        LOGGER.info("Se iniciando la prueba servicios Rest /createUserError");
        responseEntity = bankingAccountController.getUserAccount(user, password, fecha);
        String codigoResp = responseEntity.getBody().toString();

        LOGGER.info("Respuesta del servicio: " + responseEntity.getStatusCodeValue());
        LOGGER.info("Respuesta del servicio Cod: " + codigoResp);
        assertEquals("Formato de Fecha Incorrecto", codigoResp, "Error al introducir la fecha");

    }

    @Test
    @DisplayName("Prueba servicio Get /getAccounts")
    public void pruebaGetAccounts(){
        LOGGER.info("LearningJava - iniciando prueba getAccounts");
        listResponseEntity = bankingAccountController.getAccounts();
        LOGGER.info("Se obtiene el codigo: " + listResponseEntity.getStatusCodeValue());
        assertEquals(200, listResponseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio Get /getAccountByUser")
    public void pruebaGetAccountByUser(){
        LOGGER.info("LearningJava - iniciando prueba getAccountByUser");
        listResponseEntity = bankingAccountController.getAccountByUser(user);
        LOGGER.info("Se obtiene el codigo: " + listResponseEntity.getStatusCodeValue());
        assertEquals(200, listResponseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio Get /getAccountByUser - ERROR")
    public void pruebaGetAccountByUserError(){
        LOGGER.info("LearningJava - iniciando prueba getAccountByUser - Error");
        user = null;
        listResponseEntity = bankingAccountController.getAccountByUser(user);
        LOGGER.info("Se obtiene el codigo: " + listResponseEntity.getStatusCodeValue());
        assertEquals(200, listResponseEntity.getStatusCodeValue());
    }
    
    @Test
    @DisplayName("Prueba servicio Get /getAccountsByType")
    public void pruebaGetAccountsByType() {
        LOGGER.info("LearningJava - iniciando prueba getAccountsByType");
        responseByType = bankingAccountController.getAccountsGroupByType();
        LOGGER.info("Se obtiene el codigo: " + responseByType.getStatusCodeValue());
        assertEquals(200, responseByType.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo: " + responseByType.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio Delete /deleteAccounts")
    public void pruebaDeleteAccounts(){
        LOGGER.info("LearningJava - iniciando prueba deleteAccounts");
        lisResponseEntityDelete = bankingAccountController.deleteAccounts();
        LOGGER.info("Se obtiene el codigo: " + lisResponseEntityDelete.getStatusCodeValue());
        assertEquals(200, lisResponseEntityDelete.getStatusCodeValue());
    }
    
    @Test
    @DisplayName("Prueba servicio Delete /deleteCollection")
    public void pruebaDeleteCollection(){
        LOGGER.info("LearningJava - iniciando prueba deleteCollection");
        lisResponseEntityDelete = bankingAccountController.eliminaCollections();
        LOGGER.info("Se obtiene el codigo: " + lisResponseEntityDelete.getStatusCodeValue());
        assertEquals(200, lisResponseEntityDelete.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio update /actualizaCuentas/{banco}")
    public void pruebaActualizaCuentas() {
        LOGGER.info("LearningJava - iniciando prueba update /actualizaCuentas/{banco}");
        responseEntity = bankingAccountController.actualizaCuentas("Banco Azteca");
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
    
    @Test
    @DisplayName("Se prueba servicio getExternalUser/{userId}")
    public void getExternalUserTest() {

        externalUserResponse = bankingAccountController.getExternalUser(datoInicial);
        LOGGER.info("Resultado: " + externalUserResponse.getBody().getBody());
        assertEquals("No info in accountBalance since it is an external user",
                externalUserResponse.getBody().getBody());

    }

    /*@Test
    @DisplayName("Prueba servicio POST /insertaCuenta")
    public void pruebaCreaCuenta() {
        LOGGER.info("LearningJava - iniciando prueba servicio /insertaCuenta");
        responseEntity = bankingAccountController.insertCuenta(bankAccountDTO);
        LOGGER.info("Se obtiene el codigo insertaCuenta: " + responseEntity.getStatusCodeValue());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }*/
}
