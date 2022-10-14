package com.wizeline.maven.learningjava.service;

import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.model.UserDTO;

import java.util.List;

public interface UserService {

	ResponseDTO createUser(String user, String password);
	
	ResponseDTO login(String user, String password);

	ResponseDTO delete(String user, String pas);

	ResponseDTO update(List<UserDTO> listaUsuario);
	
}
