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
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(viewPosition);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.INFERRED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        viewCoordinate.getDescriptionTypePrefSpecs().add(IsaacMetadataAuxiliaryBinding.SYNONYM);
        viewCoordinate.getDescriptionTypePrefSpecs().add(IsaacMetadataAuxiliaryBinding.FULLY_SPECIFIED_NAME);
        viewCoordinate.getLangPrefSpecs().clear();
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);
        return viewCoordinate;
    }

    public static ViewCoordinate getDevelopmentInferredLatestActiveOnly() {
        try {
            ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("a881b444-b622-11e4-a71e-12e3f512a338"),
                    "development inferred-latest active-only", Ts.get().getMetadataVC());
            Position position
                    = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getNid()),
                            Long.MAX_VALUE);
            
            viewCoordinate.setViewPosition(position);
            viewCoordinate.setRelationshipAssertionType(RelAssertionType.INFERRED);
            viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));
            viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
            viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
            viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
            viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
            viewCoordinate.getLangPrefSpecs().clear();
            viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
            viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);
            
            return viewCoordinate;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public static ViewCoordinate getDevelopmentStatedLatest() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("a881b58e-b622-11e4-a71e-12e3f512a338"),
                "development stated-latest", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        viewCoordinate.getLangPrefSpecs().clear();
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);

        return viewCoordinate;
    }

    public static ViewCoordinate getDevelopmentStatedLatestActiveOnly() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("960eb68a-b623-11e4-a71e-12e3f512a338"),
                "development stated-latest active-only", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        viewCoordinate.getLangPrefSpecs().clear();
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);

        return viewCoordinate;
    }
//
public static ViewCoordinate getMasterInferredLatest() throws IOException {
    ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("58c9ae7a-b640-11e4-a71e-12e3f512a338"),
            "development inferred-latest", Ts.get().getMetadataVC());
    Position viewPosition
            = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.MASTER.getLenient().getNid()),
            Long.MAX_VALUE);

    viewCoordinate.setViewPosition(viewPosition);
    viewCoordinate.setRelationshipAssertionType(RelAssertionType.INFERRED);
    viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));
    viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
    viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
    viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
    viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        viewCoordinate.getLangPrefSpecs().clear();
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);

    return viewCoordinate;
}

    public static ViewCoordinate getMasterInferredLatestActiveOnly() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("58c9b2bc-b640-11e4-a71e-12e3f512a338"),
                "development inferred-latest active-only", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.MASTER.getLenient().getNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.INFERRED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        viewCoordinate.getLangPrefSpecs().clear();
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);

        return viewCoordinate;
    }


    public static ViewCoordinate getMasterStatedLatest() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("58c9b53c-b640-11e4-a71e-12e3f512a338"),
                "development stated-latest", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.MASTER.getLenient().getNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        viewCoordinate.getLangPrefSpecs().clear();
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);

        return viewCoordinate;
    }

    public static ViewCoordinate getMasterStatedLatestActiveOnly() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("58c9b7a8-b640-11e4-a71e-12e3f512a338"),
                "development stated-latest active-only", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.MASTER.getLenient().getNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        viewCoordinate.getLangPrefSpecs().clear();
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);

        return viewCoordinate;
    }


    public static ViewCoordinate getMetadataViewCoordinate() throws IOException {
        Path viewPath = new Path(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getNid(), null);
        Position viewPosition = new Position(Long.MAX_VALUE, viewPath);
        EnumSet<Status> allowedStatusNids = EnumSet.of(Status.ACTIVE);
        ContradictionManagerBI contradictionManager = new IdentifyAllConflict();
        int languageNid = IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT.getLenient().getNid();
        int classifierNid = IsaacMetadataAuxiliaryBinding.SNOROCKET.getLenient().getNid();
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("cec309be-b622-11e4-a71e-12e3f512a338"), "meta-vc", Precedence.PATH,
                viewPosition, allowedStatusNids, contradictionManager, languageNid, classifierNid,
                RelAssertionType.INFERRED_THEN_STATED, null, LanguageSort.RF2_LANG_REFEX);
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        viewCoordinate.getLangPrefSpecs().clear();
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        viewCoordinate.getLangPrefSpecs().add(IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT);
        return viewCoordinate;
    }
}
