/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.vha.isaac.metadata.source;


//~--- non-JDK imports --------------------------------------------------------

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import org.ihtsdo.otf.tcc.api.lang.LanguageCode;
import org.ihtsdo.otf.tcc.api.metadata.binding.RefexDynamic;
import org.ihtsdo.otf.tcc.api.metadata.binding.Taxonomies;
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
   private static final String moduleName = TermAux.ISAAC_MODULE.getDescription();

   public IsaacMetadataAuxiliary() throws NoSuchAlgorithmException, UnsupportedEncodingException {
      super(TermAux.WB_AUX_PATH, TermAux.USER, moduleName, TermAux.IS_A, "(ISAAC)", LanguageCode.EN);

      try {
         createConcept("ISAAC root");
         pushParent(current());
            createConcept("module");
            pushParent(current());
                createModuleConcept(moduleName).setComponentUuidNoRecompute(TermAux.ISAAC_MODULE.getPrimodialUuid());
                createModuleConcept("LOINC");
                createModuleConcept("RxNorm");
                createModuleConcept("AMT");
                createModuleConcept("VHA");
                createModuleConcept("DOD");
                createModuleConcept("IPO");
                createModuleConcept("SOLOR Overlay");
            popParent();
            createConcept("user").setComponentUuidNoRecompute(TermAux.USER.getUuids()[0]);
            createConcept("path").setComponentUuidNoRecompute(UUID.fromString("4459d8cf-5a6f-3952-9458-6d64324b27b7"));
            pushParent(current());
                ConceptCB developmentPath = createConcept("development");
                ConceptCB masterPath = createConcept("master");
                masterPath.setComponentUuidNoRecompute(TermAux.WB_AUX_PATH.getUuids()[0]);
            popParent();
            createConcept("set operator");
            pushParent(current());
                createConcept("sufficient set");
                createConcept("necessary set");
            popParent();
            createConcept("identifier source");
            pushParent(current());
                createConcept("SNOMED integer id").setComponentUuidNoRecompute(UUID.fromString("0418a591-f75b-39ad-be2c-3ab849326da9"));     
                createConcept("generated UUID").setComponentUuidNoRecompute(UUID.fromString("2faa9262-8fb2-11db-b606-0800200c9a66"));     
            popParent();
            createConcept("language");
            pushParent(current());
                createConcept("English");
                createConcept("Spanish");
                createConcept("French");
                createConcept("Danish");
                createConcept("Polish");
                createConcept("Dutch");
                createConcept("Lithuanian");
                createConcept("Chinese");
                createConcept("Japanese");
                createConcept("Swedish");                
            popParent();
            createConcept("assemblage membership type");
            pushParent(current());
                createConcept("normal member").setComponentUuidNoRecompute(UUID.fromString("cc624429-b17d-4ac5-a69e-0b32448aaf3c"));
                createConcept("marked parent").setComponentUuidNoRecompute(UUID.fromString("125f3d04-de17-490e-afec-1431c2a39e29"));
            popParent();
            createConcept("assemblage").setComponentUuidNoRecompute(UUID.fromString("3e0cd740-2cc6-3d68-ace7-bad2eb2621da"));
            pushParent(current());
                createConcept("dialect assemblage");
                pushParent(current());
                    createConcept("US English dialect").setComponentUuidNoRecompute(SnomedMetadataRf2.US_ENGLISH_REFSET_RF2.getUuids()[0]);
                    createConcept("GB English dialect").setComponentUuidNoRecompute(SnomedMetadataRf2.GB_ENGLISH_REFSET_RF2.getUuids()[0]);
                popParent();
                createConcept("logic assemblage");
                    pushParent(current());
                    createConcept("EL++ stated form");
                    createConcept("EL++ inferred form");
                popParent();
                createConcept("path assemblage");
                pushParent(current());
                    ConceptCB paths = createConcept("paths");
                    paths.setComponentUuidNoRecompute(TermAux.PATH_REFSET.getUuids()[0]);
                    addPath(paths, masterPath);
                    addPath(paths, developmentPath);
                    
                    ConceptCB pathOrigins = createConcept("path origins");
                    pathOrigins.setComponentUuidNoRecompute(TermAux.PATH_ORIGIN_REFSET.getUuids()[0]);
                    //addPathOrigin(pathOrigins, developmentPath, masterPath);
                popParent();
                createConcept(RefexDynamic.DYNAMIC_SEMEME_ASSEMBLAGES.getFsn()).setComponentUuidNoRecompute(RefexDynamic.DYNAMIC_SEMEME_ASSEMBLAGES.getPrimodialUuid());
                pushParent(current());
                    createConcept("description source type reference sets");  //Dynamic Sememes are created under this node for LOINC and RxNorm description types
                    createConcept("relationship source type reference sets"); //Dynamic Sememes are created under this node for LOINC and RxNorm relationship types
                popParent();
                createConcept(RefexDynamic.DYNAMIC_SEMEME_METADATA.getFsn()).setComponentUuidNoRecompute(RefexDynamic.DYNAMIC_SEMEME_METADATA.getPrimodialUuid());
          popParent();
          //
            createConcept("axiom origin");
                pushParent(current());
                ConceptCB stated = createConcept("stated");
                stated.setComponentUuidNoRecompute(SnomedMetadataRf2.STATED_RELATIONSHIP_RF2.getUuids()[0]);
                stated.addExtraUuid(TermAux.REL_STATED_CHAR.getUuids()[0], GENERATED_UUID.getUuids()[0]);
                ConceptCB inferred = createConcept("inferred");
                inferred.setComponentUuidNoRecompute(SnomedMetadataRf2.INFERRED_RELATIONSHIP_RF2.getUuids()[0]);
                inferred.addExtraUuid(TermAux.REL_INFERED_CHAR.getUuids()[0], GENERATED_UUID.getUuids()[0]);
            popParent();
          //
         //
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
            createConcept("description type in source terminology");  //LOINC and RxNorm description types are created under this node
            createConcept("description acceptability");
            pushParent(current());
                createConcept("acceptable").setComponentUuidNoRecompute(SnomedMetadataRf2.ACCEPTABLE_RF2.getUuids()[0]);
                createConcept("preferred").setComponentUuidNoRecompute(SnomedMetadataRf2.PREFERRED_RF2.getUuids()[0]);
            popParent();
            
            createConcept("relationship type in source terminology");  //LOINC and RxNorm relationship types are created under this node
         
            createConcept("taxonomy operator");
            pushParent(current());
                ConceptCB isa = createConcept("is-a");
                isa.setComponentUuidNoRecompute(Snomed.IS_A.getUuids()[0]);
                isa.addExtraUuid(TermAux.IS_A.getUuids()[0], GENERATED_UUID.getUuids()[0]);
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
            createConcept("description-logic profile");
            pushParent(current());
                createConcept("EL++");
                createConcept("SH");
            popParent();
            createConcept("description-logic classifier");
            pushParent(current());
                createConcept("IHTSDO classifier").setComponentUuidNoRecompute(UUID.fromString("7e87cc5b-e85f-3860-99eb-7a44f2b9e6f9"));
                createConcept("SnoRocket");
                createConcept("ConDOR");
            popParent();
            createConcept("role").setComponentUuidNoRecompute(Taxonomies.SNOMED_ROLE_ROOT.getUuids()[0]);
            pushParent(current());
                createConcept("intrinsic role");
                pushParent(current());
                    createConcept("role group");
                popParent();
            popParent();
            createConcept("unmodeled concept");
            pushParent(current());
                createConcept("unmodeled role concept");
                createConcept("unmodeled feature concept");
                createConcept("unmodeled taxonomic concept");
            popParent();
            createConcept("health concept").setComponentUuidNoRecompute(Taxonomies.SNOMED.getUuids()[0]);
            createConcept("object properties");
            pushParent(current());
                createConcept("coordinate properties");
                pushParent(current());
                    createConcept("author sequence for edit coordinate");
                    createConcept("module sequence for edit coordinate");
                    createConcept("path sequence for edit cordinate");
                    createConcept("language sequence for language coordinate");
                    createConcept("dialect assemblage sequence preference list for language coordinate");
                    createConcept("description type sequence preference list for language coordinate");
                    createConcept("stated assemblage sequence for logic coordinate");
                    createConcept("inferred assemblage sequence for logic coordinate");
                    createConcept("description logic profile sequence for logic coordinate");
                    createConcept("classifier sequence for logic coordinate");
                    createConcept("stamp precedence for stamp coordinate");
                    createConcept("stamp position for stamp coordinate");
                    createConcept("module sequence array for stamp coordinate");
                    createConcept("path sequence for stamp path");
                    createConcept("path origin list for stamp path");
                    createConcept("time for stamp position");
                    createConcept("path sequence for stamp position");
                    createConcept("taxonomy type for taxonomy coordinate");
                    createConcept("stamp coordinate for taxonomy coordinate");
                    createConcept("language coordinate for taxonomy coordinate");
                popParent();
                createConcept("version properties");
                pushParent(current());
                    createConcept("status for version");
                    createConcept("time for version");
                    createConcept("author sequence for version");
                    createConcept("module sequence for version");
                    createConcept("path sequence for version");
                    createConcept("committed state for version");
                    createConcept("stamp sequence for version");
                    createConcept("description version properties");               
                    pushParent(current());
                        createConcept("case significance concept sequence for description");               
                        createConcept("language concept sequence for description");               
                        createConcept("text for description");               
                        createConcept("description type for description");               
                    popParent();
                popParent();
                createConcept("chronicle properties");
                pushParent(current());
                    createConcept("version list for chronicle");
                    createConcept("native id for chronicle");
                    createConcept("concept sequence for chronicle");
                    createConcept("sememe sequence for chronicle");
                    createConcept("primordial UUID for chronicle");
                    createConcept("UUID list for chronicle");
                    createConcept("committed state for chronicle");
                    createConcept("sememe list for chronicle");
                    
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
    public static void main(String[] args) {
        try {
            IsaacMetadataAuxiliary aux = new IsaacMetadataAuxiliary();
            aux.exportEConcept(new DataOutputStream(new ByteArrayOutputStream(10240)));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(IsaacMetadataAuxiliary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(IsaacMetadataAuxiliary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IsaacMetadataAuxiliary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
