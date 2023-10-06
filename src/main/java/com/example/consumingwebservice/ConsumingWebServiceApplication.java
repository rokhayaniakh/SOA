package com.example.consumingwebservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.GetCountryResponse;

import java.util.Scanner;

@SpringBootApplication
public class ConsumingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient quoteClient) {
        return args -> {
            String country;
            System.out.println("-------------------Veuillez entrer un pays--------------------");
            Scanner sc = new Scanner(System.in);

            while (sc.hasNext()){
                country = sc.nextLine();

                try {
                    GetCountryResponse response = quoteClient.getCountry(country);
                    System.out.println("--------------------Infos Pays-------------------------------");
                    System.out.println("PAYS: " +response.getCountry().getName());
                    System.out.println("CAPITAL: " +response.getCountry().getCapital());
                    System.out.println("MONNAIE: " +response.getCountry().getCurrency());
                    System.out.println("POPULATION: " +response.getCountry().getPopulation());
                }catch (NullPointerException e){
                    System.out.println("Ce pays n\'existe pas ");
                }

            }
        };
    }

}