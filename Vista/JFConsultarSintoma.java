/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.ArchivoPDF;
import Modelo.ConexionSQL;
import Modelo.IO;
import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Gerardo
 */
public class JFConsultarSintoma extends javax.swing.JFrame {

    /**
     * Creates new form JFConsultarSintoma
     */
    public JFConsultarSintoma() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tSintoma = new javax.swing.JTextField();
        bBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabla = new javax.swing.JTable();
        bRegresar = new javax.swing.JButton();
        bCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel1.setText("Consultar Planta por Sintomas");

        jLabel2.setText("Sintoma a Consultar: ");

        bBuscar.setText("Buscar ");

        tTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre Comun", "Nombre Cientifico"
            }
        ));
        tTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tTabla);

        bRegresar.setText("Regresar");

        bCerrar.setText("Cerrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(bBuscar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(bRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(bCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bRegresar)
                    .addComponent(bCerrar))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTablaMouseClicked
         ArchivoPDF archivo = new ArchivoPDF("Registro de Plantas Medicinales.pdf");
        int seleccion = tTabla.rowAtPoint(evt.getPoint());
        
        
        ConexionSQL conect = new ConexionSQL();
            Connection con = conect.conectar();
//            Image foto = null;
//            ImageIcon imagen = null;
            String SQL = "select * from plantas ";

            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                archivo.abrir();

                while (rs.next()) {

                    archivo.addParrafo("", "ID :" + rs.getInt("id") + ": " + "Nombre Comun: " + rs.getString("nombre_comun"));
                    archivo.addParrafo("", "Nombre Cientifico: " + rs.getString("nombre_cientifico"));
                    //archivo.addParrafo("", "Imagen: " + rs.getBlob("imagen"));
                    archivo.addParrafo("", "Descripción: " + rs.getString("descripcion"));
                    archivo.addParrafo("", "Sinonimos: " + rs.getString("sinonimos"));
                    archivo.addParrafo("", "Parte Util: " + rs.getString("parte_util"));
                    archivo.addParrafo("", "Utilización: " + rs.getString("utilizacion"));
                    archivo.addParrafo("", "Contraindicaciones: " + rs.getString("contraindicaciones"));
                    archivo.addParrafo("", "Forma de Empleo: " + rs.getString("forma_empleo") + "\n\n");

                }

            } catch (Exception e) {

                IO.imprimir("Error de conexion!");
            }
            archivo.mostrar();
            archivo.cerrar();
    }//GEN-LAST:event_tTablaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFConsultarSintoma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFConsultarSintoma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFConsultarSintoma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFConsultarSintoma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFConsultarSintoma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bBuscar;
    public javax.swing.JButton bCerrar;
    public javax.swing.JButton bRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField tSintoma;
    public javax.swing.JTable tTabla;
    // End of variables declaration//GEN-END:variables
}
