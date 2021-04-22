package br.com.raynerweb.arquivos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws IOException {
        String cmd = "/bootstrap.sh &";
        Runtime run = Runtime.getRuntime();
        run.exec(cmd);
        SpringApplication.run(App.class, args);
    }

}
