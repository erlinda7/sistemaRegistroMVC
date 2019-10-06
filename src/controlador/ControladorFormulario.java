package controlador;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.*;
import modelo.*;

public class ControladorFormulario implements ActionListener{
    private PersonaDAO personaDAO;
    private Formulario formulario;

    public ControladorFormulario(PersonaDAO persona, Formulario formulario) {
        this.personaDAO = persona;
        this.formulario = formulario;
        
        
                
        formulario.setTitle("Sistema con Modelo Vista Controlador");
        formulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formulario.setLocationRelativeTo(null);
        formulario.setVisible(true);
        
        bloquearUsuario();
        personaDAO.mostrarUsuario("");
        
        
        //evento
        this.formulario.btnNuevo.addActionListener(this);
        this.formulario.btnCancelar.addActionListener(this);
        this.formulario.btnRegistrar.addActionListener(this);
        
        this.formulario.btnBuscar.addActionListener(this);
        this.formulario.btnResfrescar.addActionListener(this);
        
        this.formulario.btnModificar.addActionListener(this);
        this.formulario.BtnActualizar.addActionListener(this);
        
        this.formulario.btnEliminar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(formulario.btnNuevo==e.getSource()){
           try {
               nuevoUsuario();
           } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "no se pudo crear nuevo usuario");
           }
       }else if(formulario.btnCancelar==e.getSource()){
           try {
               bloquearUsuario();
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo crear nuevo usuario");
           }
       }else if(formulario.btnRegistrar==e.getSource()){
           try {
               personaDAO.insertarUsuario();
               limpiarTabla(formulario.tabla);
               personaDAO.mostrarUsuario("");
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo insertar de usuario");
           }
       }else if(formulario.btnResfrescar==e.getSource()){
           try {
               limpiarTabla(formulario.tabla);
               personaDAO.mostrarUsuario("");
                  

           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo mostrar tabla");
           }
       }
       else if(formulario.btnBuscar==e.getSource()){
           try {
               limpiarTabla(formulario.tabla);
               personaDAO.mostrarUsuario(formulario.txtBuscar.getText());
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo mostrar usuario");
           }
       }
       else if(formulario.btnModificar==e.getSource()){
           try {
               nuevoUsuario(); //que desbloquear para modificar los texfields
               modificarUsuario();
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo modificar usuario");
           }
       }else if(formulario.BtnActualizar==e.getSource()){
           try {
               personaDAO.actualizarUsuario();
               limpiarTabla(formulario.tabla);
               personaDAO.mostrarUsuario("");
               bloquearUsuario();
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo actualizar usuario");
           }
       }
       else if(formulario.btnEliminar==e.getSource()){
           try {
                nuevoUsuario(); //que desbloquear para modificar los texfields
               personaDAO.eliminarUsuario();
               limpiarTabla(formulario.tabla);
               personaDAO.mostrarUsuario("");
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo eliminar usuario");
           }
       }
    }
    
    
    
    
    
   public void limpiarTabla(JTable tabla){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            int filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
   
   //----------------------
    public void nuevoUsuario() {
        limpiarUsuario();

        formulario.txtNombre.setEnabled(true); //para deshabilitar 
        formulario.txtApellido.setEnabled(true);
        formulario.txtTelefono.setEnabled(true);
        formulario.txtDomicilio.setEnabled(true);
        formulario.txtEdad.setEnabled(true);
        formulario.btnCancelar.setEnabled(true);
        formulario.btnRegistrar.setEnabled(true);
        formulario.BtnActualizar.setEnabled(false);
    }

    public void bloquearUsuario() {
        formulario.txtNombre.setEnabled(false); //para deshabilitar 
        formulario.txtApellido.setEnabled(false);
        formulario.txtTelefono.setEnabled(false);
        formulario.txtDomicilio.setEnabled(false);
        formulario.txtEdad.setEnabled(false);
        formulario.btnCancelar.setEnabled(false);
        formulario.btnRegistrar.setEnabled(false);
        formulario.BtnActualizar.setEnabled(false);

        limpiarUsuario();
    }

    public void limpiarUsuario() {
        formulario.txtNombre.setText(""); //para deshabilitar 
        formulario.txtApellido.setText("");
        formulario.txtTelefono.setText("");
        formulario.txtDomicilio.setText("");
        formulario.txtEdad.setText("");
        formulario.txtBuscar.setText("");
    }
        public void modificarUsuario() { //para recargar el formulario para modificar
        
        formulario.btnRegistrar.setEnabled(false);
        formulario.BtnActualizar.setEnabled(true);

        int fila = formulario.tabla.getSelectedRow(); //fila seleccionada
        if (fila >= 0) {
            formulario.txtBuscar.setText(formulario.tabla.getValueAt(fila, 0).toString());
            formulario.txtNombre.setText(formulario.tabla.getValueAt(fila, 1).toString());
            formulario.txtApellido.setText(formulario.tabla.getValueAt(fila, 2).toString());
            formulario.txtTelefono.setText(formulario.tabla.getValueAt(fila, 3).toString());
            formulario.txtDomicilio.setText(formulario.tabla.getValueAt(fila, 4).toString());
            formulario.txtEdad.setText(formulario.tabla.getValueAt(fila, 5).toString());

        } else {
            JOptionPane.showMessageDialog(formulario, "No se ha seleccionado una fila");
        }
    }
}
