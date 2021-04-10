package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import dao.ClienteDao;

import vo.ClienteVo;

public class VentanaClientes extends JFrame implements ActionListener  {

    ClienteDao miClienteDao;

    private JLabel labelTitulo, labelTabla1;
    private JTextField textCod, textNombre, textApellido, textDireccion,textLocalidad, textProvincia, textCodPost, textTel, textDni;

    private JLabel cod, nombre, apellido, direccion,localidad, provincia, codigopostal, telefono, dni;
    private JButton botonGuardar, botonCancelar;
    JTable mitabla1;
    JScrollPane mibarra1;

    public VentanaClientes() {
        miClienteDao = new ClienteDao();

        inicializarVista();
    }

    private void inicializarVista() {
        botonGuardar = new JButton();
        botonGuardar.setBounds(110, 500, 120, 25);
        botonGuardar.setText("Registrar");
        add(botonGuardar);
        botonGuardar.addActionListener(this);


        botonCancelar = new JButton();
        botonCancelar.setBounds(250, 500, 120, 25);
        botonCancelar.setText("Cancelar");
        add(botonCancelar);
        botonCancelar.addActionListener(this);

        labelTitulo = new JLabel();
        labelTitulo.setText("REGISTRO DE CLIENTES");
        labelTitulo.setBounds(120, 20, 380, 30);
        labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

        labelTabla1 = new JLabel();
        labelTabla1.setText("Tabla Usando Matriz de datos");
        labelTabla1.setBounds(40, 250, 380, 50);



		/*		cod = new JLabel();
		cod.setText("Codigo");
		cod.setBounds(20, 80, 80, 25);
		add(cod);
		 */
        cod = new JLabel();
        cod.setText("Codigo");
        cod.setBounds(20, 60, 80, 25);
        add(cod);

        nombre = new JLabel();
        nombre.setText("Nombre");
        nombre.setBounds(20, 100, 80, 25);
        add(nombre);

        apellido = new JLabel();
        apellido.setText("Apellido");
        apellido.setBounds(20, 140, 80, 25);
        add(apellido);

        direccion = new JLabel();
        direccion.setText("Direccion");
        direccion.setBounds(200, 140, 80, 25);
        add(direccion);


        localidad = new JLabel();
        localidad.setText("Localidad");
        localidad.setBounds(200, 60, 80, 25);
        add(localidad);


        provincia = new JLabel();
        provincia.setText("Provincia");
        provincia.setBounds(200, 100, 80, 25);
        add(provincia);

        codigopostal = new JLabel();
        codigopostal.setText("CP");
        codigopostal.setBounds(20, 180, 80, 25);
        add(codigopostal);

        telefono = new JLabel();
        telefono.setText("Telefono");
        telefono.setBounds(200, 180, 80, 25);
        add(telefono);

        dni = new JLabel();
        dni.setText("DNI");
        dni.setBounds(20, 220, 80, 25);
        add(dni);



        textCod = new JTextField();
        textCod.setBounds(65, 60, 80, 25);
        add(textCod);

        textNombre = new JTextField();
        textNombre.setBounds(65, 100, 80, 25);
        add(textNombre);

        textApellido = new JTextField();
        textApellido.setBounds(65, 140, 80, 25);
        add(textApellido);

        textDireccion = new JTextField();
        textDireccion.setBounds(255, 140, 190, 25);
        add(textDireccion);

        textLocalidad = new JTextField();
        textLocalidad.setBounds(255, 60, 190, 25);
        add(textLocalidad);

        textProvincia = new JTextField();
        textProvincia.setBounds(255, 100, 190, 25);
        add(textProvincia);

        textCodPost = new JTextField(); //65, 180, 100, 25
        textCodPost.setBounds(65, 180, 80, 25);
        add(textCodPost);

        textTel = new JTextField();
        textTel.setBounds(255, 180, 190, 25);
        add(textTel);

        textDni = new JTextField();
        textDni.setBounds(65, 220, 190, 25);
        add(textDni);

        // ////////////////////////////////////////////
        mibarra1 = new JScrollPane();
        mibarra1.setBounds(40, 300, 400, 130);
        mostrarDatosUsandoLogica1();// mostramos la tabla




        add(labelTitulo);
        add(labelTabla1);
        add(mibarra1);

        limpiar();
        setSize(480, 650);
        setTitle("TABLA CLIENTE");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
    }

    public void mostrarDatosUsandoLogica1() {

        String titulos[] = { "cod", "nombre", "apellido", "direccion","localidad", "provincia", "codigopostal", "telefono", "dni" };
        String informacion[][] = obtieneMatriz1();// obtenemos la informacion de
        // la BD

        mitabla1 = new JTable(informacion, titulos);
        mitabla1.setEnabled(false);
        mitabla1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mibarra1.setViewportView(mitabla1);
    }

    private String[][] obtieneMatriz1() {
        /*
         * llamamos al metodo que retorna la info de la BD y la almacena en la
         * lista
         */
        ArrayList<ClienteVo> miListaClientes = miClienteDao.buscarClienteConMatriz();
        /*
         * como sabemos que son 5 campos, definimos ese valor por defecto para
         * las columnaslas filas dependen de los registros retornados
         */
        String[][] informacion = new String[miListaClientes.size()][9];

        for (int x = 0; x < informacion.length; x++) {
            informacion[x][0] = miListaClientes.get(x).getCodCliente() + "";
            informacion[x][1] = miListaClientes.get(x).getNombreCliente() + "";
            informacion[x][2] = miListaClientes.get(x).getApellidoCliente() + "";
            informacion[x][3] = miListaClientes.get(x).getDireccionCliente() + "";
            informacion[x][4] = miListaClientes.get(x).getLocalidadCliente() + "";
            informacion[x][5] = miListaClientes.get(x).getProvinciaCliente() + "";
            informacion[x][6] = miListaClientes.get(x).getCodPostalCliente() + "";
            informacion[x][7] = miListaClientes.get(x).getTelCliente() + "";
            informacion[x][8] = miListaClientes.get(x).getDniCliente() + "";
        }
        return informacion;
    }

    private void limpiar() {
        textCod.setText("");
        textNombre.setText("");
        textApellido.setText("");
        textDireccion.setText("");
        textLocalidad.setText("");
        textProvincia.setText("");
        textCodPost.setText("");
        textTel.setText("");
        textDni.setText("");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGuardar) {

            try {
                ClienteVo miCliente = new ClienteVo();
                miCliente.setCodCliente(Integer.parseInt(textCod.getText()));
                miCliente.setNombreCliente(textNombre.getText());
                miCliente.setApellidoCliente(textApellido.getText());
                miCliente.setDireccionCliente(textDireccion.getText());
                miCliente.setLocalidadCliente(textLocalidad.getText());
                miCliente.setProvinciaCliente(textProvincia.getText());
                miCliente.setCodPostalCliente(textCodPost.getText());
                miCliente.setTelCliente(textTel.getText());
                miCliente.setDniCliente(textDni.getText());










                miClienteDao.registrarCliente(miCliente);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Error en el Ingreso de Datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                /* Actualizamos siempre las tablas despues del registro */
                mostrarDatosUsandoLogica1();
                limpiar();
            }
        }
        if (e.getSource() == botonCancelar) {
            limpiar();
        }
    }

}
