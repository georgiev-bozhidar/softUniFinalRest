package org.georgievbozhidar.softunifinal2rest.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.georgievbozhidar.softunifinal2.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.entity.enums.LocationType;

public class CreateLocationDTO {
    @NotBlank
    private String address;

    @NotNull
    private Chain ownedBy;
    @NotNull
    private LocationType locationType;

    public @NotBlank String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank String address) {
        this.address = address;
    }

    public @NotNull Chain getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(@NotNull Chain ownedBy) {
        this.ownedBy = ownedBy;
    }

    public @NotNull LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(@NotNull LocationType locationType) {
        this.locationType = locationType;
    }
}
