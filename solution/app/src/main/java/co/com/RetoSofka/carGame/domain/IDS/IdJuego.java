/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.carGame.domain.IDS;

import java.util.UUID;


public class IdJuego 
{
    private UUID id;

    public IdJuego(UUID id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id.toString();
    }

}
