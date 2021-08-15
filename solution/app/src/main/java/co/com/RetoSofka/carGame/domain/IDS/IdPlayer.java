/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.carGame.domain.IDS;

import java.util.UUID;


public class IdPlayer 
{
    private UUID id;

    public IdPlayer(UUID id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id.toString();
    }

}
