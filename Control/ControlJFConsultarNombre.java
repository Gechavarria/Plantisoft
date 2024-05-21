/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ArchivoPDF;
import Modelo.ConexionSQL;
import Modelo.IO;
import Modelo.Planta;
import Vista.JFConsultarNombre;
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Gerardo
 */
public class ControlJFConsultarNombre implements ActionListener {

    JFConsultarNombre control;
    Planta plantica = new Planta();
    ArchivoPDF archivo = new ArchivoPDF("Consulta por Nombre.pdf");

    @SuppressWarnings("LeakingThisInConstructor")
    ControlJFConsultarNombre() {
        control = new JFConsultarNombre();

        control.tNombreComun.addActionListener(this);
        control.tNombreCientifico.addActionListener(this);
        control.bConsultar.addActionListener(this);
        control.bRegresar.addActionListener(this);
        control.bCerrar.addActionListener(this);
        control.setVisible(true);
        control.setLocationRelativeTo(null);

        SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
        SpellChecker.registerDictionaries(null, null);
        SpellChecker.register(control.tNombreComun);
        SpellChecker.register(control.tNombreCientifico);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == control.bConsultar) {

            ConexionSQL conect = new ConexionSQL();
            Connection con = conect.conectar();

            String nombre_comun = control.tNombreComun.getText();
            String nombre_cientifico = control.tNombreCientifico.getText();

            String SQL = "select * from plantas where  nombre_comun= '" + nombre_comun + "'  or nombre_cientifico='" + nombre_cientifico + "' ";

            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                boolean consulta = false;
                archivo.abrir();
                archivo.addParrafo("Consulta de plantas por Nombre\n", "la planta que buscas es la siguiente\n");
                while (rs.next()) {
                    plantica.setNombreComun(control.tNombreComun.getText());
                    plantica.setNombreCientifico(control.tNombreCientifico.getText());
                    if (plantica.getNombreComun().equalsIgnoreCase(control.tNombreComun.getText()) || plantica.getNombreCientifico().equalsIgnoreCase(control.tNombreCientifico.getText())) {

                        archivo.addParrafo("", "ID :" + rs.getInt("id") + ": " + "Nombre Comun: " + rs.getString("nombre_comun"));
                        archivo.addParrafo("", "Nombre Cientifico: " + rs.getString("nombre_cientifico"));
                        archivo.addImagen(rs.getString("imagen"));
                        archivo.addParrafo("", "Descripción: " + rs.getString("descripcion"));
                        archivo.addParrafo("", "Sinonimos: " + rs.getString("sinonimos"));
                        archivo.addParrafo("", "Parte Util: " + rs.getString("parte_util"));
                        archivo.addParrafo("", "Utilización: " + rs.getString("utilizacion"));
                        archivo.addParrafo("", "Contraindicaciones: " + rs.getString("contraindicaciones"));
                        archivo.addParrafo("", "Forma de Empleo: " + rs.getString("forma_empleo") + "\n\n");

                        consulta = true;

                    }

                    archivo.mostrar();
                    archivo.cerrar();
                }
                if (consulta) {
                    IO.imprimir("La planta que buscas se encontró con exito");

                } else {
                    IO.imprimir("El nombre que buscas no registra en la base");
                }

            } catch (Exception e) {

                IO.imprimir("Error de conexion!");
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
