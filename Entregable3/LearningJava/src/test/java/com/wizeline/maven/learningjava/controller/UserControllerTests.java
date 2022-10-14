package com.wizeline.maven.learningjava.controller;

import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserControllerTests {

    private static final Logger LOGGER = Logger.getLogger(UserControllerTests.class.getName());

    int data = 1;
    private int respServicio = 0;
    private String password = null;
    private String user = null;
    private List<UserDTO> listDto = new ArrayList<>();
    
    @Mock
    ResponseEntity<ResponseDTO> response;
    @Mock
    ResponseEntity<List<ResponseDTO>>  responseList;
    @Mock
    private UserDTO userDTO;

    @Autowired
    private UserController userController;

    //@Mock
    // private List<UserDTO> listDto = new ArrayList<>();
 
    @BeforeEach
    void antesPrueba() {
        data = 0;
        LOGGER.info("Inicaiar variables antes de cada Prueba");

        if (user == null & password == null) {
            user = "user1@wizeline.com";
            password = "pass1";
        }
    }

    @Test
    @DisplayName("Se prueba el happy path")
    public void procesandoCaminoFeliz() {

        LOGGER.info("Se inica el servicio del Camino Feliz de login");
        response = userController.loginUser(user, password);
        respServicio = response.getStatusCodeValue();

        LOGGER.info("Respuesta del servicio Camino Feliz: " + response.getStatusCodeValue());
        assertEquals(respServicio, 200, "Prueba Exitosa");
    }

    @Test
    @DisplayName("Prueba Unitaria /loginUser")
    public void pruebaLogin() {
        LOGGER.info("Se inicia la prueba para el servicio /loginUser");
        response = userController.loginUser(user, password);
        respServicio = response.getStatusCodeValue();

        LOGGER.info("Respuesta del servicio /login: " + response.getStatusCodeValue());
        assertEquals(respServicio, 200, "Prueba Exitosa ");
    }

    @Test
    @DisplayName("Prueba Unitaria /loginUser - Edge Case")
    public void pruebaLoginEdgeCase() {
        password = null;
        user = null;
        LOGGER.info("Se inicia la prueba del servicio Rest /loginUser Edge Case");
        response = userController.loginUser(user, password);
        String codigoResp = response.getBody().getStatus();

        LOGGER.info("Respuesta del servicio /loginUser : " + response.getStatusCodeValue());
        assertEquals("fail", codigoResp, "Prueba Exitosa Edge Case - El User o Password no coinciden");

    }

    @Test
    @DisplayName("Prueba Unitaria /createUser")
    public void pruebaCrearUsuario() {
        LOGGER.info("Se inicia la prueba del servicio Rest /createUser");
        userDTO.setUser(user);
        userDTO.setPassword(password);

        response = userController.createUserAccount(userDTO);

        LOGGER.info("Respuesta del servicio /createUser: " + response.getBody().getCode());
        assertEquals("OK000", response.getBody().getCode(), "Prueba Exitosa ");
    }

    @Test
    @DisplayName("Prueba Unitaria /createUser - Edge Case")
    public void pruebaCrearUsuarioEdgeCase() {
        password = null;
        user = null;
        LOGGER.info("Se inicia la prueba servicios Rest /createUser - Edge Case");
        response = userController.createUserAccount(userDTO);
        String codigoResp = response.getBody().getStatus();

        LOGGER.info("Respuesta del servicio /createUser: " + response.getStatusCodeValue());
        assertEquals("fail", codigoResp, "Prueba Exitosa Edge Case - No se pudo crear el usuario");

    }

    @Test
    @DisplayName("Prueba Unitaria DELETE /usuario")
    public void eliminaUsuario() throws Exception {
        LOGGER.info("Se inicia la prueba del servicio DELETE /usuario");
        userDTO.setUser(user);
        userDTO.setPassword(password);
        response = userController.deleteUser(userDTO);
        LOGGER.info("Respuesta del servicio DELETE /usuario: " + response.getStatusCodeValue());
        assertEquals(200, response.getStatusCodeValue(), "Prueba Exitosa - DELETE /usuario");
    }

    @Test
    @DisplayName("Prueba Unitaria DELETE /usuario - Edge Case")
    public void pruebaEliminaUsuarioEdgeCase() {
        password = null;
        user = null;
        LOGGER.info("Se inicia la prueba - Edge Case del servicios DELETE /usuario");
        response = userController.deleteUser(userDTO);
        String respuesta = response.getBody().getStatus();
        LOGGER.info("Respuesta del servicio DELETE /usuario: " + response.getStatusCodeValue());
        assertEquals("fail", respuesta, "Prueba Exitosa Edge Case - El user y pass no deben de ir nulos");
    }

    @BeforeEach
    void usuarios() {
        LOGGER.info("Llenado lista Dto");
        userDTO.setUser(user);
        userDTO.setPassword(password);
        listDto.add(userDTO);
        userDTO.setUser("andrea.martinez@gmail.com");
        userDTO.setPassword("@ndy21");
        listDto.add(userDTO);
        LOGGER.info("Tamaño Lista " + listDto.size());
    }

    @Test
    @DisplayName("Prueba Unitaria PUT /usuario")
    public void pruebaPutUsuario() throws Exception {

        LOGGER.info("Se inicia la prueba del servicio PUT /usuario ");
        response = userController.updateUser(listDto);
        LOGGER.info("Respuesta del servicio PUT /usuario: " + response.getStatusCodeValue());
        assertEquals(200, response.getStatusCodeValue(), "Prueba Exitosa");
    }

    @Test
    @DisplayName("Prueba Unitaria PUT /usuario - Edge Case")
    public void preubaPutUsuarioEdcase() {
        List<UserDTO> listDto = new ArrayList<>();
        userDTO.setUser(user);
        userDTO.setPassword(password);
        listDto.add(userDTO);
        userDTO.setUser(null);
        userDTO.setPassword(null);
        listDto.add(userDTO);
        LOGGER.info("Se inicia la prueba del servicios Put /usuario - Edge Case");
        response = userController.updateUser(listDto);
        String codigoResp = response.getBody().getStatus();
        LOGGER.info("Respuesta del servicio /usuario: " + response.getStatusCodeValue());
        assertEquals("fail", codigoResp, "Prueba Exitosa");
    }

    @Test
    @DisplayName("Prueba Unitaria /createUsersAccount")
    public void pruebaCrearUsuarios() {
        LOGGER.info("Se inicia la prueba del servicio Rest /createUsers");
        List<UserDTO> listDto = new ArrayList<>();
        userDTO.setUser("kevin");
        userDTO.setPassword("12345");
        listDto.add(userDTO);
        userDTO.setUser("Andrea");
        userDTO.setPassword("12345");
        listDto.add(userDTO);

        responseList = userController.createUsersAccount(listDto);

        LOGGER.info("Respuesta del servicio /createUsers : " + response.getStatusCodeValue());
        assertEquals(200, responseList.getStatusCodeValue());
    }


}
