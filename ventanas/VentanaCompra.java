package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.CompraDao;
import vo.CompraVo;


public class VentanaCompra extends JFrame implements ActionListener {

    CompraDao miCompraDao;

    private JLabel labelTitulo, labelTabla1;
    private JTextField textCodCliente, textCodArt,  textFecha, textUnidades;

    private JLabel codCliente, codArt, fecha, unidades;
    private JButton botonGuardar, botonCancelar;
    JTable mitabla1;
    JScrollPane mibarra1;

    public VentanaCompra() {
        miCompraDao = new CompraDao();

        inicializarVista();
    }

    private void inicializarVista() {
        botonGuardar = new JButton();
        botonGuardar.setBounds(110, 200, 120, 25);
        botonGuardar.setText("Registrar");
        add(botonGuardar);
        botonGuardar.addActionListener(this);


        botonCancelar = new JButton();
        botonCancelar.setBounds(250, 200, 120, 25);
        botonCancelar.setText("Cancelar");
        add(botonCancelar);
        botonCancelar.addActionListener(this);

        labelTitulo = new JLabel();
        labelTitulo.setText("REGISTRO DE COMPRA");
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
        codCliente = new JLabel();
        codCliente.setText("Cod Cliente");
        codCliente.setBounds(20, 120, 80, 25);
        add(codCliente);

        codArt = new JLabel();
        codArt.setText("Cod Art");
        codArt.setBounds(290, 160, 80, 25);
        add(codArt);

        fecha = new JLabel();
        fecha.setText("Fecha \n yyyy-mm-dd");
        fecha.setBounds(290, 120, 80, 25);
        add(fecha);

        unidades = new JLabel();
        unidades.setText("Unidades" );
        unidades.setBounds(20, 160, 80, 25);
        add(unidades);





        textCodCliente = new JTextField();
        textCodCliente.setBounds(90, 120, 180, 25);
        add(textCodCliente);

        textCodArt = new JTextField();
        textCodArt.setBounds(340, 160, 80, 25);
        add(textCodArt);

        textFecha = new JTextField();
        textFecha.setBounds(340, 120, 80, 25);
        add(textFecha);

        textUnidades = new JTextField();
        textUnidades.setBounds(80, 160, 190, 25);
        add(textUnidades);



        // ////////////////////////////////////////////
        mibarra1 = new JScrollPane();
        mibarra1.setBounds(40, 300, 400, 130);
        mostrarDatosUsandoLogica1();// mostramos la tabla




        add(labelTitulo);
        add(labelTabla1);
        add(mibarra1);

        limpiar();
        setSize(480, 650);
        setTitle("TABLA COMPRA");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
    }

    public void mostrarDatosUsandoLogica1() {

        String titulos[] = { "codCliente", "codArt", "fecha", "unidades" };
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
        ArrayList<CompraVo> miCompra = miCompraDao.buscarCompraConMatriz();
        /*
         * como sabemos que son 5 campos, definimos ese valor por defecto para
         * las columnaslas filas dependen de los registros retornados
         */
        String[][] informacion = new String[miCompra.size()][4];

        for (int x = 0; x < informacion.length; x++) {
            informacion[x][0] = miCompra.get(x).getCodCliente() + "";
            informacion[x][1] = miCompra.get(x).getCodArticulo() + "";
            informacion[x][2] = miCompra.get(x).getFecha() + "";
            informacion[x][3] = miCompra.get(x).getUnidades() + "";

        }
        return informacion;
    }

    private void limpiar() {
        textCodCliente.setText("");
        textCodArt.setText("");
        textFecha.setText("");
        textUnidades.setText("");


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGuardar) {

            try {
                CompraVo miCompra = new CompraVo();
                miCompra.setCodCliente(Integer.parseInt(textCodCliente.getText()));
                miCompra.setCodArticulo(Integer.parseInt(textCodArt.getText()));
               miCompra.setFecha((Date) new SimpleDateFormat("yyyy-MM-dd").parse(textFecha.getText()));
                miCompra.setUnidades(Integer.parseInt(textUnidades.getText()));








                miCompraDao.registrarCompra(miCompra);
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