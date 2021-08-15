/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.carGame.domain.Lane;

import co.com.RetoSofka.carGame.domain.IDS.IdCarro;
import co.com.RetoSofka.carGame.domain.IDS.IdJuego;
import co.com.RetoSofka.carGame.domain.Lane.values.Posicion;


public class Lane {

    protected IdCarro carroId;
    protected IdJuego juegoId;
    protected Posicion posicion;
    protected Integer metros;
    protected Boolean desplazamientoFinal;

    public Lane(IdCarro carroId, IdJuego juegoId, Posicion posicion, Integer metros, Boolean desplazamientoFinal) 
    {
        this.carroId = carroId;
        this.juegoId = juegoId;
        this.posicion = posicion;
        this.metros = metros;
        this.desplazamientoFinal = desplazamientoFinal;
    }

    public void alcanzarLaMeta() 
    {
        if (posicionActual() >= posicionDeseada()) 
        {
            desplazamientoFinal = true;
        }
    }

    public void moverCarro(Posicion posicion, Integer cantidad) {
        this.posicion = posicion;
        posicion.setActual(posicion.actual() + cantidad);
        alcanzarLaMeta();
    }

    public Integer metros() 
    {
        return metros;
    }

    public Posicion posicion() 
    {
        return posicion;
    }

    public Integer posicionActual() 
    {
        return posicion.actual();
    }

    public Integer posicionDeseada() 
    {
        return posicion.meta();
    }

    public Boolean desplazamientoFinal() 
    {
        return desplazamientoFinal;
    }
}
