package gov.vha.isaac.metadata.coordinates;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.ochre.api.IdentifierService;
import gov.vha.isaac.ochre.api.LookupService;
import gov.vha.isaac.ochre.api.coordinate.EditCoordinate;
import gov.vha.isaac.ochre.model.coordinate.EditCoordinateImpl;
import org.ihtsdo.otf.tcc.api.spec.ConceptSpec;


/**
 * Created by kec on 2/16/15.
 */
public class EditCoordinates {
    
    private static IdentifierService identifierService = null;
    private static IdentifierService getIdentifierService() {
        if (identifierService == null) {
            identifierService = LookupService.getService(IdentifierService.class);
        }
        return identifierService;
    }
    
    private static int getNid(ConceptSpec spec) {
        return getIdentifierService().getNidForUuids(spec.getUuids());
    }

    public static EditCoordinate getDefaultUserSolorOverlay() {

        EditCoordinate editCoordinate = new EditCoordinateImpl(
                getNid(IsaacMetadataAuxiliaryBinding.USER),
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
