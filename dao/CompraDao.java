package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import vo.CompraVo;

import conexion.Conexion;

public class CompraDao {
    private Conexion conex;

    public CompraDao() {
        conex = new Conexion();
    }

    public void registrarCompra(CompraVo miCompra) {
        try {
            Statement estatuto = conex.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO compra VALUES ('"
                    + miCompra.getCodCliente() + "', '"
                    + miCompra.getCodArticulo() + "', '"
                    + miCompra.getFecha() + "', '"
                    + miCompra.getUnidades() + "')");

            JOptionPane.showMessageDialog(null,
                    "Se ha registrado Exitosamente", "Informaci�n",
                    JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "No se Registro, verifique la consola para ver el error",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<CompraVo> buscarCompraConMatriz() {
        ArrayList<CompraVo> miCompra = new ArrayList<CompraVo>();
        CompraVo compra;
        try {
            Statement estatuto = conex.getConexion().createStatement();
            ResultSet rs = estatuto.executeQuery("SELECT * FROM compra ");

            while (rs.next()) {
                compra = new CompraVo();
                compra.setCodCliente(Integer.parseInt(rs.getString("clientes_codcliente")));
                compra.setCodArticulo(Integer.parseInt(rs.getString("articulos_codigo")));
                compra.setFecha(rs.getDate("fecha"));
                compra.setUnidades(Integer.parseInt(rs.getString("unidades")));

                miCompra.add(compra);
            }
            rs.close();
            estatuto.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
        return miCompra;
    }


}
