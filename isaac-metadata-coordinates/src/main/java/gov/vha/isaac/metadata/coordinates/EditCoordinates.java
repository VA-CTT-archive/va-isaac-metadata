package gov.vha.isaac.metadata.coordinates;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import org.ihtsdo.otf.tcc.api.coordinate.EditCoordinate;
import org.ihtsdo.otf.tcc.api.metadata.binding.Snomed;

import java.io.IOException;

/**
 * Created by kec on 2/16/15.
 */
public class EditCoordinates {

    public static EditCoordinate getDefaultUserSolorOverlay() throws IOException {

        EditCoordinate editCoordinate = new EditCoordinate(IsaacMetadataAuxiliaryBinding.USER.getLenient().getNid(),
                IsaacMetadataAuxiliaryBinding.SOLOR_OVERLAY.getLenient().getNid(),
                IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getNid());

        return editCoordinate;
    }


    public static EditCoordinate getDefaultUserVeteransAdministrationExtension() throws IOException {

        EditCoordinate editCoordinate = new EditCoordinate(IsaacMetadataAuxiliaryBinding.USER.getLenient().getNid(),
                IsaacMetadataAuxiliaryBinding.VHA.getLenient().getNid(),
                IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getNid());

        return editCoordinate;
    }

    public static EditCoordinate getDefaultUserMetadata() throws IOException {

        EditCoordinate editCoordinate = new EditCoordinate(IsaacMetadataAuxiliaryBinding.USER.getLenient().getNid(),
                IsaacMetadataAuxiliaryBinding.ISAAC_METADATA_MODULE.getLenient().getNid(),
                IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getNid());

        return editCoordinate;
    }
}
