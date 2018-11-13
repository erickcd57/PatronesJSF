/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo;

import com.mysql.jdbc.PreparedStatement;
import datos.AccesoDatosSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diaz
 */
@ManagedBean
@RequestScoped
public class PeliculaServicio {
    private AccesoDatosSingleton datos = AccesoDatosSingleton.getInstance();
    private Connection conn = datos.getConnection();
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

    
    @ManagedProperty(value="#{pelicula}")
    private Pelicula pelicula = new Pelicula();

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    public List<Pelicula> getPeliculas() {
        List<Pelicula> lista = new ArrayList<>();
        Pelicula pelicula = null;
        String sql = "select id_pelicula, nombre, sinapsis from pelicula";
        PreparedStatement pstm = null;
        try {
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt(1));
                pelicula.setNombre(rs.getString(2));
                pelicula.setSinapsis(rs.getString(3));
                lista.add(pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void insertPelicula() {
        String sql = "insert into pelicula ( nombre, sinapsis) values (?,?)";
        PreparedStatement pstm = null;
        try {
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, pelicula.getNombre());
            pstm.setString(2, pelicula.getSinapsis());
            pstm.executeUpdate();
            pelicula.setNombre("");
            pelicula.setSinapsis("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String editPelicula(Pelicula pelicula) {
        sessionMap.put("pelicula", pelicula);
        return "/editar.xhtml?faces-redirect=true";
    }
    
    public String updatePelicula(Pelicula pelicula1) {
        String sql = "update pelicula set nombre = ? , sinapsis = ? where id_pelicula = ?";
        PreparedStatement pstm = null;
        try {
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, pelicula1.getNombre());
            pstm.setString(2, pelicula1.getSinapsis());
            pstm.setInt(3, pelicula1.getIdPelicula());
            pstm.executeUpdate();
            pelicula.setNombre("");
            pelicula.setSinapsis("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    public void deletePelicula(int id) {
        String sql = "delete from pelicula where id_pelicula = ?";
        PreparedStatement pstm = null;
        try {
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
