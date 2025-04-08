package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.LibroVO;
import com.example.gestionbiblioteca.modelo.LibroModelo;
import com.example.gestionbiblioteca.modelo.repository.impl.LibroRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class LibroController {

    @FXML
    private TableView<LibroModelo> tablaLibros;
    @FXML
    private TableColumn<LibroModelo, String> columnaTitulo;
    @FXML
    private TableColumn<LibroModelo, String> columnaAutor;

    @FXML
    private TextField ponerIdLibro;
    @FXML
    private TextField ponerTitulo;
    @FXML
    private TextField ponerAutor;
    @FXML
    private TextField ponerAnioPublicacion;
    @FXML
    private TextField ponerEditorial;
    @FXML
    private CheckBox ponerDisponible;
    @FXML
    private TextField ponerPortada;

    @FXML
    private Button botonNuevoLibro;
    @FXML
    private Button botonEditarLibro;
    @FXML
    private Button botonEliminarLibro;

    private ObservableList<LibroModelo> listaLibros;
    private final LibroRepositoryImpl libroRepository = new LibroRepositoryImpl();
    private Stage ventana;

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    public void initialize() {
        // Inicializar las columnas de la tabla
        columnaTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        columnaAutor.setCellValueFactory(cellData -> cellData.getValue().autorProperty());

        // Cargar los libros
        cargarLibros();
    }

    private void cargarLibros() {
        listaLibros = FXCollections.observableArrayList();

        try {
            // Cargar todos los libros desde el repositorio
            List<LibroVO> libros = libroRepository.obtenerListaLibros();
            for (LibroVO libro : libros) {
                listaLibros.add(new LibroModelo(libro.getIdLibro(), libro.getTitulo(), libro.getAutor(),
                        libro.getAnioPublicacion(), libro.getEditorial(), libro.isDisponible(), libro.getPortada()));
            }

            // Establecer los datos en la tabla
            tablaLibros.setItems(listaLibros);
        } catch (Exception e) {
            e.printStackTrace();  // Imprime la excepción en la consola
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar la vista");
            alert.setContentText(e.getMessage()); // Mostrar el mensaje de error completo
            alert.showAndWait();
        }
    }

    @FXML
    private void botonEditarLibro() {
        // Verificar si un libro ha sido seleccionado en la tabla
        LibroModelo libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (libroSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un libro para editar.");
            return;
        }

        // Obtener los datos del formulario
        int idLibro = Integer.parseInt(ponerIdLibro.getText());
        String titulo = ponerTitulo.getText();
        String autor = ponerAutor.getText();
        int anioPublicacion = Integer.parseInt(ponerAnioPublicacion.getText());
        String editorial = ponerEditorial.getText();
        boolean disponible = ponerDisponible.isSelected();
        String portada = ponerPortada.getText();

        // Validar que los campos no estén vacíos
        if (titulo.isEmpty() || autor.isEmpty() || editorial.isEmpty() || portada.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        try {
            // Crear el objeto LibroVO con los datos editados
            LibroVO libroEditado = new LibroVO(idLibro, titulo, autor, anioPublicacion, editorial, disponible, portada);

            // Actualizar el libro en el repositorio
            libroRepository.editLibro(libroEditado);

            // Actualizar la lista de libros y refrescar la tabla
            cargarLibros();

            // Limpiar los campos de entrada
            limpiarCampos();

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo editar el libro.");
        }
    }

    @FXML
    private void botonVerDetalles() {
        // Verificar si un libro ha sido seleccionado en la tabla
        LibroModelo libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (libroSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un libro para ver sus detalles.");
            return;
        }

        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/DetallesLibro.fxml"));
            BorderPane root = (BorderPane) loader.load();  // Cambiar AnchorPane por BorderPane

            // Crear una nueva ventana (Stage)
            Stage ventanaDetalles = new Stage();
            ventanaDetalles.setTitle("Detalles del Libro");

            // Obtener el controlador de la ventana de detalles
            DetallesLibroController detallesLibroController = loader.getController();

            // Pasar el libro seleccionado al controlador de detalles
            detallesLibroController.mostrarDetalles(libroSeleccionado);

            // Crear la escena y mostrar la ventana
            Scene scene = new Scene(root);
            ventanaDetalles.setScene(scene);
            ventanaDetalles.show();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los detalles del libro.");
            e.printStackTrace();
        }
    }


    @FXML
    private void botonEliminarLibro() {
        // Verificar si un libro ha sido seleccionado en la tabla
        LibroModelo libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (libroSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un libro para eliminar.");
            return;
        }

        // Confirmación antes de eliminar
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro de que desea eliminar el libro con ID " + libroSeleccionado.getIdLibro() + "?");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            try {
                // Eliminar el libro del repositorio
                libroRepository.deleteLibro(libroSeleccionado.getIdLibro());

                // Eliminar el libro de la lista y refrescar la tabla
                listaLibros.remove(libroSeleccionado);

            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo eliminar el libro.");
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        // Limpiar los campos de entrada
        ponerIdLibro.clear();
        ponerTitulo.clear();
        ponerAutor.clear();
        ponerAnioPublicacion.clear();
        ponerEditorial.clear();
        ponerDisponible.setSelected(false);
        ponerPortada.clear();
    }
}
