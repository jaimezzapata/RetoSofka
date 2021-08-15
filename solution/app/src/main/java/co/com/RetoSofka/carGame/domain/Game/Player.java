/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.carGame.domain.Game;

import java.awt.Color;

import co.com.RetoSofka.carGame.domain.IDS.Nombre;


public class Player {

    private Nombre nombre;
    private Color color;
    private Integer puntos;

    public Player(Nombre nombre, Color color, Integer puntos) {
        this.nombre = nombre;
        this.color = color;
        this.puntos = puntos;
    }

    public Nombre nombre() {
        return nombre;
    }

    public Color color() {
        return color;
    }
    
    public Integer puntos() {
        return puntos;
    }

    public void asignarPuntos(Integer puntos) {
        puntos = puntos;
    }

}
