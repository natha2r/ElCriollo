package controllers;

import br.com.adilson.util.PrinterMatrix;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import models.DetallesPedidos;

public class Ticket {

    List<String> pedido = new ArrayList<>();

    public String construirFactura(List<DetallesPedidos> detalles, String mesa) {
        StringBuilder factura = new StringBuilder();
        // Obtener la fecha y hora actuales
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaHoraFormateada = fechaActual.format(formatter);

        // Información del restaurante
        factura.append("    El Criollo Restaurante \n\n");
        factura.append("        Cra 24 #20-58       \n");
        factura.append("       Tel: 607 6803436     \n");
        factura.append("        Nit: 73590725-7      \n\n");
        factura.append("       ").append(fechaHoraFormateada).append("\n\n");
        factura.append("           CUENTA             \n\n");
        factura.append("  Mesa: ").append(mesa).append("\n");
        factura.append("------------------------------\n");

        // Detalles del pedido
        double total = 0;
        for (DetallesPedidos detalle : detalles) {
            String linea = String.format("%d %-20s %8.2f\n",
                    detalle.getCantidad(),
                    detalle.getPlatosId(),
                    detalle.getPrecioUnitario());
            factura.append(linea);
            total += detalle.getCantidad() * detalle.getPrecioUnitario();
        }

        factura.append("------------------------------\n");
        // Agregar la sección de total
        factura.append(String.format("%-22s %8.2f\n", "Total", total));
        factura.append("------------------------------\n");

        // Mensaje de agradecimiento
        factura.append("    Gracias por tu compra   \n");
        factura.append("     Te esperamos pronto\n");
        factura.append("------------------------------\n");

        return factura.toString();
    }

    public void imprimirFactura(List<DetallesPedidos> detalles, String Mesa) {
        try {
            // Buscar impresoras disponibles en el sistema
            PrintService service = PrintServiceLookup.lookupDefaultPrintService(); // Usa la impresora predeterminada

            if (service != null) {
                PrinterMatrix printer = new PrinterMatrix();

                printer.setOutSize(30, 32);

                // Construir la factura
                String factura = construirFactura(detalles, Mesa);

                // Imprimir la factura
                String[] lineas = factura.split("\n");
                for (int i = 0; i < lineas.length; i++) {
                    printer.printTextWrap(i + 1, 5, 2, 32, lineas[i]);
                }
                printer.toFile("impresion.txt");

                FileInputStream inputStream = null;

                try {
                    inputStream = new FileInputStream("impresion.txt");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                // Crear un documento para la impresión
                Doc doc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);

                // Crear un trabajo de impresión
                DocPrintJob job = service.createPrintJob();

                // Establecer atributos de impresión
                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();

                // Enviar el trabajo de impresión a la impresora
                job.print(doc, attributes);
                System.out.println("Impresión completada.");
            } else {
                System.out.println("No se encontró ninguna impresora.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
