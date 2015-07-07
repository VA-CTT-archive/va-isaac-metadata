package gov.vha.isaac.metadata.coordinates;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.ochre.api.Get;
import gov.vha.isaac.ochre.api.coordinate.EditCoordinate;
import gov.vha.isaac.ochre.model.coordinate.EditCoordinateImpl;
import org.ihtsdo.otf.tcc.api.spec.ConceptSpec;


/**
 * Created by kec on 2/16/15.
 */
public class EditCoordinates {
    
    private static int getNid(ConceptSpec spec) {
        return Get.identifierService().getNidForUuids(spec.getUuids());
    }

    public static EditCoordinate getDefaultUserSolorOverlay() {

        EditCoordinate editCoordinate = new EditCoordinateImpl(
                getNid(IsaacMetadataAuxiliaryBinding.USER),
                getNid(IsaacMetadataAuxiliaryBinding.SOLOR_OVERLAY),
                getNid(IsaacMetadataAuxiliaryBinding.DEVELOPMENT));

        return editCoordinate;
    }

    public static EditCoordinate getClassifierSolorOverlay() {
        EditCoordinate editCoordinate = new EditCoordinateImpl(
                getNid(IsaacMetadataAuxiliaryBinding.IHTSDO_CLASSIFIER),
                getNid(IsaacMetadataAuxiliaryBinding.SOLOR_OVERLAY),
                getNid(IsaacMetadataAuxiliaryBinding.DEVELOPMENT));

        return editCoordinate;
    }

    public static EditCoordinate getDefaultUserVeteransAdministrationExtension() {

        EditCoordinate editCoordinate = new EditCoordinateImpl(
                getNid(IsaacMetadataAuxiliaryBinding.USER),
                getNid(IsaacMetadataAuxiliaryBinding.VHA),
                getNid(IsaacMetadataAuxiliaryBinding.DEVELOPMENT));

        return editCoordinate;
    }

    public static EditCoordinate getDefaultUserMetadata()  {

        EditCoordinate editCoordinate = new EditCoordinateImpl(
                getNid(IsaacMetadataAuxiliaryBinding.USER),
                getNid(IsaacMetadataAuxiliaryBinding.ISAAC_MODULE),
                getNid(IsaacMetadataAuxiliaryBinding.DEVELOPMENT));

        return editCoordinate;
    }
}
