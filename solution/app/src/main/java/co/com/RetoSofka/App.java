package co.com.RetoSofka;

import java.awt.Color;
import java.util.Scanner;
import java.util.UUID;

import co.com.RetoSofka.carGame.domain.Game.Game;
import co.com.RetoSofka.carGame.domain.IDS.IdPlayer;
import co.com.RetoSofka.carGame.domain.IDS.Nombre;

public class App 
{
    public static void main(String[] args) 
    {
        UUID id;
        int cantidadJugadores;
        String nombreJugador;

        Game nuevoJuego = new Game();

        //Datos jugadores para iniciar el juego 
        System.out.println("Iniciando un nuevo juego ...");

        //Consulta  cuántos jugadores se quieren crear y se crean dichos jugadores con nombres "jugador"+n
        Scanner in = new Scanner(System.in);        
        System.out.println("¿Cuántos jugadores desea crear?");
         while(!in.hasNextInt()) in.next();         
        cantidadJugadores = in.nextInt();

        for (int i = 0; i < cantidadJugadores; i++) 
        {
            id = UUID.randomUUID();
            IdPlayer jugadorId = new IdPlayer(id);
            nombreJugador = "jugador" + (i + 1);
            Nombre nombre = new Nombre(nombreJugador);
            nuevoJuego.crearJugador(jugadorId, nombre, Color.yellow);
        }
        // Crear pistas
        nuevoJuego.crearPistas();

        // Iniciar el Juego
        nuevoJuego.iniciarJuego();  
    }
}
