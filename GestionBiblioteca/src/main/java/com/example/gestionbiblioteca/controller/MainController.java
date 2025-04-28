package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.Libro;
import com.example.gestionbiblioteca.modelo.LibroModelo;
import com.example.gestionbiblioteca.modelo.repository.impl.Conexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MainController {

    @FXML
    private VBox mainContent;

    @FXML
    private TextField searchField;
    private final Conexion conexion = new Conexion();
    @FXML
    private void agregarUsuario() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/CrearUsuario.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            CrearUsuarioController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Crear Usuario");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de creación de usuario");
            alert.showAndWait();
        }
    }

    @FXML
    private void listarUsuarios() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/VP.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            VPController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Listar Usuarios");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de creación de usuarios");
            alert.showAndWait();
        }
    }

    @FXML
    private void agregarLibro() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/CrearLibro.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            CrearLibroController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Crear Libro");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de creación de libro");
            alert.showAndWait();
        }
    }


    @FXML
    private void listarLibros() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/Libros.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            LibroController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Listar Libros");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de visualización de Libros");
            alert.showAndWait();
        }
    }

    @FXML
    private void nuevoPrestamo() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/CrearPrestamo.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            AgregarPrestamoController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Nuevo Prestamo");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de creación de prestamos");
            alert.showAndWait();
        }
    }

    @FXML
    private void historialPrestamos() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/VR.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            VRController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Listar Prestamos");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de visualización de Prestamos");
            alert.showAndWait();
        }
    }
    @FXML
    private void initialize() {
        mostrarLibros();  // Llamamos al método para cargar los libros cuando la vista se inicializa

        // Listener para detectar cambios en el campo de búsqueda
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Si el campo está vacío, mostramos todos los libros
            if (newValue.isEmpty()) {
                mostrarLibros();
            } else {
                // Si el campo tiene texto, buscamos por el título
                buscar();
            }
        });
    }

    @FXML
    private void buscar() {
        String query = searchField.getText().trim();

        if (query.isEmpty()) {
            // Si el campo de búsqueda está vacío, mostramos todos los libros
            mostrarLibros();
        } else {
            // Si el campo tiene texto, buscamos por el título
            List<Libro> librosEncontrados = buscarLibrosPorTitulo(query);
            if (librosEncontrados.isEmpty()) {
                // Si no se encuentra ningún libro, podemos mostrar un mensaje indicando esto
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sin resultados");
                alert.setHeaderText("No se encontró ningún libro con ese título.");
                alert.showAndWait();
            } else {
                // Mostrar todos los libros encontrados en la vista
                mostrarLibrosEnVista(librosEncontrados);
            }
        }
    }

    private List<Libro> buscarLibrosPorTitulo(String titulo) {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT idLibro, titulo, autor, anioPublicacion, editorial, disponible, portada " +
                "FROM libros WHERE titulo LIKE ?";  // No usamos LIMIT para que devuelva todos los libros que coincidan

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + titulo + "%");  // Usamos % para permitir coincidencias parciales

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idLibro = rs.getInt("idLibro");
                String tituloLibro = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anioPublicacion = rs.getInt("anioPublicacion");
                String editorial = rs.getString("editorial");
                boolean disponible = rs.getBoolean("disponible");
                String portada = rs.getString("portada");

                // Crear un libro con los datos obtenidos
                Libro libro = new Libro(idLibro, tituloLibro, autor, anioPublicacion, editorial, disponible, portada);
                libros.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }

    private void mostrarLibrosEnVista(List<Libro> libros) {
        // Limpiar el contenido previo del VBox
        mainContent.getChildren().clear();

        // Crear un TilePane para organizar los libros en 3 columnas
        TilePane tilePane = new TilePane();
        tilePane.setHgap(20);  // Espacio horizontal entre los libros
        tilePane.setVgap(20);  // Espacio vertical entre los libros
        tilePane.setPrefColumns(3);  // Mostrar 3 libros por fila
        tilePane.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Recorrer la lista de libros y añadir a la interfaz
        for (Libro libro : libros) {
            VBox libroBox = new VBox(10);  // Usar un VBox para cada libro con espaciado entre la portada y el título
            libroBox.setStyle("-fx-padding: 10; -fx-alignment: center;");

            // Crear el ImageView para la portada
            ImageView imageView = new ImageView();

            String rutaImagen = libro.getPortada();  // Ruta de la imagen (o URL)

            // Comprobar si la ruta es una ruta local o una URL
            if (rutaImagen.startsWith("http://") || rutaImagen.startsWith("https://")) {
                // Si es una URL (HTTP/HTTPS), usarla directamente
                Image image = new Image(rutaImagen);
                imageView.setImage(image);
            } else {
                // Si es una ruta local, verificar que el archivo exista
                File archivo = new File(rutaImagen);
                if (archivo.exists()) {
                    // Convertir la ruta de archivo en una URL válida
                    Image image = new Image(archivo.toURI().toString());
                    imageView.setImage(image);
                } else {
                    // Si no se encuentra el archivo, mostrar una imagen por defecto
                    Image image = new Image("file:default-image.jpg");  // Reemplaza con una imagen por defecto
                    imageView.setImage(image);
                }
            }

            // Establecer el tamaño de la imagen
            imageView.setFitWidth(150);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);  // Mantener la relación de aspecto

            // Crear el Label para el título del libro
            Label tituloLabel = new Label(libro.getTitulo());
            tituloLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: center;");

            // Asignar el manejador de eventos de clic a la imagen
            // Establecer el manejador de eventos de clic a la imagen
            imageView.setOnMouseClicked(event -> {
                // Mensaje para verificar que se hace clic en la portada
                System.out.println("Portada clickeada: " + libro.getTitulo()); // Mensaje de depuración
                abrirDetallesLibro(libro);  // Llamamos al método para abrir la ventana de detalles del libro
            });


            // Añadir la portada y el título al VBox
            libroBox.getChildren().addAll(imageView, tituloLabel);

            // Añadir el VBox al TilePane
            tilePane.getChildren().add(libroBox);
        }

        // Añadir el TilePane al VBox principal
        mainContent.getChildren().add(tilePane);
    }

    private void abrirDetallesLibro(Libro libro) {
        try {
            // Convertir el objeto Libro a un objeto LibroModelo
            LibroModelo libroModelo = new LibroModelo(
                    libro.getIdLibro(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getAnioPublicacion(),
                    libro.getEditorial(),
                    libro.isDisponible(),
                    libro.getPortada()
            );

            // Cargar el FXML de la ventana de detalles

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/DetallesLibro.fxml"));
            BorderPane root = loader.load();  // Si el FXML tiene BorderPane


            // Obtener el controlador de la vista cargada
            DetallesLibroController controller = loader.getController();

            // Pasar el objeto libroModelo al controlador
            controller.mostrarDetalles(libroModelo);

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Detalles del Libro");

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede abrir la ventana de detalles
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de detalles del libro");
            alert.showAndWait();
        }
    }
    @FXML
    private void ordenarLibrosAlfabeticamente() {
        List<Libro> libros = obtenerLibrosDesdeBaseDeDatos();
        libros.sort((libro1, libro2) -> libro1.getTitulo().compareToIgnoreCase(libro2.getTitulo()));  // Orden alfabético

        // Mostrar los libros ordenados
        mostrarLibrosEnVista(libros);
    }


    @FXML
    private void mostrarGrafico() {
        try {
            // Cargar el FXML de la vista del gráfico
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/grafico.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            GraficoController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Gráfico de Libros Más Prestados");

            // Pasar el Stage al controlador del gráfico
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana del gráfico");
            alert.showAndWait();
        }
    }

        public List<Libro> obtenerLibrosDesdeBaseDeDatos() {
            List<Libro> libros = new ArrayList<>();
            String query = "SELECT idLibro, titulo, autor, anioPublicacion, editorial, disponible, portada FROM libros";

            try (Connection conn = this.conexion.conectarBD();
                 Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int idLibro = rs.getInt("idLibro");
                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    int anioPublicacion = rs.getInt("anioPublicacion");
                    String editorial = rs.getString("editorial");
                    boolean disponible = rs.getBoolean("disponible");
                    String portada = rs.getString("portada");

                    // Crear un libro con los datos obtenidos
                    Libro libro = new Libro(idLibro, titulo, autor, anioPublicacion, editorial, disponible, portada);
                    libros.add(libro);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return libros;
        }

    @FXML
    private void mostrarLibros() {
        List<Libro> libros = obtenerLibrosDesdeBaseDeDatos();
        // Limpiar el contenido previo del VBox
        mainContent.getChildren().clear();

        // Crear un TilePane para organizar los libros en 3 columnas
        TilePane tilePane = new TilePane();
        tilePane.setHgap(20);  // Espacio horizontal entre los libros
        tilePane.setVgap(20);  // Espacio vertical entre los libros
        tilePane.setPrefColumns(3);  // Mostrar 3 libros por fila
        tilePane.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Recorrer la lista de libros y añadir a la interfaz
        for (Libro libro : libros) {
            VBox libroBox = new VBox(10);  // Usar un VBox para cada libro con espaciado entre la portada y el título
            libroBox.setStyle("-fx-padding: 10; -fx-alignment: center;");

            // Crear el ImageView para la portada
            ImageView imageView = new ImageView();

            String rutaImagen = libro.getPortada();  // Ruta de la imagen (o URL)

            // Comprobar si la ruta es una ruta local o una URL
            if (rutaImagen.startsWith("http://") || rutaImagen.startsWith("https://")) {
                // Si es una URL (HTTP/HTTPS), usarla directamente
                Image image = new Image(rutaImagen);
                imageView.setImage(image);
            } else {
                // Si es una ruta local, verificar que el archivo exista
                File archivo = new File(rutaImagen);
                if (archivo.exists()) {
                    // Convertir la ruta de archivo en una URL válida
                    Image image = new Image(archivo.toURI().toString());
                    imageView.setImage(image);
                } else {
                    // Si no se encuentra el archivo, mostrar una imagen por defecto
                    Image image = new Image("file:default-image.jpg");  // Reemplaza con una imagen por defecto
                    imageView.setImage(image);
                }
            }

            // Establecer el tamaño de la imagen
            imageView.setFitWidth(150);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);  // Mantener la relación de aspecto

            // Crear el Label para el título del libro
            Label tituloLabel = new Label(libro.getTitulo());
            tituloLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: center;");

            // Añadir la portada y el título al VBox
            libroBox.getChildren().addAll(imageView, tituloLabel);

            // Añadir el VBox al TilePane
            tilePane.getChildren().add(libroBox);
        }

        // Añadir el TilePane al VBox principal
        mainContent.getChildren().add(tilePane);
    }
    }


