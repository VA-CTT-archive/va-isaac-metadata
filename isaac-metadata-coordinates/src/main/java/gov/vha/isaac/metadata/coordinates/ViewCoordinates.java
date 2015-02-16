package gov.vha.isaac.metadata.coordinates;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionManagerBI;
import org.ihtsdo.otf.tcc.api.contradiction.strategy.IdentifyAllConflict;
import org.ihtsdo.otf.tcc.api.coordinate.*;
import org.ihtsdo.otf.tcc.api.relationship.RelAssertionType;
import org.ihtsdo.otf.tcc.api.store.Ts;

import java.io.IOException;
import java.util.EnumSet;
import java.util.UUID;

/**
 * Created by kec on 2/16/15.
 */
public class ViewCoordinates {

    public static ViewCoordinate getDevelopmentInferredLatest() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("a881b200-b622-11e4-a71e-12e3f512a338"),
                "development inferred-latest", Ts.get().getMetadataVC());
        Position viewPosition
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(viewPosition);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.INFERRED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));

        return viewCoordinate;
    }

    public static ViewCoordinate getDevelopmentInferredLatestActiveOnly() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("a881b444-b622-11e4-a71e-12e3f512a338"),
                "development inferred-latest active-only", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.INFERRED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));

        return viewCoordinate;
    }


    public static ViewCoordinate getSnomedStatedLatest() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("a881b58e-b622-11e4-a71e-12e3f512a338"),
                "development stated-latest", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));

        return viewCoordinate;
    }

    public static ViewCoordinate getSnomedStatedLatestActiveOnly() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("960eb68a-b623-11e4-a71e-12e3f512a338"),
                "development stated-latest active-only", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));

        return viewCoordinate;
    }


    public static ViewCoordinate getMetadataViewCoordinate() throws IOException {
        Path viewPath = new Path(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid(), null);
        Position viewPosition = new Position(Long.MAX_VALUE, viewPath);
        EnumSet<Status> allowedStatusNids = EnumSet.of(Status.ACTIVE);
        ContradictionManagerBI contradictionManager = new IdentifyAllConflict();
        int languageNid = IsaacMetadataAuxiliaryBinding.US_ENGLISH.getLenient().getConceptNid();
        int classifierNid = IsaacMetadataAuxiliaryBinding.SNOROCKET.getLenient().getNid();

        return new ViewCoordinate(UUID.fromString("cec309be-b622-11e4-a71e-12e3f512a338"), "meta-vc", Precedence.PATH,
                viewPosition, allowedStatusNids, contradictionManager, languageNid, classifierNid,
                RelAssertionType.INFERRED_THEN_STATED, null, LanguageSort.RF2_LANG_REFEX);
    }



}
