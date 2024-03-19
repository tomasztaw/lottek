/**
 * Created by tomasz_taw
 * Date: 19.03.2024
 * Time: 15:46
 * Project Name: lottek
 * Description:
 */
package pl.taw.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NumbersForm {

    private List<Integer> numbers = new ArrayList<>();

}
