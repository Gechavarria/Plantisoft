/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.ConexionSQL;
import Modelo.IO;
import Modelo.Persistencia;
import Modelo.Planta;
import Vista.JFNavegador;
import Vista.JFRegistroPlanta;
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import com.mysql.jdbc.Connection;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Gerardo
 */
public class ControlJFRegistroPlanta implements ActionListener {

    JFRegistroPlanta control;
    Planta plantica = new Planta();
    File archi;
    JFNavegador buscador = new JFNavegador();
    Persistencia archivo = new Persistencia("", "Registro de plantas.dat");
    Planta[] usu, usuNuevo;

    @SuppressWarnings("LeakingThisInConstructor")
    public ControlJFRegistroPlanta() {

        control = new JFRegistroPlanta();
        control.tNombreComun.addActionListener(this);
        control.tNombreCientifico.addActionListener(this);
        control.tRuta.addActionListener(this);

        control.bSeleccionarImagen.addActionListener(this);
        control.tSinonimos.addActionListener(this);
        control.tParteUtil.addActionListener(this);
        control.tUtilizacion.addActionListener(this);
        control.tContraindicaciones.addActionListener(this);
        control.tFormaEmpleo.addActionListener(this);
        control.bNuevo.addActionListener(this);
        control.bGuardarMYSQL.addActionListener(this);
        control.bCerrar.addActionListener(this);
        control.bRegresar.addActionListener(this);
        control.bLimpiar.addActionListener(this);
        control.bGuardarTXT.addActionListener(this);
        control.setVisible(true);
        control.setLocationRelativeTo(null);

        control.tNombreComun.setEnabled(false);
        control.tNombreCientifico.setEnabled(false);
        control.bSeleccionarImagen.setEnabled(false);
        control.tSinonimos.setEnabled(false);
        control.tParteUtil.setEnabled(false);
        control.tUtilizacion.setEnabled(false);
        control.tContraindicaciones.setEnabled(false);
        control.tFormaEmpleo.setEnabled(false);
        control.bGuardarMYSQL.setEnabled(false);
        control.bGuardarTXT.setEnabled(false);
        control.bLimpiar.setEnabled(false);

        SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
        SpellChecker.registerDictionaries(null, null);
        SpellChecker.register(control.tNombreComun);
        SpellChecker.register(control.tNombreCientifico);
        SpellChecker.register(control.tDescripcion);
        SpellChecker.register(control.tSinonimos);
        SpellChecker.register(control.tContraindicaciones);
        SpellChecker.register(control.tParteUtil);
        SpellChecker.register(control.tUtilizacion);
        SpellChecker.register(control.tFormaEmpleo);
           

    }

    public void desbloquear() {
        control.tNombreComun.setEnabled(true);
        control.tNombreCientifico.setEnabled(true);
        control.bSeleccionarImagen.setEnabled(true);
        control.tSinonimos.setEnabled(true);
        control.tParteUtil.setEnabled(true);
        control.tUtilizacion.setEnabled(true);
        control.tContraindicaciones.setEnabled(true);
        control.tFormaEmpleo.setEnabled(true);
        control.bGuardarMYSQL.setEnabled(true);
        control.bGuardarTXT.setEnabled(true);
        control.bLimpiar.setEnabled(true);

    }

    public void limpiar() {
        control.tNombreComun.setText("");
        control.tNombreCientifico.setText("");
        control.lImagen.setText(null);
        control.tSinonimos.setText("");
        control.tParteUtil.setText("");
        control.tUtilizacion.setText("");
        control.tContraindicaciones.setText("");
        control.tFormaEmpleo.setText("");

    }

