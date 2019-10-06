package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.*;

public class PersonaDAO {

    Formulario form;
    ConexionBBDD conectaBBDD = new ConexionBBDD();
    Connection miConexion;
    
    PreparedStatement ps;

    public PersonaDAO(Formulario form) {
        this.form = form;
        miConexion = conectaBBDD.Conexion();
         
    }


    public void insertarUsuario() {
        try {
            String nombre = form.txtNombre.getText();
            String apellido = form.txtApellido.getText();
            String telefono = form.txtTelefono.getText();
            String domicilio = form.txtDomicilio.getText();
            String edad = form.txtEdad.getText();

            if (!nombre.equals("") && !apellido.equals("") && !telefono.equals("") && !domicilio.equals("") && !edad.equals("")) {

                String consultaInsertar = "INSERT INTO USUARIOS(NOMBRE,APELLIDO,TELEFONO,DOMICILIO,EDAD)"
                        + " VALUES(?,?,?,?,?)";
                ps = miConexion.prepareStatement(consultaInsertar);

                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, telefono);
                ps.setString(4, domicilio);
                ps.setInt(5, Integer.parseInt(edad));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(form, "insercion Exitosa");
            } else {
                JOptionPane.showMessageDialog(form, "Error , hay campos vacios");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(form, "insercion Fallida!!");
        }
    }

    public void mostrarUsuario(String atributo) {
        String consultaUsuarios;
        if (atributo.equals("")) {
            consultaUsuarios = "SELECT * FROM USUARIOS";
        } else {
            consultaUsuarios = "SELECT * FROM USUARIOS WHERE ID='" + atributo + "'";
        }
        
        List<Persona> listaP=new ArrayList<>();

        try {
            Statement statement = miConexion.createStatement();
            ResultSet rs = statement.executeQuery(consultaUsuarios);

            while (rs.next()) {
                Persona persona=new Persona();
                
                persona.setId(rs.getInt(1));
                persona.setNom(rs.getString(2));
                persona.setApell(rs.getString(3));
                persona.setTelf(rs.getString(4));
                persona.setDom(rs.getString(5));
                persona.setEdad(rs.getInt(6));

                listaP.add(persona);
            }
            
            DefaultTableModel tablaModel = new DefaultTableModel();
            tablaModel=(DefaultTableModel) form.tabla.getModel();
            
            Object obj[]=new Object[6];
            
            for (int i = 0; i < listaP.size(); i++) {
                obj[0]=listaP.get(i).getId();
                obj[1]=listaP.get(i).getNom();
                obj[2]=listaP.get(i).getApell();
                obj[3]=listaP.get(i).getTelf();
                obj[4]=listaP.get(i).getDom();
                obj[5]=listaP.get(i).getEdad();  
                
                tablaModel.addRow(obj);
            }

            form.tabla.setModel(tablaModel);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(form, "No se pudo mostrar, hay un error");
        }

    }

    public void actualizarUsuario() {
        try {
            String id = form.txtBuscar.getText();
            String nombre = form.txtNombre.getText();
            String apellido = form.txtApellido.getText();
            String telefono = form.txtTelefono.getText();
            String domicilio = form.txtDomicilio.getText();
            String edad = form.txtEdad.getText();

            if (!nombre.equals("") && !apellido.equals("") && !telefono.equals("") && !domicilio.equals("") && !edad.equals("")) {
                String consultaActulizar = "UPDATE USUARIOS SET NOMBRE=?, APELLIDO=?, TELEFONO=?, DOMICILIO=?, EDAD=? WHERE ID=?";
                ps= miConexion.prepareStatement(consultaActulizar);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, telefono);
                ps.setString(4, domicilio);
                ps.setInt(5, Integer.parseInt(edad));
                ps.setInt(6, Integer.parseInt(id));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(form, "Usuario modificado");
                mostrarUsuario("");
                
            } else {
                JOptionPane.showMessageDialog(form, "Error , hay campos vacios");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(form, "Error " + e);
        }

    }

    public void eliminarUsuario() {
       
        form.btnRegistrar.setEnabled(false);
        form.BtnActualizar.setEnabled(true);

        int fila = form.tabla.getSelectedRow(); //fila seleccionada
        if (fila >= 0) {
            String id = form.tabla.getValueAt(fila, 0).toString();
            try {

                String consultaEliminar = "DELETE FROM USUARIOS WHERE ID=?";
                ps= miConexion.prepareStatement(consultaEliminar);
                ps.setInt(1, Integer.parseInt(id));
                ps.execute();
                JOptionPane.showMessageDialog(null, "usuario eliminado");
                mostrarUsuario("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(form, "No se pudo eliminar");
            }
        } else {
            JOptionPane.showMessageDialog(form, "No se ha seleccionado una fila");
        }
    }  
}
