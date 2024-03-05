/**
 * Created by tomasz_taw
 * Date: 04.03.2024
 * Time: 23:45
 * Project Name: lottek
 * Description:
 */
package pl.taw.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlayerTicker {

    private List<Integer> chosenNumbers;
    
}
