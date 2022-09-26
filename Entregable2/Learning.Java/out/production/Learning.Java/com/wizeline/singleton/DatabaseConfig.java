package com.wizeline.singleton;

public class DatabaseConfig {

    private static DatabaseConfig instancia;
    public String valor;

    private DatabaseConfig(String valor) {
        this.valor = valor;
    }

    public static DatabaseConfig getInstancia( String valor) {
        if(instancia ==null){
            instancia = new DatabaseConfig( valor );
        }
        return instancia;
    }

    public void conectar(){
        System.out.println("Connecting to " + valor + " database ...");
    }


}
