package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import vo.ClienteVo;

import conexion.Conexion;


public class ClienteDao {
    private Conexion conex;

    public ClienteDao() {
        conex = new Conexion();
    }
    public void registrarCliente(ClienteVo miCliente) {
        try {
            Statement estatuto = conex.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO clientes VALUES ('"
                    + miCliente.getCodCliente() + "', '"
                    + miCliente.getNombreCliente() + "', '"
                    + miCliente.getApellidoCliente() + "', '"
                    + miCliente.getDireccionCliente() + "', '"
                    + miCliente.getLocalidadCliente() + "', '"
                    + miCliente.getProvinciaCliente() + "', '"
                    + miCliente.getCodPostalCliente() + "', '"
                    + miCliente.getTelCliente() + "', '"
                    + miCliente.getDniCliente() + "') ");
            JOptionPane.showMessageDialog(null,
                    "Se ha registrado Exitosamente", "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "No se Registro, verifique la consola para ver el error",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<ClienteVo> buscarClienteConMatriz() {
        ArrayList<ClienteVo> miListaClientes = new ArrayList<ClienteVo>();
        ClienteVo cliente;
        try {
            Statement estatuto = conex.getConexion().createStatement();
            ResultSet rs = estatuto.executeQuery("SELECT * FROM clientes ");

            while (rs.next()) {
                cliente = new ClienteVo();
                cliente.setCodCliente(Integer.parseInt(rs.getString("codcliente")));
                cliente.setNombreCliente(rs.getString("nombre"));
                cliente.setApellidoCliente(rs.getString("apellido"));
                cliente.setDireccionCliente(rs.getString("direccion"));
                cliente.setLocalidadCliente(rs.getString("localidad"));
                cliente.setProvinciaCliente(rs.getString("Provincia"));
                cliente.setCodPostalCliente(rs.getString("cod_postal"));
                cliente.setTelCliente(rs.getString("Telefono"));
                cliente.setDniCliente(rs.getString("DNI"));
                miListaClientes.add(cliente);
            }
            rs.close();
            estatuto.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
        return miListaClientes;
    }

}