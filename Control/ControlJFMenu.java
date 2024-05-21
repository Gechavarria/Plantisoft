/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ArchivoPDF;
import Modelo.ConexionSQL;
import Modelo.IO;
import Vista.JFMenu;
import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Gerardo
 */
public class ControlJFMenu implements ActionListener {

    JFMenu control;
    ArchivoPDF archivo = new ArchivoPDF("Registro de Plantas Medicinales.pdf");

    @SuppressWarnings("LeakingThisInConstructor")
    ControlJFMenu() {

        control = new JFMenu();
        control.bRegistrarPlanta.addActionListener(this);
        control.bConsultarNombre.addActionListener(this);
        control.bConsultarSintomas.addActionListener(this);
        control.bAcerca.addActionListener(this);
        control.bCerrar.addActionListener(this);
        control.bConsultaTable.addActionListener(this);
        control.bMostrar.addActionListener(this);
        control.bConsultarRegistro.addActionListener(this);
        control.setVisible(true);
        control.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == control.bRegistrarPlanta) {

            ControlJFRegistroPlanta ver = new ControlJFRegistroPlanta();
        }

        if (ae.getSource() == control.bConsultarNombre) {

            ControlJFConsultarNombre ver = new ControlJFConsultarNombre();
        }

        if (ae.getSource() == control.bConsultaTable) {

            ControlJFTabla ver = new ControlJFTabla();
        }
        if (ae.getSource() == control.bConsultarSintomas) {

            ControlJFConsultarSintoma ver = new ControlJFConsultarSintoma();
        }
        if (ae.getSource() == control.bMostrar) {

            ConexionSQL conect = new ConexionSQL();
            Connection con = conect.conectar();

            String SQL = "select * from plantas ";

            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                archivo.abrir();
                archivo.addParrafo("Listado de Plantas Medicinales", "" + "\n");
                while (rs.next()) {
                    archivo.addParrafo("", "ID: " + rs.getString("id")+"\n");
                    archivo.addParrafo("", "Nombre Comun: " + rs.getString("nombre_comun") +"\n\n") ;
                    archivo.addParrafo("", "Nombre Cientifico: " + rs.getString("nombre_cientifico") +"\n\n");
                    archivo.addImagen(rs.getString("imagen"));
                    archivo.addParrafo("", "Descripción: " + rs.getString("descripcion") +"\n\n");
                    archivo.addParrafo("", "Sinonimos: " + rs.getString("sinonimos") +"\n\n");
                    archivo.addParrafo("", "Parte Util: " + rs.getString("parte_util") +"\n\n");
                    archivo.addParrafo("", "Utilización: " + rs.getString("utilizacion") +"\n\n");
                    archivo.addParrafo("", "Contraindicaciones: " + rs.getString("contraindicaciones") +"\n\n");
                    archivo.addParrafo("", "Forma de Empleo: " + rs.getString("forma_empleo") + "\n");
                    archivo.addParrafo("", "**----------------------------------------------------------------------------------------------------------------**\n");

                }

            } catch (Exception e) {

                IO.imprimir("Error de conexion!");
            }
            archivo.mostrar();
            archivo.cerrar();

        }
        if (ae.getSource() == control.bCerrar) {
            System.exit(0);
        }
        if (ae.getSource() == control.bConsultarRegistro) {
            ControlJFConsultarRegistroPlanta ver = new ControlJFConsultarRegistroPlanta();

        }
        if(ae.getSource()== control.bAcerca){
            ControlJFInformacionSoftware ver = new ControlJFInformacionSoftware();
        }

    }
}
