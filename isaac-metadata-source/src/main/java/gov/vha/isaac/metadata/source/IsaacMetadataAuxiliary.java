/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.vha.isaac.metadata.source;


//~--- non-JDK imports --------------------------------------------------------

import org.ihtsdo.otf.tcc.api.lang.LanguageCode;
import org.ihtsdo.otf.tcc.api.metadata.binding.TermAux;

//~--- JDK imports ------------------------------------------------------------

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.ihtsdo.otf.tcc.api.blueprint.ComponentProperty;
import org.ihtsdo.otf.tcc.api.blueprint.ConceptCB;
import org.ihtsdo.otf.tcc.api.blueprint.IdDirective;
import org.ihtsdo.otf.tcc.api.blueprint.InvalidCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexDirective;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.ihtsdo.otf.tcc.api.metadata.binding.Snomed;
import org.ihtsdo.otf.tcc.api.metadata.binding.SnomedMetadataRf2;
import org.ihtsdo.otf.tcc.api.refex.RefexType;
import org.ihtsdo.otf.tcc.api.spec.ConceptSpec;
import org.ihtsdo.otf.tcc.dto.taxonomy.Taxonomy;

/**
 *
 * @author kec
 */
public class IsaacMetadataAuxiliary extends Taxonomy {
    
    //Need to add: SnomedMetadataRf2.PREFERRED_RF2
    //             SNOMED IS-A
    //    private static final UUID usRefexUuid = SnomedMetadataRf2.US_ENGLISH_REFSET_RF2.getUuids()[0];
    //    private static final UUID gbRefexUuid = SnomedMetadataRf2.GB_ENGLISH_REFSET_RF2.getUuids()[0];
    //    author, path, 
 
    public static ConceptSpec GENERATED_UUID  =
            new ConceptSpec("generated UUID",
            UUID.fromString("2faa9262-8fb2-11db-b606-0800200c9a66"));


   /** Field description */
   private static final String moduleName = "ISAAC Metadata Module";

