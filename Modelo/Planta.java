package Modelo;

import java.io.File;
import java.io.Serializable;

public class Planta implements Serializable {

    private String nombreComun;
    private String nombreCientifico;
    private File imagen;
    private String ruta;
    private String descripcion;
    private String sinonimos;
    private String parteUtil;
    private String utilizacion;
    private String contraindicaciones;
    private String formaEmpleo;

    public Planta(String nombreComun, String nombreCientifico, File imagen, String ruta, String descripcion, String sinonimos, String parteUtil, String utilizacion, String contraindicaciones, String formaEmpleo) {
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.imagen = imagen;
        this.ruta = ruta;
        this.descripcion = descripcion;
        this.sinonimos = sinonimos;
        this.parteUtil = parteUtil;
        this.utilizacion = utilizacion;
        this.contraindicaciones = contraindicaciones;
        this.formaEmpleo = formaEmpleo;
    }

  

    public Planta() {
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSinonimos() {
        return sinonimos;
    }

    public void setSinonimos(String sinonimos) {
        this.sinonimos = sinonimos;
    }

    public String getParteUtil() {
        return parteUtil;
    }

    public void setParteUtil(String parteUtil) {
        this.parteUtil = parteUtil;
    }

    public String getUtilizacion() {
        return utilizacion;
    }

    public void setUtilizacion(String utilizacion) {
        this.utilizacion = utilizacion;
    }

    public String getContraindicaciones() {
        return contraindicaciones;
    }

    public void setContraindicaciones(String contraindicaciones) {
        this.contraindicaciones = contraindicaciones;
    }

    public String getFormaEmpleo() {
        return formaEmpleo;
    }

    public void setFormaEmpleo(String formaEmpleo) {
        this.formaEmpleo = formaEmpleo;
    }
    
    
}