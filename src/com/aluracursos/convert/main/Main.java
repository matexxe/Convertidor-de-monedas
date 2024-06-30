package com.aluracursos.convert.main;

import com.aluracursos.convert.service.ConvertirMoneda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner SCANNER = new Scanner(System.in);
    private final ConvertirMoneda convertirMoneda = new ConvertirMoneda();
    private final List<String> historialConversiones = new ArrayList<>();
    private final List<LocalDateTime> fechasConversiones = new ArrayList<>();

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        while (true) {
            convertCurrency();
        }
    }

    public void convertCurrency() {
        System.out.println("Por favor, escribe la opción que deseas convertir:");
        System.out.println("1 - Dólar estadounidense");
        System.out.println("2 - Peso argentino");
        System.out.println("3 - Boliviano boliviano");
        System.out.println("4 - Real brasileño");
        System.out.println("5 - Peso chileno");
        System.out.println("6 - Peso colombiano");
        System.out.println("7 - Mostrar historial de conversiones");
        System.out.println("0 - Salir");
        System.out.println("Seleccione la moneda de origen: ");

        int fromSelection = SCANNER.nextInt();
        SCANNER.nextLine();  // Clear the buffer

        if (fromSelection == 0) {
            System.out.println("Aplicacion cerrada...");
            System.exit(0);
        } else if (fromSelection == 7) {
            mostrarHistorialConversiones();
            return;
        }

        String fromCurrency = getCurrencyCode(fromSelection);

        System.out.print("Ingrese la cantidad en " + fromCurrency + ": ");
        double amount = SCANNER.nextDouble();
        SCANNER.nextLine();  // Clear the buffer
        System.out.println("---------------------------------");
        System.out.println("Seleccione la moneda de destino:");
        System.out.println("1 - Dólar estadounidense");
        System.out.println("2 - Peso argentino");
        System.out.println("3 - Boliviano boliviano");
        System.out.println("4 - Real brasileño");
        System.out.println("5 - Peso chileno");
        System.out.println("6 - Peso colombiano");
        System.out.print("Seleccione la moneda de destino: ");

        int toSelection = SCANNER.nextInt();
        SCANNER.nextLine();


        String toCurrency = getCurrencyCode(toSelection);

        double convertedAmount = convertirMoneda.convertir(fromCurrency, toCurrency, amount);
        String formattedAmount = String.format("%.1f", amount);
        String formattedConvertedAmount = String.format("%.1f", convertedAmount);

        String conversionResult = formattedAmount + " " + fromCurrency + " son " + formattedConvertedAmount + " " + toCurrency;
        historialConversiones.add(conversionResult);
        fechasConversiones.add(LocalDateTime.now());

        System.out.printf("%s %s son %s %s%n", formattedAmount, fromCurrency, formattedConvertedAmount, toCurrency);
    }

    private void mostrarHistorialConversiones() {
        if (historialConversiones.isEmpty()) {
            System.out.println("No hay historial de conversiones.");
        } else {
            System.out.println("Historial de conversiones:");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            for (int i = 0; i < historialConversiones.size(); i++) {
                String conversion = historialConversiones.get(i);
                LocalDateTime fecha = fechasConversiones.get(i);
                System.out.println(conversion + " - " + formatter.format(fecha));
            }
        }
    }

    private String getCurrencyCode(int selection) {
        List<String> currencies = List.of("USD", "ARS", "BOB", "BRL", "CLP", "COP");
        if (selection < 1 || selection > currencies.size()) {
            throw new IllegalArgumentException("Selección inválida.");
        }
        return currencies.get(selection - 1);
    }
}
