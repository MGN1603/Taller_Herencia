package vista;

import Controladores.ControladorPersona;
import Modelo.Persona;
import Modelo.Propietario;
import Modelo.Veterinario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Ventana extends JFrame {

    private ControladorPersona controladorPersona;
    private JTextField txtNombre, txtIdentificacion, txtExtra;
    private JTextArea areaListado;
    private JComboBox<String> cmbTipo;

    public Ventana() {
        setTitle("Gestión de Personas");
        setSize(600, 500);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        controladorPersona = new ControladorPersona();
        initUI();
        mostrarListado();
    }

    private void initUI() {
        JPanel panelSuperior = new JPanel(new GridLayout(5, 2, 5, 5));

        txtNombre = new JTextField();
        txtIdentificacion = new JTextField();
        txtExtra = new JTextField();

        cmbTipo = new JComboBox<>(new String[]{"Propietario", "Veterinario"});
        cmbTipo.addActionListener(e -> actualizarEtiquetaExtra());

        panelSuperior.add(new JLabel("Nombre:"));
        panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Identificación:"));
        panelSuperior.add(txtIdentificacion);
        panelSuperior.add(new JLabel("Tipo de persona:"));
        panelSuperior.add(cmbTipo);
        panelSuperior.add(new JLabel("Teléfono / Especialidad:"));
        panelSuperior.add(txtExtra);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar por ID");

        btnAgregar.addActionListener(this::agregarPersona);
        btnEliminar.addActionListener(this::eliminarPersona);

        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnEliminar);

        areaListado = new JTextArea();
        areaListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListado);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void actualizarEtiquetaExtra() {
        String tipo = (String) cmbTipo.getSelectedItem();
        JLabel lblExtra = (JLabel) ((JPanel) getContentPane().getComponent(0)).getComponent(6);
        if (tipo.equals("Propietario")) {
            lblExtra.setText("Teléfono:");
        } else {
            lblExtra.setText("Especialidad:");
        }
    }

    private void agregarPersona(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String documento = txtIdentificacion.getText().trim();
        String extra = txtExtra.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();

        // Validación simple
        if (nombre.isEmpty() || documento.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        Persona persona;
        if (tipo.equals("Propietario")) {
            persona = new Propietario(nombre, documento, extra);
        } else {
            persona = new Veterinario(extra, nombre, documento);
        }

        controladorPersona.guardarPersona(persona);
        limpiarCampos();
        mostrarListado();
    }

    private void eliminarPersona(ActionEvent e) {
        String id = JOptionPane.showInputDialog(this, "Ingrese ID a eliminar:");
        if (id != null && !id.trim().isEmpty()) {
            controladorPersona.eliminarPersona(id.trim());
            mostrarListado();
        }
    }

    private void mostrarListado() {
        ArrayList<Persona> lista = controladorPersona.listar();

        // Verificar que la lista no sea null antes de usarla
        if (lista != null && !lista.isEmpty()) {
            StringBuilder sb = new StringBuilder("Listado de personas:\n\n");
            for (Persona p : lista) { // Usar la variable 'lista' en lugar de llamar listar() otra vez
                sb.append(p).append("\n");
            }
            areaListado.setText(sb.toString());
        } else {
            // Mostrar mensaje cuando no hay datos
            areaListado.setText("No hay personas registradas");
            System.out.println("La lista está vacía o no inicializada");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtIdentificacion.setText("");
        txtExtra.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ventana().setVisible(true));
    }
}
