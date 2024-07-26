package org.georgievbozhidar.softunifinal2rest.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.georgievbozhidar.softunifinal2rest.entity.enums.LocationType;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;

public class CreateLocationDTO {
    @NotBlank
    private String address;

    @NotNull
    @JsonProperty("ownedBy")
    private ChainDTO ownedBy;
    @NotNull
    private LocationType locationType;

    public @NotBlank String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank String address) {
        this.address = address;
    }

    public @NotNull ChainDTO getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(@NotNull ChainDTO ownedBy) {
        this.ownedBy = ownedBy;
    }

    public @NotNull LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(@NotNull LocationType locationType) {
        this.locationType = locationType;
    }
}
