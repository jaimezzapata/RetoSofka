/**
 *
 * @author Jaime Zapata <zapataval2304@gmail.com>
 */
package co.com.RetoSofka.carGame.domain.Game.values;

import co.com.RetoSofka.carGame.domain.Game.Player;


public interface Props 
{
    public Player primerLugar();
    public Player segundoLugar();
    public Player tercerLugar();
    public Boolean estaLleno();

}
