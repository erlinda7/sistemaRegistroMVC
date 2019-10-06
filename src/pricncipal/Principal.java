/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pricncipal;

import vista.*;
import modelo.*;
import controlador.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Principal {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
       
        Formulario formulario = new Formulario();
        PersonaDAO persona = new PersonaDAO(formulario);
        ControladorFormulario controlador = new ControladorFormulario(persona, formulario);
    
        
    }
}
