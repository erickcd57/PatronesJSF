/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Diaz
 */
@ManagedBean
@RequestScoped
public class Pelicula {
    private int idPelicula;
    
    private String nombre;
    
    private String sinapsis;
    
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinapsis() {
        return sinapsis;
    }

    public void setSinapsis(String sinapsis) {
        this.sinapsis = sinapsis;
    }

    @Override
    public String toString() {
        return "Juego{" + "idPelicula=" + idPelicula + ", nombre=" + nombre + ", sinapsis=" + sinapsis + '}';
    }
    
}