    public void insertarMYSQL() {

        ConexionSQL conect = new ConexionSQL();
        Connection con = conect.conectar();

        plantica.setNombreComun(String.valueOf(control.tNombreComun.getText()));
        plantica.setNombreCientifico(String.valueOf(control.tNombreCientifico.getText()));
        plantica.setDescripcion(String.valueOf(control.tDescripcion.getText()));
        plantica.setSinonimos(String.valueOf(control.tSinonimos.getText()));
        plantica.setParteUtil(String.valueOf(control.tParteUtil.getText()));
        plantica.setUtilizacion(String.valueOf(control.tUtilizacion.getText()));
        plantica.setContraindicaciones(String.valueOf(control.tContraindicaciones.getText()));
        plantica.setFormaEmpleo(String.valueOf(control.tFormaEmpleo.getText()));

        String SQL = "insert into plantas(nombre_comun,nombre_cientifico,imagen,descripcion,sinonimos,parte_util,utilizacion,contraindicaciones,forma_empleo)values(?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, control.tNombreComun.getText());
            pst.setString(2, control.tNombreCientifico.getText());
            pst.setString(3, control.tRuta.getText());
            pst.setString(4, control.tDescripcion.getText());
            pst.setString(5, control.tSinonimos.getText());
            pst.setString(6, control.tParteUtil.getText());

            pst.setString(7, control.tUtilizacion.getText());

            pst.setString(8, control.tContraindicaciones.getText());
            pst.setString(9, control.tFormaEmpleo.getText());

            pst.executeUpdate();
            IO.imprimir("Registro Exitoso");

        } catch (Exception e) {

            IO.imprimir("Error de Registro");
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == control.bNuevo) {

            desbloquear();
        }

        if (ae.getSource() == control.bGuardarMYSQL) {

            insertarMYSQL();
        }

        if (ae.getSource() == control.bGuardarTXT) {

            if (!archivo.existe()) {
                System.out.println("Ingresando primer registro...");
                usuNuevo = new Planta[1];
                usuNuevo[0] = new Planta();
                usuNuevo[0].setNombreComun(control.tNombreComun.getText());
                usuNuevo[0].setNombreCientifico(control.tNombreCientifico.getText());
                usuNuevo[0].setImagen(archi);
                usuNuevo[0].setDescripcion(control.tDescripcion.getText());
                usuNuevo[0].setRuta(control.tRuta.getText());
                usuNuevo[0].setParteUtil(control.tParteUtil.getText());
                usuNuevo[0].setSinonimos(control.tSinonimos.getText());
                usuNuevo[0].setContraindicaciones(control.tContraindicaciones.getText());
                usuNuevo[0].setUtilizacion(control.tUtilizacion.getText());
                usuNuevo[0].setFormaEmpleo(control.tFormaEmpleo.getText());
                IO.imprimir("El Archivo se Guardo Correctamente");

            } else {
                archivo.abrirLectura();
                usu = (Planta[]) archivo.cargar();
                archivo.cerrarLectura();
                usuNuevo = new Planta[usu.length + 1];

                for (int i = 0; i < usu.length; i = i + 1) {
                    usuNuevo[i] = new Planta();
                    usuNuevo[i] = usu[i];

                }
                usuNuevo[usu.length] = new Planta();

                usuNuevo[usu.length].setNombreComun(control.tNombreComun.getText());
                usuNuevo[usu.length].setNombreCientifico(control.tNombreCientifico.getText());
                usuNuevo[usu.length].setImagen(archi);
                usuNuevo[usu.length].setDescripcion(control.tDescripcion.getText());
                usuNuevo[usu.length].setRuta(control.tRuta.getText());
                usuNuevo[usu.length].setParteUtil(control.tParteUtil.getText());
                usuNuevo[usu.length].setSinonimos(control.tSinonimos.getText());
                usuNuevo[usu.length].setContraindicaciones(control.tContraindicaciones.getText());
                usuNuevo[usu.length].setUtilizacion(control.tUtilizacion.getText());
                usuNuevo[usu.length].setFormaEmpleo(control.tFormaEmpleo.getText());
                IO.imprimir("El Archivo se Guardo Correctamente");

            }
            archivo.abrirEscritura();
            archivo.guardar(usuNuevo);

            archivo.cerrarEscritura();

        }

        if (ae.getSource() == control.bSeleccionarImagen) {

            int resultado;

            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG, y GIF", "jpg", "png", "gif");

            buscador.JFCNavegador.setFileFilter(filtro);
            resultado = buscador.JFCNavegador.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == resultado) {
                archi = buscador.JFCNavegador.getSelectedFile();
                control.tRuta.setText(archi.getAbsolutePath());
            }

                try {

                    ImageIcon imagen = new ImageIcon(archi.toString());
                    Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(control.lImagen.getWidth(), control.lImagen.getHeight(), Image.SCALE_DEFAULT));
                    control.lImagen.setIcon(icono);
                } catch (Exception e) {
                    IO.imprimir("Error al abrrir el archivo");
                }
            
        }

        if (ae.getSource() == control.bLimpiar) {

            limpiar();

        }

        if (ae.getSource() == control.bCerrar) {

            System.exit(0);
        }

        if (ae.getSource() == control.bRegresar) {
            control.dispose();
        }

    }
}
