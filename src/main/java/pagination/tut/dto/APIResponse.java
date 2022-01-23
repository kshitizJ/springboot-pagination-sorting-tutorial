package pagination.tut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * APIResponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {

    Integer recordCount;
    T reponse;

}