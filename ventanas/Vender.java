package ventanas;

import javax.swing.*;

import vo.ArticuloVo;
import vo.ClienteVo;

import java.sql.Statement;

import conexion.Conexion;
import dao.ArticuloDao;
import dao.ClienteDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Vender extends JFrame implements ItemListener, ActionListener {

	private ArticuloDao miArticuloDao;
	private ClienteDao miClienteDao;

	private Conexion conex;
	private JComboBox combo1, combo2;
	private JLabel stockLbl, precioLbl, cantidadLbl, codigoLbl, costoLbl;
	private JLabel titulo, stock, precio, codCliente, costo;
	private JTextField textCantidad;
	private JButton botonVender;
	private double precioArt;

	public Vender() {
		miArticuloDao = new ArticuloDao();
		ArrayList<ArticuloVo> articulos = miArticuloDao.buscarArticulosConMatriz();

		miClienteDao = new ClienteDao();
		ArrayList<ClienteVo> clientes = miClienteDao.buscarClienteConMatriz();


		setLayout(null);

		titulo = new JLabel();
		titulo.setText("VENTA DE ARTICULOS");
		titulo.setBounds(120, 10, 380, 30);
		titulo.setFont(new java.awt.Font("Verdana", 1, 18));
		add(titulo);


		combo1 = new JComboBox();
		combo2 = new JComboBox();

		for (ArticuloVo articulo : articulos) {
			combo1.addItem(articulo);
		}

		for (ClienteVo cliente : clientes) {
			combo2.addItem(cliente);
		}

		combo1.setBounds(10, 50, 200, 20);
		combo1.addItemListener(this);
		add(combo1);


		combo2.setBounds(10, 100, 200, 20);
		combo2.addItemListener(this);
		add(combo2);

		stockLbl = new JLabel();
		stockLbl.setText("Stock");
		stockLbl.setBounds(220, 50, 80, 25);
		add(stockLbl);

		stock = new JLabel();
		stock.setBounds(260, 50, 80, 25);
		add(stock);

		precioLbl = new JLabel();
		precioLbl.setText("Precio");
		precioLbl.setBounds(290, 50, 80, 25);
		add(precioLbl);

		precio = new JLabel();
		precio.setBounds(340, 50, 80, 25);
		add(precio);

		codigoLbl = new JLabel();
		codigoLbl.setText("Codigo");
		codigoLbl.setBounds(220, 100, 80, 25);
		add(codigoLbl);

		codCliente = new JLabel();
		codCliente.setBounds(300, 100, 80, 20);
		add(codCliente);

		cantidadLbl = new JLabel();
		cantidadLbl.setText("cantidad");
		cantidadLbl.setBounds(20, 180, 80, 25);
		add(cantidadLbl);

		textCantidad = new JTextField();
		textCantidad.setBounds(150, 180, 190, 25);
		add(textCantidad);

		botonVender = new JButton();
		botonVender.setBounds(10, 220, 120, 25);
		botonVender.setText("VENDER");
		add(botonVender);
		botonVender.addActionListener(this);

		costoLbl = new JLabel();
		costoLbl.setText("Costo Total");
		costoLbl.setBounds(20, 280, 80, 25);
		add(costoLbl);

		costo = new JLabel();
		costo.setBounds(250, 280, 80, 25);
		add(costo);

	}


	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == combo1) {
			ArticuloVo seleccionado = (ArticuloVo) combo1.getSelectedItem();
			stock.setText(seleccionado.getStockArt().toString());
			precio.setText(seleccionado.getPrecioArt().toString());
			precioArt = seleccionado.getPrecioArt();
		} else if (e.getSource() == combo2) {
			ClienteVo seleccion = (ClienteVo) combo2.getSelectedItem();
			codCliente.setText(seleccion.getCodCliente().toString());
		}
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonVender) {

			try {
				double total = calcularTotal(Double.parseDouble(textCantidad.getText()),precioArt);
				costo.setText(Double.toString(total));
				JOptionPane.showMessageDialog(null,
						"Gracias!", "Compra Realizada",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Error en el Ingreso de Datos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public double calcularTotal(double cantidad, double precio) {
		double total = cantidad * precio;
		return total;
	}
}