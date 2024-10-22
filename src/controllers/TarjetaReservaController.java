package controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.Reservas;
import models.ReservasDao;
import java.time.LocalDate;

public class TarjetaReservaController implements Initializable {

    @FXML
    private Button btn_editarReserva;

    @FXML
    private Button btn_eliminarReserva;

    @FXML
    private Button btn_reservar;

    @FXML
    private GridPane contenedor_tarjetasR;

    @FXML
    private DatePicker dp_fecha;

    @FXML
    private Label lbl_hora;

    @FXML
    private Label lbl_nombre;

    @FXML
    private Label lbl_telefono;

    @FXML
    private Label lbl_fecha;

    @FXML
    private Label lbl_motivo;

    @FXML
    private Label lbl_personas;

    CajeraViewController cajeraController = new CajeraViewController();
    ReservasDao reservasDao = new ReservasDao();
    Reservas reservas = new Reservas();
    private int currentColumn = 0;
    private int currentRow = 0;
    private int maxColumns = 3;
    
    private String idReserva;

    public void setCajeraController(CajeraViewController cajeraController) {
        this.cajeraController = cajeraController;
    }

    public Button getBtn_editarReserva() {
        return btn_editarReserva;
    }

    public Button getBtn_deletereserva() {
        return btn_eliminarReserva;
    }

    public void setDatosReserva(Reservas reserva, String hora) {
        this.idReserva = reserva.getIdReservas();
        lbl_nombre.setText("Nombre: " + reserva.getNombreCliente());
        lbl_telefono.setText("Telefono: " + reserva.getTelefono());
        lbl_fecha.setText("Fecha: " + reserva.getFecha());
        lbl_motivo.setText("Nota: " + reserva.getNota());
        lbl_personas.setText("Personas: " + reserva.getNumeroPersonas());
        lbl_hora.setText(hora);
    }

    public void handleEditar() {
        if (confirmarEdicion()) {
            editarReserva(idReserva);
        }
    }

    private boolean confirmarEdicion() {
        // Aquí puedes mostrar un diálogo de confirmación
        // Por ejemplo, usando Alert de JavaFX:
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas editar esta reserva?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void editarReserva(String idReserva) {
        cajeraController.getGlassPane().setVisible(true);

        cajeraController.getTxt_nombre().setText(lbl_nombre.getText().replace("Nombre: ", ""));
        cajeraController.getTxt_telefono().setText(lbl_telefono.getText().replace("Telefono: ", ""));
        cajeraController.getDp_fecha().setValue(LocalDate.parse(lbl_fecha.getText().replace("Fecha: ", "")));
        cajeraController.getTxt_motivo().setText(lbl_motivo.getText().replace("Nota: ", ""));
        cajeraController.getTxt_personas().setText(lbl_personas.getText().replace("Personas:", ""));
        cajeraController.getTxt_hora().setText(lbl_hora.getText());
        
        // Almacena el ID de la reserva actual en el CajeraViewController para que se use al guardar
        cajeraController.setIdReservaActual(idReserva);
    }

    public void actualizarReserva() {
        String idReservaActual = cajeraController.getIdReservaActual();
        // Cargar otros campos de texto y DatePicker con los valores actuales
        // Guardar los cambios en la base de datos al presionar un botón de "Guardar cambios"
        // Crear un nuevo objeto de reserva con los datos actualizados
        Reservas reservaActualizada = new Reservas();
        reservaActualizada.setNombreCliente(cajeraController.getTxt_nombre().getText());
        reservaActualizada.setFecha(java.sql.Date.valueOf(cajeraController.getDp_fecha().getValue()));
        reservaActualizada.setNumeroPersonas(Integer.parseInt(cajeraController.getTxt_personas().getText()));
        reservaActualizada.setTelefono(cajeraController.getTxt_telefono().getText());
        reservaActualizada.setNota(cajeraController.getTxt_motivo().getText());
        reservaActualizada.setIdReservas(idReservaActual);

        if (reservasDao.actualizarReserva(reservaActualizada)) {

            cajeraController.getContenedor_tarjetasR().getChildren().clear();
            cajeraController.cargarReservas(); // Actualiza las reservas visibles
            cajeraController.limpiarFormulario(); // Limpiar los campos del formulario
        } else {
            System.out.println("Error al guardar la reserva en la base de datos.");
        }
    }

    public void handleEliminar(String idReserva) {
        // Confirmar eliminación
        if (confirmarEliminacion()) {
            eliminarReserva(idReserva);
        }
    }

    // Método para confirmar la eliminación
    private boolean confirmarEliminacion() {
        // Aquí puedes mostrar un diálogo de confirmación
        // Por ejemplo, usando Alert de JavaFX:
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas eliminar esta reserva?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    // Método para eliminar la reserva
    public void eliminarReserva(String reservaId) {
        // Eliminar la reserva de la base de datos
        boolean eliminado = reservasDao.eliminarReserva(reservaId);
        // Actualizar la vista de reservas en CajeraViewController
        if (eliminado) {
            // Obtener el controlador de la vista principal (CajeraViewController)
            if (cajeraController != null) {
                // Limpiar y recargar las reservas
                cajeraController.getContenedor_tarjetasR().getChildren().clear();
                cajeraController.cargarReservas(); // Actualiza las reservas visibles
                cajeraController.moverBotonAddReservaEliminar(); // Mueve el botón de agregar
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
