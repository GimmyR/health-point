package mg.healthpoint.dto;

import java.time.LocalDateTime;

public record ParameterEntryResponse(Integer id, Integer parameterId, LocalDateTime entryDate, Double value) {

}
