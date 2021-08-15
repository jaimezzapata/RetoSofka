/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */

package co.com.RetoSofka.carGame.domain.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import co.com.RetoSofka.carGame.domain.Car.Car;
import co.com.RetoSofka.carGame.domain.Car.Conductor;
import co.com.RetoSofka.carGame.domain.Game.values.Pista;
import co.com.RetoSofka.carGame.domain.Game.values.Podio;
import co.com.RetoSofka.carGame.domain.IDS.IdCarro;
import co.com.RetoSofka.carGame.domain.IDS.IdJuego;
import co.com.RetoSofka.carGame.domain.IDS.IdPlayer;
import co.com.RetoSofka.carGame.domain.IDS.Nombre;
import co.com.RetoSofka.carGame.domain.Lane.Lane;
import co.com.RetoSofka.carGame.domain.Lane.values.Posicion;
import co.com.RetoSofka.persistencia.PersistenceController;


public class Game {

    public Game() {
    }

    protected Map<IdPlayer, Player> jugadores = new HashMap<>();
    protected Pista pista;
    protected Boolean jugando;
    protected Podio podio = new Podio();
    protected ArrayList<Pista> pistas = new ArrayList<>();
    protected ArrayList<Car> carrosEnJuego = new ArrayList<>();
    protected ArrayList<Lane> carrilesEnJuego = new ArrayList<>();
    protected ArrayList<GanadoresBD> ganadores = new ArrayList<>();
    private final Car carro = new Car();
    private Boolean primeraPartida=true;

 

    // Crear jugador y la lista de jugadores con sus respectivos id
    public void crearJugador(IdPlayer jugadorId, Nombre nombre, Color color) {
        Player jugador = new Player(nombre, color, 0);
        jugadores.put(jugadorId, jugador);
        crearConductor(nombre);
    }

    // Elegir si se desea que el jugador sea un conductor y crear el conductor y asignarle un carro.
    public void crearConductor(Nombre nombre) {
        UUID id;            
        Scanner in = new Scanner(System.in);
        System.out.println("Desea que el jugador con nombre: " + nombre.getNombre() + " sea conductor ? " + "Y/N");
        while (!in.hasNext("[yYnN]")) {
            System.out.println("Solo se reciben como respuesta Y/N ó y/n");
            in.next();
        }
        String consultaConductores = in.next();
        if (consultaConductores.equals("Y") || consultaConductores.equals("y")) {
            Conductor conductor = new Conductor(nombre.getNombre());
            id = UUID.randomUUID();
            IdCarro carroId = new IdCarro(id);
            carro.asignarConductor(carroId, conductor);
        }
    }

    /* 
        Eligir pistas que se crean según cantidad de carros (pueden existir tantos carros como pistas)
        y dar kilometros  aleatorios a cada pista con limites de kilometros 100 y cada pista tiene la misma
        cantidad de carriles que carros existentes para que todos los conductores puedan participar de la carrera.        
     */
    public void crearPistas() {
        int kilometrosRandom;
        int numeroCarriles = carro.numeroCarros();
        for (int i = 0; i < carro.numeroCarros(); i++) {
            kilometrosRandom = (int) (Math.random() * 100 + 1);
            Pista pista = new Pista(kilometrosRandom, numeroCarriles);
            pistas.add(pista);
        }
    }

    public void asignarPrimerLugar(IdPlayer jugadorId) {
        podio.asignarPrimerLugar(jugadores.get(jugadorId));
        System.out.println("**********" + jugadores.get(jugadorId).nombre().getNombre() + ": Primer Lugar" + "***********");

    }

    public void asignarSegundoLugar(IdPlayer jugadorId) {
        podio.asignarSegundoLugar(jugadores.get(jugadorId));
        System.out.println("**********" + jugadores.get(jugadorId).nombre().getNombre() + ": Segundo Lugar" + "***********");
    }

    public void asignarTercerLugar(IdPlayer jugadorId) {
        podio.asignarTercerLugar(jugadores.get(jugadorId));
        System.out.println("**********" + jugadores.get(jugadorId).nombre().getNombre() + ": Tercer Lugar" + "***********");

    }

