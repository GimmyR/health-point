package mg.healthpoint.dto;

public record ParameterResponse(Integer id, String name, String unit, Double min, Double max, Iterable<ParameterEntryResponse> entries) {

}
