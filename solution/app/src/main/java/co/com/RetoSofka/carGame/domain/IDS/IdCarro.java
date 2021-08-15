/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.carGame.domain.IDS;

import java.util.UUID;

public class IdCarro 
{
    private UUID id;

    public IdCarro(UUID id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id.toString();
    }

}
