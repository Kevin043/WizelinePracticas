package com.wizeline.maven.learningjava.controller;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wizeline.maven.learningjava.LearningJavaApplication;
import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.model.UserDTO;
import com.wizeline.maven.learningjava.service.UserService;
import com.wizeline.maven.learningjava.utils.CommonServices;
@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CommonServices commonServices;

    private static final Logger LOGGER = Logger.getLogger(LearningJavaApplication.class.getName());
    String msgProcPeticion = "LearningJava - Inicia procesamiento de peticion ...";

    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestParam String user, @RequestParam String password){
        LOGGER.info(msgProcPeticion);
        ResponseDTO response = new ResponseDTO();

        LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
        UserDTO userName = new UserDTO();
        StringBuilder builder = new StringBuilder("http://localhost:8080/api/login");
        builder.append("?user=" + user);
        builder.append("&password=" + password);
        URI uri = URI.create(builder.toString());
        userName = userName.getParameters(splitQuery(uri));
        response = commonServices.login(userName.getUser(), userName.getPassword());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<ResponseDTO>(response, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public  ResponseEntity<ResponseDTO> createUserAccount(@RequestBody UserDTO userDTO) {
        LOGGER.info(msgProcPeticion);
        ResponseDTO respDto = new ResponseDTO();

        respDto = createUser(userDTO.getUser(), userDTO.getPassword());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(respDto, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/createUsers")
    public  ResponseEntity<List<ResponseDTO>> createUsersAccount(@RequestBody List<UserDTO> userDTOList) {
        LOGGER.info(msgProcPeticion);
        AtomicReference<ResponseDTO> respDto = new AtomicReference<>(new ResponseDTO());
        List<ResponseDTO> responseList = new ArrayList<>();

        userDTOList.stream().forEach( userDTO -> {
                    String user = userDTO.getUser();
                    String password = userDTO.getPassword();
                    respDto.set(createUser(user, password));
                    responseList.add(respDto.get());
                }
        );

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");

        return new ResponseEntity<List<ResponseDTO>>(responseList, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/usuario")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestBody UserDTO userDTO){
        LOGGER.info(msgProcPeticion);
        ResponseDTO respDto = new ResponseDTO();
        respDto = userService.delete(userDTO.getUser(), userDTO.getPassword());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(respDto, responseHeaders, HttpStatus.OK);
    }

    @PutMapping("/usuario")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody List<UserDTO> userDTOList){
        LOGGER.info(msgProcPeticion);
        ResponseDTO respDto = new ResponseDTO();
        respDto = userService.update(userDTOList);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(respDto, responseHeaders, HttpStatus.OK);
    }


    public static Map<String, String> splitQuery(URI uri) {
        Map<String, String> queryPairs = new LinkedHashMap<String, String>();
        String query = uri.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8),
                    URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }
        return queryPairs;
    }

    private ResponseDTO createUser(String user, String password) {
        return userService.createUser(user, password);
    }
}