    public void iniciarJuego() {
        //Identificado para iniciar el juego
        UUID id;
        id = UUID.randomUUID();
        IdJuego juegoId = new IdJuego(id);

        //Elegir pista en cuál jugar
        Scanner in = new Scanner(System.in);
        System.out.println("Para iniciar el juego, elige la  pista (numero) en la que deseas jugar:  ");
        System.out.println("Pistas: ");
        int counter = 1;
        for (Pista p : pistas) {
            System.out.println(counter + "." + " Kilometros: " + p.kilometros() + " Número de carriles:  " + p.numeroDeCarriles());
            counter++;
        }
       while(!in.hasNextInt()) in.next();   
        int pistaElegida = in.nextInt();

        // Crear lista de carros en juego
        carro.carros().forEach((key, value) -> {
            Car carrosJuego = new Car(value, 0, Color.yellow, juegoId);
            carrosEnJuego.add(carrosJuego);

            //añadir carros a los Carriles para jugar
            int kilometrosToMetros = pistas.get(pistaElegida - 1).kilometros() * 1000;
            Posicion posicion = new Posicion(0, kilometrosToMetros);
            Lane carriles = new Lane(key, juegoId, posicion, kilometrosToMetros, false);
            carrilesEnJuego.add(carriles);
        });

        //Iniciar JUEGO
        jugando = true;
        Conductor conductor = new Conductor();
        System.out.println("----------Inicia la carrera--------");

        //Mientras no hayan 3 ganadores el juego continua
        while (jugando) {
            int contador = 0;
            System.out.println("--------Avance----- " + "--------- Meta: " + carrilesEnJuego.get(contador).metros() + " metros");
            for (Car carros : carrosEnJuego) {

                //Si el carro no ha ganado sigue jugando
                if (!yaGanoCarro(carros.conductor().nombre())) {
                    int mover = conductor.lanzarDado() * 100;
                    carros.setDistancia(carros.distancia() + mover);
                    carrilesEnJuego.get(contador).moverCarro(carrilesEnJuego.get(contador).posicion(), mover);
                    System.out.println(carros.conductor().nombre() + ":" + " mueve: " + mover + " Nueva posición: " + carros.distancia());

                    //Si el jugador llego a la final, asignarle la posición y el podio
                    if (carrilesEnJuego.get(contador).desplazamientoFinal()) {
                        if (podio.primerLugar() == null) {
                            asignarPrimerLugar(jugadorID(carros.conductor().nombre()));
                        } else if (podio.segundoLugar() == null) {
                            asignarSegundoLugar(jugadorID(carros.conductor().nombre()));
                        } else if (podio.tercerLugar() == null) {
                            asignarTercerLugar(jugadorID(carros.conductor().nombre()));
                        }
                    }
                }
                contador++;
            }
            if (podio.estaLleno()) {
                break;
            }
        }

        mostrarPodio();
        guardarRegistroBD();
        repetirJuego();
    }

    public Map<IdPlayer, Player> jugadores() {

        return jugadores;

    }

    public Boolean jugando() {

        return jugando;

    }

       // Retorna el id del jugador dando el nombre del jugador
    public IdPlayer jugadorID(String nombre) {
        IdPlayer lookId = null;
        for (IdPlayer keys : jugadores.keySet()) {
            if (jugadores.get(keys).nombre().getNombre().equals(nombre)) {
                lookId = keys;
            }
        }
        return lookId;
    }

    //Retorna True  si el carro en la carrera ya ganó
    public Boolean yaGanoCarro(String nombre) {
        boolean yaGano = false;
        if (podio.tercerLugar() == jugadores.get(jugadorID(nombre))
                || podio.primerLugar() == jugadores.get(jugadorID(nombre))
                || podio.segundoLugar() == jugadores.get(jugadorID(nombre))) {
            yaGano = true;
        }
        return yaGano;
    }

    // Método para guardar el registro  en la base de datos 
    // Si es la primera partida crea el registro en la base de datos  y si no es la primera solo hace modificaciones.
    public void guardarRegistroBD() {
        int id = 1;
        int contador = 0;
        PersistenceController controller = new PersistenceController();
        for (Car carros : carrosEnJuego) {
            int vecesPrimero = 0;
            int vecesSegundo = 0;
            int vecesTercero = 0;
            String nombreCondParticipantes = carros.conductor().nombre();
            if (!primeraPartida) {
                vecesPrimero = ganadores.get(contador).getVecesPrimero();
                vecesSegundo = ganadores.get(contador).getVecesSegundo();
                vecesTercero = ganadores.get(contador).getVecesTercero();
            }
            if (podio.primerLugar().nombre().getNombre().equals(nombreCondParticipantes)) {
                vecesPrimero += 1;

            } else if (podio.segundoLugar().nombre().getNombre().equals(nombreCondParticipantes)) {
                vecesSegundo += 1;

            } else if (podio.tercerLugar().nombre().getNombre().equals(nombreCondParticipantes)) {
                vecesTercero += 1;
            }

            if (primeraPartida) {
                GanadoresBD conductoresG = new GanadoresBD(id, nombreCondParticipantes, vecesPrimero, vecesSegundo, vecesTercero);
                ganadores.add(conductoresG);
                id++;
            } else {
                ganadores.get(contador).setVecesPrimero(vecesPrimero);
                ganadores.get(contador).setVecesSegundo(vecesSegundo);
                ganadores.get(contador).setVecesTercero(vecesTercero);
                contador++;
            }

        }

        for (GanadoresBD g : ganadores) {
            if (primeraPartida) {
                controller.crearRegistro(g);
            } else {
                controller.modificarRegistro(g);
            }
        }
        primeraPartida = false;
    }

    // Método para saber si repetir el juego y limpiar listas de juego anterior
    public void repetirJuego() {
        Scanner in = new Scanner(System.in);
        System.out.println("Desea jugar otra carrera?  Y/N");
        while (!in.hasNext("[yYnN]")) {
            System.out.println("Solo se reciben como respuesta Y/N ó y/n");
            in.next();
        }
        String jugarOtro = in.next();
        if (jugarOtro.equals("Y") || jugarOtro.equals("y")) {
            carrosEnJuego.clear();
            carrilesEnJuego.clear();
            Podio podioNuevo = new Podio();
            podio = podioNuevo;
            iniciarJuego();

        }

    }

    //Método para mostrar los conductores que quedaron en el podio 
    public void mostrarPodio() {
        System.out.println("--------Podio--------");
        System.out.println("Primer Lugar:  " + podio.primerLugar().nombre().getNombre());
        System.out.println("Segundo Lugar:  " + podio.segundoLugar().nombre().getNombre());
        System.out.println("Tercer Lugar:  " + podio.tercerLugar().nombre().getNombre());
        System.out.println("----------------------");

    }

}
