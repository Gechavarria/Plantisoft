/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ConexionSQL;
import Modelo.IO;
import Modelo.Planta;
import Vista.JFRegistroPlanta;
import Vista.JFTabla;
import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

/**
 *
 * @author Gerardo
 */
public class ControlJFTabla implements ActionListener{

    JFTabla control;
    Planta consulta;
    JFRegistroPlanta pregunta;
    DefaultTableModel modelo = new DefaultTableModel();

    @SuppressWarnings("LeakingThisInConstructor")
    ControlJFTabla() {
        control = new JFTabla();
        control.bCerrar.addActionListener(this);
        control.bMostrar.addActionListener(this);
        control.bRegresar.addActionListener(this);

        control.setVisible(true);
        control.setLocationRelativeTo(null);
        modelo.addColumn("ID");
        modelo.addColumn("Nombre Comun");
        modelo.addColumn("Nombre Cientifico");
        

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == control.bMostrar) {

            ConexionSQL conect = new ConexionSQL();
            Connection con = conect.conectar();

            String SQL = "select * from plantas ";

            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);

                while (rs.next()) {

                    modelo.addRow(new String[]{"" + rs.getString("id"), "" + rs.getString("nombre_comun"), "" + rs.getString("nombre_cientifico")
                            
                    });
                    control.tTable.setModel(modelo);
                }

            } catch (Exception e) {

                IO.imprimir("Eror de conexion" + e.getMessage());
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
