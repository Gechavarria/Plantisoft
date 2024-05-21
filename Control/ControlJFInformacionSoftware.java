/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Vista.JFInformacionSoftware;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gerardo
 */
public class ControlJFInformacionSoftware implements ActionListener {

    JFInformacionSoftware control;

    ControlJFInformacionSoftware() {

        control = new JFInformacionSoftware();
        control.bRegresar.addActionListener(this);
        control.setVisible(true);
        control.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == control.bRegresar) {
            control.dispose();
        }

    }

}
