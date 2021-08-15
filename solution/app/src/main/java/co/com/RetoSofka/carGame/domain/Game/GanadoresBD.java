/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.carGame.domain.Game;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class GanadoresBD implements Serializable 
{
    @Id
    private int id;
    private String nombre;
    private int vecesPrimero;
    private int vecesSegundo;
    private int vecesTercero;

    public GanadoresBD() 
    {
    }

    public GanadoresBD(int id, String nombre, int vecesPrimero, int vecesSegundo, int vecesTercero) 
    {
        this.id = id;
        this.nombre = nombre;
        this.vecesPrimero = vecesPrimero;
        this.vecesSegundo = vecesSegundo;
        this.vecesTercero = vecesTercero;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public int getVecesPrimero() 
    {
        return vecesPrimero;
    }

    public void setVecesPrimero(int vecesPrimero) 
    {
        this.vecesPrimero = vecesPrimero;
    }

    public int getVecesSegundo() 
    {
        return vecesSegundo;
    }

    public void setVecesSegundo(int vecesSegundo) 
    {
        this.vecesSegundo = vecesSegundo;
    }

    public int getVecesTercero() 
    {
        return vecesTercero;
    }

    public void setVecesTercero(int vecesTercero) 
    {
        this.vecesTercero = vecesTercero;
    }

}