   public IsaacMetadataAuxiliary() throws NoSuchAlgorithmException, UnsupportedEncodingException {
      super(TermAux.WB_AUX_PATH, TermAux.USER, moduleName, TermAux.IS_A, "(ISAAC Metadata)", LanguageCode.EN);

      try {
         createConcept("ISAAC-metadata auxiliary");
         pushParent(current());
            createConcept("module");
            pushParent(current());
                createModuleConcept(moduleName);
                createModuleConcept("LOINC");
                createModuleConcept("RxNorm");
                createModuleConcept("AMT");
                createModuleConcept("VHA");
                createModuleConcept("DOD");
                createModuleConcept("IPO");
                createModuleConcept("SOLOR Overlay");
            popParent();
            createConcept("user");
            createConcept("path");
            pushParent(current());
                ConceptCB developmentPath = createConcept("development");
                ConceptCB masterPath = createConcept("master");
            popParent();
            createConcept("set operator");
            pushParent(current());
                createConcept("sufficient set");
                createConcept("necessary set");
            popParent();
            createConcept("assemblage");
            pushParent(current());
                createConcept("dialect assemblage");
                pushParent(current());
                    createConcept("US English").setComponentUuidNoRecompute(SnomedMetadataRf2.US_ENGLISH_REFSET_RF2.getUuids()[0]);
                    createConcept("GB English").setComponentUuidNoRecompute(SnomedMetadataRf2.GB_ENGLISH_REFSET_RF2.getUuids()[0]);
                popParent();
                createConcept("path assemblage");
                pushParent(current());
                    ConceptCB paths = createConcept("paths");
                    paths.setComponentUuidNoRecompute(TermAux.PATH_REFSET.getUuids()[0]);
                    addPath(paths, masterPath);
                    addPath(paths, developmentPath);
                    
                    ConceptCB pathOrigins = createConcept("path origins");
                    pathOrigins.setComponentUuidNoRecompute(TermAux.PATH_ORIGIN_REFSET.getUuids()[0]);
                    addPathOrigin(pathOrigins, developmentPath, masterPath);
                popParent();
                
         //
            createConcept("logic assemblage");
            pushParent(current());
                createConcept("EL++ stated form");
                createConcept("EL++ inferred form");
            popParent();
         //
            createConcept("description type");
            pushParent(current());
                ConceptCB fsn = createConcept("fully specified name");
                fsn.setComponentUuidNoRecompute(Snomed.FULLY_SPECIFIED_DESCRIPTION_TYPE.getUuids()[0]);
                fsn.addExtraUuid(UUID.fromString("5e1fe940-8faf-11db-b606-0800200c9a66"), // RF1 FSN
                    GENERATED_UUID.getUuids()[0]);
                ConceptCB syn = createConcept("synonym");
                syn.setComponentUuidNoRecompute(Snomed.SYNONYM_DESCRIPTION_TYPE.getUuids()[0]);
                syn.addExtraUuid(UUID.fromString("d6fad981-7df6-3388-94d8-238cc0465a79"), 
                    GENERATED_UUID.getUuids()[0]);
                createConcept("definition").setComponentUuidNoRecompute(Snomed.DEFINITION_DESCRIPTION_TYPE.getUuids()[0]);
            popParent();
            createConcept("description acceptability");
            pushParent(current());
                createConcept("acceptable").setComponentUuidNoRecompute(SnomedMetadataRf2.ACCEPTABLE_RF2.getUuids()[0]);
                createConcept("preferred").setComponentUuidNoRecompute(SnomedMetadataRf2.PREFERRED_RF2.getUuids()[0]);
            popParent();
         
            createConcept("taxonomy operator");
            pushParent(current());
                ConceptCB isa = createConcept("is-a");
                isa.setComponentUuidNoRecompute(Snomed.IS_A.getUuids()[0]);
                isa.addExtraUuid(UUID.fromString("46bccdc4-8fb6-11db-b606-0800200c9a66"), 
                    GENERATED_UUID.getUuids()[0]); // Termaux is-a
            popParent();
            createConcept("connective operator");
            pushParent(current());
                createConcept("and");
                createConcept("or");
                createConcept("disjoint with");
                createConcept("definition root");
            popParent();
            createConcept("node operator");
            pushParent(current());
                createConcept("template merge");
                createConcept("field substitution");
                pushParent(current());
                    createConcept("concept substitution");
                    createConcept("boolean substitution");
                    createConcept("float substitution");
                    createConcept("instant substitution");
                    createConcept("integer substitution");
                    createConcept("string substitution");
                popParent();
                createConcept("concept reference");
            popParent();
            createConcept("template concept");
            pushParent(current());
                createConcept("skin of region template");
                // add annotations for order and labels
                // create template
            popParent();
            createConcept("role operator");
            pushParent(current());
                createConcept("universal restriction");
                createConcept("existential restriction");
            popParent();
            createConcept("feature");
            createConcept("literal value");
            pushParent(current());
                createConcept("boolean literal");
                createConcept("float literal");
                createConcept("instant literal");
                createConcept("integer literal");
                createConcept("string literal");
            popParent();
            createConcept("concrete domain operator");
            pushParent(current());
                createConcept("greater than");
                createConcept("greater than or equal to");
                createConcept("equal to");
                createConcept("less than or equal to");
                createConcept("less than");
            popParent();
            createConcept("description-logic variety");
            pushParent(current());
                createConcept("EL++");
                createConcept("SH");
            popParent();
            createConcept("description-logic classifier");
            pushParent(current());
                createConcept("SnoRocket");
                createConcept("ConDOR");
            popParent();
            createConcept("intrinsic role");
                pushParent(current());
                createConcept("role group");
      } catch (Exception ex) {
         Logger.getLogger(IsaacMetadataAuxiliary.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

    private void addPathOrigin(ConceptCB pathOrigins, ConceptCB newPath, ConceptCB originPath) throws ContradictionException, IOException, InvalidCAB {
        RefexCAB originCAB = pathOrigins.addSememeBlueprint(new RefexCAB(RefexType.CID_LONG,
                newPath.getComponentUuid(),
                pathOrigins.getComponentUuid(),
                IdDirective.GENERATE_REFEX_CONTENT_HASH, RefexDirective.INCLUDE));
        originCAB.getProperties().put(ComponentProperty.COMPONENT_EXTENSION_1_ID, originPath.getComponentUuid());
        originCAB.getProperties().put(ComponentProperty.LONG_EXTENSION_1, System.currentTimeMillis() + 60*60*1000);
    }

    private void addPath(ConceptCB paths, ConceptCB path) throws ContradictionException, InvalidCAB, IOException {
        paths.addSememeBlueprint(new RefexCAB(RefexType.MEMBER,
                path.getComponentUuid(), paths.getComponentUuid(),
                IdDirective.GENERATE_HASH, RefexDirective.INCLUDE));
    }

}
