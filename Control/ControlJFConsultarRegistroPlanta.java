/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.IO;
import Modelo.Persistencia;
import Modelo.Planta;
import Vista.JFConsultarRegistroPlanta;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Gerardo
 */
public class ControlJFConsultarRegistroPlanta implements ActionListener {

    JFConsultarRegistroPlanta control;
    Planta[] usu, usuNuevo;
    Persistencia archivo = new Persistencia("", "Registro de plantas.dat");

    @SuppressWarnings("LeakingThisInConstructor")

    public ControlJFConsultarRegistroPlanta() {

        control = new JFConsultarRegistroPlanta();
        control.tRuta.addActionListener(this);
        control.bMostrar.addActionListener(this);
        control.bRegresar.addActionListener(this);
        control.bCerrar.addActionListener(this);
        control.setVisible(true);
        control.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == control.bMostrar) {
            int a = 0;

            if (archivo.existe()) {  // Sólo hacer esto si el archivo contiene al menos un dato

                archivo.abrirLectura();
                usu = (Planta[]) archivo.cargar();
                archivo.cerrarLectura();
                System.out.println("\n\nCONTENIDO DE ARCHIVO");

                for (int i = 0; i < usu.length; i = i + 1) {

                    ImageIcon ImgIcon = new ImageIcon(usu[i].getImagen().toString());
                    Icon icono = new ImageIcon(ImgIcon.getImage().getScaledInstance(control.lImagen.getWidth(), control.lImagen.getHeight(), Image.SCALE_DEFAULT));

                    control.lImagen.setIcon(icono);
                    control.tInformacion.setText("Nombre Comun: " + usu[i].getNombreComun() + "\n"
                            + "Nombre Cientifico: " + usu[i].getNombreCientifico() + "\n" + "Descripcion: " + usu[i].getDescripcion() + "\n"
                            + "Parte Util: " + usu[i].getParteUtil() + "\n" + "Sinonimos: " + usu[i].getSinonimos() + "\n"
                            + "Contraindicaciones: " + usu[i].getContraindicaciones() + "\n" + "Utilización: " + usu[i].getUtilizacion() + "\n"
                            + "Forma de Empleo: " + usu[i].getFormaEmpleo() + "\n");
                    control.tRuta.setText(usu[i].getRuta());

                    a = JOptionPane.showConfirmDialog(null, "Siguiente");
                    if (a == 1) {
                        IO.imprimir("Plantas Visualizadas con exito");
                        break;

                    }
                }
            }

        }
        if (ae.getSource() == control.bRegresar) {
            control.dispose();
        }
        if (ae.getSource() == control.bCerrar) {
            System.exit(0);
        }

    }

}
