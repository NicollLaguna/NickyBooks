package NickyDev.NickyBooks.principal;

import NickyDev.NickyBooks.model.Datos;
import NickyDev.NickyBooks.model.DatosLibros;
import NickyDev.NickyBooks.service.ConsumoAPI;
import NickyDev.NickyBooks.service.ConvierteDatos;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ventana extends JFrame {

    private final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private DefaultListModel<String> listModel;
    private JPanel panelPrincipal, panelSuperior, panelCentral, panelBotones, panelEstadisticas;
    private JLabel lblNombreLibro, lblTop, lblEstadisticas, lblError;
    private JTextField txtNombreLibro;
    private JButton btnBuscarLibro, btnTop, btnEstadisticas;

    public Ventana() {
        createPanel();
        setTitle("Gestión de Libros - NickyBooks");
        ImageIcon icono = new ImageIcon(getClass().getResource("/images/icono1.png"));
        setIconImage(icono.getImage());
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createPanel() {
        // Crear los paneles con layout adecuado
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCentral = new JPanel(new BorderLayout(5, 5));
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelEstadisticas = new JPanel(new BorderLayout(5, 5));

        listModel = new DefaultListModel<>();
        JList<String> resultList = new JList<>(listModel);

        // Estilizar componentes
        lblNombreLibro = new JLabel("Ingresa el nombre del libro que deseas buscar:");
        lblNombreLibro.setFont(new Font("Arial", Font.BOLD, 14));

        lblError = new JLabel();
        lblError.setForeground(Color.RED);

        txtNombreLibro = new JTextField(30);
        btnBuscarLibro = new JButton("Buscar");
        estilizarBoton(btnBuscarLibro);

        lblTop = new JLabel("Conoce el Top de los 10 libros más descargados:");
        lblTop.setFont(new Font("Arial", Font.BOLD, 14));

        btnTop = new JButton("Top 10");
        btnTop.setPreferredSize(new Dimension(120, 30)); // Tamaño ajustado
        estilizarBoton(btnTop);

        lblEstadisticas = new JLabel("Da click para ver las estadísticas:");
        lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));

        btnEstadisticas = new JButton("Estadísticas");
        btnEstadisticas.setPreferredSize(new Dimension(120, 30)); // Tamaño ajustado
        estilizarBoton(btnEstadisticas);

        // Panel superior
        panelSuperior.add(lblNombreLibro);
        panelSuperior.add(txtNombreLibro);
        panelSuperior.add(btnBuscarLibro);

        // Panel de botones para Top y Estadísticas
        panelBotones.add(lblTop);
        panelBotones.add(btnTop);
        panelBotones.add(lblEstadisticas);
        panelBotones.add(btnEstadisticas);

        // Añadir el JList al panel central con Scroll
        panelCentral.add(new JScrollPane(resultList), BorderLayout.CENTER);

        // Panel de error
        panelCentral.add(lblError, BorderLayout.SOUTH);

        // Añadir subpaneles al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Eventos de los botones
        btnBuscarLibro.addActionListener(e -> buscarLibro(txtNombreLibro.getText()));
        btnTop.addActionListener(e -> mostrarTop10());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticas());

        // Añadir panel principal a la ventana
        getContentPane().add(panelPrincipal);
        setVisible(true); // Asegura que la ventana sea visible
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(70, 130, 180));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private void buscarLibro(String titulo) {
        var json = consumoApi.obtenerDatos(URL_BASE);

        listModel.clear();
        lblError.setText("");  // Limpiar el mensaje de error
        json = consumoApi.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        if (titulo.isEmpty()) {
            lblError.setText("Por favor, ingresa un título para buscar.");
            return;
        }

        try {
            Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(titulo.toUpperCase()))
                    .findFirst();

            // Si el libro fue encontrado
            if (libroBuscado.isPresent()) {
                DatosLibros libro = libroBuscado.get();
                String autor = "";

                // Verificar si la lista de autores no está vacía y acceder al primer autor
                if (libro.autor() != null && !libro.autor().isEmpty()) {
                    autor = libro.autor().get(0).nombre(); // Asumiendo que 'nombre' es un campo de DatosAutor
                }

                // Agregar el libro encontrado al modelo de la lista
                listModel.addElement("Título: " + libro.titulo() + " | Autor: " + autor);
            } else {
                // Si no se encontró el libro
                lblError.setText("No se encontraron libros con el título proporcionado.");
            }

        } catch (Exception e) {
            lblError.setText("Error al realizar la búsqueda. Inténtalo de nuevo.");
            e.printStackTrace();
        }
    }

    private void mostrarTop10() {
        listModel.clear();
        lblError.setText("");

        try {
            // Obtener datos desde la API
            var json = consumoApi.obtenerDatos(URL_BASE);
            var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

            // Limpiar posibles errores previos
            lblError.setText("");

            // Ordenar y mostrar los top 10 libros por descargas
            datosBusqueda.resultados().stream()
                    .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed()) // Ordenar por descargas
                    .limit(10) // Limitar a los primeros 10
                    .map(l -> l.titulo().toUpperCase()) // Convertir título a mayúsculas
                    .forEach(titulo -> listModel.addElement(titulo)); // Añadir al modelo de la lista

        } catch (Exception e) {
            lblError.setText("Error al obtener el Top 10.");
            e.printStackTrace();
        }
    }

    private void mostrarEstadisticas() {
        listModel.clear();
        lblError.setText("");

        try {
            // Obtener datos desde la API
            var json = consumoApi.obtenerDatos(URL_BASE);
            var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

            // Limpiar posibles errores previos
            lblError.setText("");

            // Trabajar con estadísticas de las descargas
            DoubleSummaryStatistics est = datosBusqueda.resultados().stream()
                    .filter(d -> d.numeroDeDescargas() > 0) // Filtrar aquellos con descargas mayores a 0
                    .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas)); // Calcular estadísticas

            // Añadir las estadísticas al modelo de la lista
            listModel.addElement("Cantidad media de descargas: " + est.getAverage());
            listModel.addElement("Cantidad máxima de descargas: " + est.getMax());
            listModel.addElement("Cantidad mínima de descargas: " + est.getMin());
            listModel.addElement("Cantidad de registros evaluados para calcular las estadísticas: " + est.getCount());

        } catch (Exception e) {
            lblError.setText("Error al obtener estadísticas.");
            e.printStackTrace();
        }
    }
}
