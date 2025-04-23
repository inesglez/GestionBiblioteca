package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.repository.impl.Conexion;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraficoController {

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private final Conexion conexion = new Conexion();

    private List<Map.Entry<String, Integer>> obtenerLibrosMasPrestados() {
        List<Map.Entry<String, Integer>> librosMasPrestados = new ArrayList<>();

        String query = "SELECT l.titulo, COUNT(p.idPrestamo) AS prestamos " +
                "FROM prestamos p " +
                "JOIN libros l ON p.titulo = l.titulo " +
                "GROUP BY l.titulo " +
                "ORDER BY prestamos DESC " +
                "LIMIT 10"; // Limita los resultados a los 10 libros más prestados

        try (Connection conn = this.conexion.conectarBD();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String libro = rs.getString("titulo");
                int prestamos = rs.getInt("prestamos");
                librosMasPrestados.add(Map.entry(libro, prestamos));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return librosMasPrestados;
    }



    @FXML
    private void initialize() {
        try {
            // Obtener los libros más prestados desde la base de datos
            List<Map.Entry<String, Integer>> librosMasPrestados = obtenerLibrosMasPrestados();

            // Configurar los ejes
            xAxis.setLabel("Libro");
            yAxis.setLabel("Número de Préstamos");

            // Crear la serie de datos para el gráfico
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Libros Más Prestados");

            // Añadir los datos al gráfico
            for (Map.Entry<String, Integer> entry : librosMasPrestados) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }

            // Añadir la serie al gráfico
            barChart.getData().add(series);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para setear el Stage (ventana) si es necesario
    public void setVentana(Stage stage) {
        // Si necesitas hacer algo con el Stage, puedes hacerlo aquí
    }
}
