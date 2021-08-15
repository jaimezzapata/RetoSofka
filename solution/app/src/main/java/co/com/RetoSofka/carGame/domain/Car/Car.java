/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.carGame.domain.Car;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import co.com.RetoSofka.carGame.domain.IDS.IdCarro;
import co.com.RetoSofka.carGame.domain.IDS.IdJuego;


public class Car 
{
    protected Conductor conductor;
    protected Integer distancia;
    protected Color color;
    protected IdJuego juegoId;
    private final Map<IdCarro, Conductor> carros = new HashMap<>();

    public Car() 
    {
    }

    public Car(Conductor conductor, Integer distancia, Color color, IdJuego juegoId) 
    {
        this.conductor = conductor;
        this.distancia = distancia;
        this.color = color;
        this.juegoId = juegoId;
    }

    public void asignarConductor(IdCarro carroId, Conductor conductor) 
    {
        carros.put(carroId, conductor);
    }

    public Map<IdCarro, Conductor> carros() 
    {
        return carros;
    }

    public void setDistancia(Integer distancia) 
    {
        this.distancia = distancia;
    }

    public Integer numeroCarros() 
    {
        return carros.size();
    }

    public Conductor conductor() 
    {
        return conductor;
    }

    public Integer distancia() 
    {
        return distancia;
    }
}
