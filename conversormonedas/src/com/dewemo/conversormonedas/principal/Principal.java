package com.dewemo.conversormonedas.principal;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    static String pais;
    static Double cantidad;
    static Double valorTotal;
    static boolean pasarAdolar;

    public void datosApi(String pais, Double cantidad, boolean pasarAdolar) throws IOException, InterruptedException {

        String direccionApi = "https://v6.exchangerate-api.com/v6/5f4162b132b0139dc035d711/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccionApi))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String datosApi = response.body();

        // Parsear el JSON
        JsonObject jsonObject = JsonParser.parseString(datosApi).getAsJsonObject();

        // Obtener el objeto de tasas de conversión
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        // Extraer el valor
        double tasa = conversionRates.get(pais).getAsDouble();

        //Double tasa;

        if (pais.equals("ARS")){
            if (pasarAdolar== false){
                valorTotal = tasa * cantidad;
                System.out.println("El valor es: " + valorTotal + " pesos argentinos");
            }else{
                valorTotal = cantidad / tasa;
                System.out.println("El valor es: " + valorTotal + " dolares");
            }
        }

        if (pais.equals("BRL")){
            if (pasarAdolar== false){
                valorTotal = tasa * cantidad;
                System.out.println("El valor es: " + valorTotal + " reales brasileños");
            }else{
                valorTotal = cantidad / tasa;
                System.out.println("El valor es: " + valorTotal + " dolares");
            }
        }

        if (pais.equals("COP")){
            if (pasarAdolar== false){
                valorTotal = tasa * cantidad;
                System.out.println("El valor es: " + valorTotal + " pesos colombianos");
            }else{
                valorTotal = cantidad / tasa;
                System.out.println("El valor es: " + valorTotal + " dolares");
            }
        }
    }

    Scanner teclado = new Scanner(System.in);

    public void muestraElMenu () throws IOException, InterruptedException {
        var opcion = -1;
        while (opcion != 7) {
            var menu = """
                    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
                    ▓         *** CONVERSOR DE MONEDA ***              ▓
                    ▓                                                  ▓
                    ▓ 1 - Dolar ===> Peso Argentino                    ▓
                    ▓ 2 - Peso Argentino ===> Dolar                    ▓
                    ▓ 3 - Dolar ===> Real Brasileño                    ▓
                    ▓ 4 - Real Brasileño ===> Dolar                    ▓
                    ▓ 5 - Dolar ===> Peso Colombiano                   ▓
                    ▓ 6 - Peso Colombiano ===> Dolar                   ▓
                    ▓ 7 - Salir                                        ▓
                    ▓                                                  ▓
                    ▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓
                    ▓ Digite Opción                                    ▓
                    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    dolarToPesoArgentino();
                    break;
                case 2:
                   pesoArgentinoToDolar();
                    break;
                case 3:
                   dolarToRealBrasilero();
                    break;
                case 4:
                    realBrasileroToDolar();
                    break;
                case 5:
                    dolarToPesoColombiano();
                    break;
                case 6:
                    pesoColombianoToDolar();
                    break;
                case 7:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void dolarToPesoArgentino () throws IOException, InterruptedException {
        System.out.println("Ingrese la cantidad de dolares a convertir:");
        cantidad = teclado.nextDouble();
        pais = "ARS";
        pasarAdolar = false;
        datosApi(pais, cantidad, pasarAdolar);
    }

    private void pesoArgentinoToDolar() throws IOException, InterruptedException {
        System.out.println("Ingrese la cantidad de pesos argentinos a convertir:");
        cantidad = teclado.nextDouble();
        pais = "ARS";
        pasarAdolar = true;
        datosApi(pais, cantidad, pasarAdolar);
    }

    private void dolarToRealBrasilero() throws IOException, InterruptedException {
        System.out.println("Ingrese la cantidad de dolares a convertir:");
        cantidad = teclado.nextDouble();
        pais = "BRL";
        pasarAdolar = false;
        datosApi(pais, cantidad, pasarAdolar);
    }

    private void realBrasileroToDolar() throws IOException, InterruptedException {
        System.out.println("Ingrese la cantidad de reales a convertir:");
        cantidad = teclado.nextDouble();
        pais = "BRL";
        pasarAdolar = true;
        datosApi(pais, cantidad, pasarAdolar);
    }

    private void dolarToPesoColombiano() throws IOException, InterruptedException {
        System.out.println("Ingrese la cantidad de dolares a convertir:");
        cantidad = teclado.nextDouble();
        pais = "COP";
        pasarAdolar = false;
        datosApi(pais, cantidad, pasarAdolar);
    }

    private void pesoColombianoToDolar() throws IOException, InterruptedException {
        System.out.println("Ingrese la cantidad de pesos colombianos a convertir:");
        cantidad = teclado.nextDouble();
        pais = "COP";
        pasarAdolar = true;
        datosApi(pais, cantidad, pasarAdolar);
    }
}

