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
        
        personaDAO.iniciarPersona();
        
        
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
               personaDAO.nuevoUsuario();
           } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "no se pudo crear nuevo usuario");
           }
       }else if(formulario.btnCancelar==e.getSource()){
           try {
               personaDAO.bloquearUsuario();
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo crear nuevo usuario");
           }
       }else if(formulario.btnRegistrar==e.getSource()){
           try {
               personaDAO.insertarUsuario();
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
               personaDAO.modificarUsuario();
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo modificar usuario");
           }
       }else if(formulario.BtnActualizar==e.getSource()){
           try {
               personaDAO.actualizarUsuario();
           } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "no se pudo actualizar usuario");
           }
       }
       else if(formulario.btnEliminar==e.getSource()){
           try {
               personaDAO.eliminarUsuario();
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
    
}
