package gov.vha.isaac.metadata.coordinates;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionManagerBI;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionManagerPolicy;
import org.ihtsdo.otf.tcc.api.contradiction.strategy.IdentifyAllConflict;
import org.ihtsdo.otf.tcc.api.coordinate.*;
import org.ihtsdo.otf.tcc.api.metadata.binding.SnomedMetadataRf2;
import org.ihtsdo.otf.tcc.api.metadata.binding.TermAux;
import org.ihtsdo.otf.tcc.api.relationship.RelAssertionType;
import org.ihtsdo.otf.tcc.api.spec.ConceptSpec;
import org.ihtsdo.otf.tcc.api.store.Ts;

import java.io.IOException;
import java.util.ArrayList;
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
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
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
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);

        return viewCoordinate;
    }


    public static ViewCoordinate getDevelopmentStatedLatest() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("a881b58e-b622-11e4-a71e-12e3f512a338"),
                "development stated-latest", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);

        return viewCoordinate;
    }

    public static ViewCoordinate getDevelopmentStatedLatestActiveOnly() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("960eb68a-b623-11e4-a71e-12e3f512a338"),
                "development stated-latest active-only", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);

        return viewCoordinate;
    }
//
public static ViewCoordinate getMasterInferredLatest() throws IOException {
    ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("58c9ae7a-b640-11e4-a71e-12e3f512a338"),
            "development inferred-latest", Ts.get().getMetadataVC());
    Position viewPosition
            = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.MASTER.getLenient().getConceptNid()),
            Long.MAX_VALUE);

    viewCoordinate.setViewPosition(viewPosition);
    viewCoordinate.setRelationshipAssertionType(RelAssertionType.INFERRED);
    viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));
    viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
    viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
    viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
    viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);

    return viewCoordinate;
}

    public static ViewCoordinate getMasterInferredLatestActiveOnly() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("58c9b2bc-b640-11e4-a71e-12e3f512a338"),
                "development inferred-latest active-only", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.MASTER.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.INFERRED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);

        return viewCoordinate;
    }


    public static ViewCoordinate getMasterStatedLatest() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("58c9b53c-b640-11e4-a71e-12e3f512a338"),
                "development stated-latest", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.MASTER.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE, Status.INACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);

        return viewCoordinate;
    }

    public static ViewCoordinate getMasterStatedLatestActiveOnly() throws IOException {
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("58c9b7a8-b640-11e4-a71e-12e3f512a338"),
                "development stated-latest active-only", Ts.get().getMetadataVC());
        Position position
                = Ts.get().newPosition(Ts.get().getPath(IsaacMetadataAuxiliaryBinding.MASTER.getLenient().getConceptNid()),
                Long.MAX_VALUE);

        viewCoordinate.setViewPosition(position);
        viewCoordinate.setRelationshipAssertionType(RelAssertionType.STATED);
        viewCoordinate.setAllowedStatus(EnumSet.of(Status.ACTIVE));
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);

        return viewCoordinate;
    }


    public static ViewCoordinate getMetadataViewCoordinate() throws IOException {
        Path viewPath = new Path(IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid(), null);
        Position viewPosition = new Position(Long.MAX_VALUE, viewPath);
        EnumSet<Status> allowedStatusNids = EnumSet.of(Status.ACTIVE);
        ContradictionManagerBI contradictionManager = new IdentifyAllConflict();
        int languageNid = IsaacMetadataAuxiliaryBinding.US_ENGLISH.getLenient().getConceptNid();
        int classifierNid = IsaacMetadataAuxiliaryBinding.SNOROCKET.getLenient().getNid();
        ViewCoordinate viewCoordinate = new ViewCoordinate(UUID.fromString("cec309be-b622-11e4-a71e-12e3f512a338"), "meta-vc", Precedence.PATH,
                viewPosition, allowedStatusNids, contradictionManager, languageNid, classifierNid,
                RelAssertionType.INFERRED_THEN_STATED, null, LanguageSort.RF2_LANG_REFEX);
        viewCoordinate.setDescriptionLogicProfileSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS);
        viewCoordinate.setStatedAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM);
        viewCoordinate.setInferredAssemblageSpec(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM);
        viewCoordinate.setClassifierSpec(IsaacMetadataAuxiliaryBinding.SNOROCKET);
        return viewCoordinate;
    }

//
//
//    public static ViewCoordinate makeMetaViewCoordinate() throws IOException {
//        Path viewPath = new Path();
//        viewPath.setConceptSpec(TermAux.WB_AUX_PATH);
//        Position viewPosition = new Position(Long.MAX_VALUE, viewPath);
//        EnumSet<Status> allowedStatus = EnumSet.of(Status.ACTIVE);
//        ContradictionManagerBI contradictionManager = new IdentifyAllConflict();
//        ViewCoordinate vc = new ViewCoordinate();
//        vc.setAllowedStatus(allowedStatus);
//        vc.setClassifierSpec(TermAux.IHTSDO_CLASSIFIER);
//        vc.setContradictionManager(contradictionManager);
//        vc.setContradictionManagerPolicy(ContradictionManagerPolicy.IDENTIFY_ALL_CONFLICTS);
//        vc.setLanguageSpec(SnomedMetadataRf2.US_ENGLISH_REFSET_RF2);
//        ArrayList<ConceptSpec> langPrefConceptSpecList = new ArrayList<>();
//        langPrefConceptSpecList.add(SnomedMetadataRf2.US_ENGLISH_REFSET_RF2);
//        langPrefConceptSpecList.add(SnomedMetadataRf2.GB_ENGLISH_REFSET_RF2);
//        vc.setLanguagePreferenceList(new LanguagePreferenceList(langPrefConceptSpecList));
//        vc.setLanguageSort(LanguageSort.RF2_LANG_REFEX);
//        vc.setName("meta-vc");
//        vc.setPrecedence(Precedence.PATH);
//        vc.setRelationshipAssertionType(RelAssertionType.INFERRED_THEN_STATED);
//        vc.setVcUuid(UUID.fromString("014ae770-b32a-11e1-afa6-0800200c9a66"));
//        vc.setViewPosition(viewPosition);
//        return vc;
//    }

}
