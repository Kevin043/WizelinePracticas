package com.wizeline.maven.learningjava.repository;

import java.io.*;
import java.util.logging.Logger;

public class UserRepositoryImpl {

	private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class.getName());

	public String delete(String user, String pass) {
		readFile(user,pass);
		LOGGER.info("Inicia procesamiento en capa de acceso de datos");
		LOGGER.info("Inicia proceso de baja de usuaro en BD...");

		deleteFile(user, pass);

		LOGGER.info("Baja Exitosa");
		return "success";
	}

	public String actualizar(String userAnt, String passwordAnt, String userNew, String passwordNew){
		LOGGER.info("Inicia procesamiento en capa de acceso de datos");
		LOGGER.info("Inicia proceso de actualización de usuaro en BD...");
		updateFile(userAnt, passwordAnt, userNew, passwordNew);
		LOGGER.info("Baja Exitosa");
		return "success";
	}

	public String createUser(String user, String password) {
		createFile();
		LOGGER.info("Inicia procesamiento en capa de acceso de datos");
		LOGGER.info("Inicia proceso de alta de usuaro en BD...");

		writeFile(user, password);
		
		LOGGER.info("Alta exitosa");
		return "success";
	}

	public String login(String user, String password) {
		createFile();
		LOGGER.info("Inicia procesamiento en capa de acceso de datos");
		LOGGER.info("Inicia proceso de login...");
		if ("success".equals(readFile(user, password))) {
			LOGGER.info("Login exitoso");
			return "success";
		} else {
			return "Usuario o password incorrecto";
		}
	}


	private void createFile() {
		try {
			File myObj = new File("file.txt");
			if (myObj.createNewFile()) {
				LOGGER.info("File created: " + myObj.getName());
			} else {
				LOGGER.info("File already exists.");
			}
		} catch (IOException e) {
			LOGGER.info("An error occurred.");
			e.printStackTrace();
		}
	}
	
	private void writeFile(String user, String password) {
		try {
			File file = new File("file.txt");
			if (file.createNewFile()) {
				LOGGER.info("File created: " + file.getName());
			} else {
				LOGGER.info("File already exists.");
			}
			FileWriter fileWritter = new FileWriter(file.getName(),true);

			BufferedWriter bw = new BufferedWriter(fileWritter);

			bw.write(user+", "+password);
			bw.newLine();
			bw.close();
			LOGGER.info("Successfully wrote to the file.");
		} catch (IOException e) {
			LOGGER.info("An error occurred.");
			e.printStackTrace();
		}
	}

	private String readFile(String user, String password) {
		String result = "fail";
		try {
	        File file = new File("file.txt");
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        String line = "";
	        while ((line = br.readLine()) != null) {
	            if(line.contains(user) && line.contains(password)) {
	            	result = "success";
	            }
	        }
			br.close();
	      } catch (IOException e) {
	        LOGGER.info("An error occurred.");
	        e.printStackTrace();
	      }
		return result;
	}

	private void deleteFile(String user, String password) {
		String result = "fail";
		try {
			File file = new File("file.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			StringBuffer sb = new StringBuffer();
			line = br.readLine();
			while ((line ) != null) {
				if (!line.trim().equals(user + ", " +password)) {
					sb.append(line + "\n");
				}
				line = br.readLine();
			}
			br.close();
			file.delete();
			File file2 = new File("file.txt");
			file2.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getName(),true));
			bw.write(sb.toString());
			bw.close();
			LOGGER.info("El usuario se elimino correctamente.");
		} catch (IOException e) {
			LOGGER.info("A ocurrido un error");
			e.printStackTrace();
		}
	}

	private void updateFile(String userAnt, String passwordAnt, String userNew, String passwordNew) {
		try {
			File file = new File("file.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			StringBuffer sb = new StringBuffer();
			line = br.readLine();
			while ((line ) != null) {
				if (line.trim().equals(userAnt + ", " +passwordAnt)) {
					sb.append(userNew + ", " + passwordNew);
				} else  {
					sb.append(line + "\n");
				}

				line = br.readLine();
			}
			br.close();
			file.delete();
			File file2 = new File("file.txt");
			file2.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getName(),true));
			bw.write(sb.toString());
			bw.close();
			LOGGER.info("El usuario se elimino correctamente.");
			/*File file = new File("file.txt");
			if (file.createNewFile()) {
				LOGGER.info("File created: " + file.getName());
			} else {
				LOGGER.info("File already exists.");
			}
			FileWriter fileWritter = new FileWriter(file.getName(),true);

			BufferedWriter bw = new BufferedWriter(fileWritter);

			bw.write(userNew + ", " + passwordNew);
			bw.newLine();
			bw.close();*/
			LOGGER.info("Successfully wrote to the file.");
		} catch (IOException e) {
			LOGGER.info("An error occurred.");
			e.printStackTrace();
		}
	}
}
