package mg.healthpoint.dto;

import java.time.LocalDateTime;

public record ParameterEntryResponse(Integer id, LocalDateTime entryDate, Double value) {

}
