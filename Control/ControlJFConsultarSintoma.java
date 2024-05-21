/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;


import Modelo.ConexionSQL;
import Modelo.IO;
import Modelo.Planta;
import Vista.JFConsultarSintoma;
import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gerardo
 */
public class ControlJFConsultarSintoma implements ActionListener {

    JFConsultarSintoma control;
    Planta plantica;
    DefaultTableModel modelo = new DefaultTableModel();

    @SuppressWarnings("LeakingThisInConstructor")
    ControlJFConsultarSintoma() {
        control = new JFConsultarSintoma();
        control.tSintoma.addActionListener(this);
        control.bBuscar.addActionListener(this);
        control.bRegresar.addActionListener(this);
        control.bCerrar.addActionListener(this);
        modelo.addColumn("ID");
        modelo.addColumn("Nombre Comun");
        modelo.addColumn("Nombre Cientifico");
        control.setVisible(true);
        control.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == control.bBuscar) {

            ConexionSQL conect = new ConexionSQL();
            Connection con = conect.conectar();

            String utilizacion = control.tSintoma.getText();

            String SQL = "select * from plantas where  utilizacion like utilizacion";

            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                boolean consulta = false;

                while (rs.next()) {

                    String x = rs.getString("utilizacion");
                    String cadena[] = x.split(", ");

                    for (int i = 0; i < cadena.length; i++) {
                        if (cadena[i].equalsIgnoreCase(utilizacion)) {

                            //archivo.addParrafo("", "" + rs.getInt("id") + ":" + rs.getString("nombre_comun") + "\n");
                            modelo.addRow(new String[]{"" + rs.getInt("id"), "" + rs.getString("nombre_comun"), "" + rs.getString("nombre_cientifico")
                            });
                            control.tTabla.setModel(modelo);
                             consulta = true;
                        }
                    }
                }

               

                if (consulta == true) {
                    IO.imprimir("Con las Siguientes plantas puedes tratar tus malestares");

                } else {
                    IO.imprimir("El nombre que buscas no registra en la base");
                }

            } catch (Exception e) {

                IO.imprimir("Error de conexion!");
            }

        }
        if(ae.getSource()== control.bRegresar){
            control.dispose();
        }
        if(ae.getSource()== control.bCerrar){
            System.exit(0);
        }

    }

}
