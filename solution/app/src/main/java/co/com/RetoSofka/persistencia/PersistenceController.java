/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.RetoSofka.carGame.domain.Game.GanadoresBD;


public class PersistenceController 
{
    GanadoresBDJpaController ganadoresJPA = new GanadoresBDJpaController();

    public void crearRegistro(GanadoresBD ganadores) 
    {
        try 
        {
            ganadoresJPA.create(ganadores);
        } catch (Exception ex) 
        {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarRegistro(GanadoresBD ganadores) 
    {
        try 
        {
            ganadoresJPA.edit(ganadores);
        } catch (Exception ex) 
        {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
