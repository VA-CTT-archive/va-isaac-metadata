package gov.vha.isaac.ochre.impl.sememe;

import java.util.Arrays;
import java.util.Optional;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copyright Notice
 *
 * This is a work of the U.S. Government and is not subject to copyright 
 * protection in the United States. Foreign copyrights may apply.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.inject.Singleton;
import org.ihtsdo.otf.tcc.api.blueprint.ComponentProperty;
import org.ihtsdo.otf.tcc.api.blueprint.ConceptCB;
import org.ihtsdo.otf.tcc.api.blueprint.DescriptionCAB;
import org.ihtsdo.otf.tcc.api.blueprint.IdDirective;
import org.ihtsdo.otf.tcc.api.blueprint.InvalidCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexDirective;
import org.ihtsdo.otf.tcc.api.concept.ConceptChronicleBI;
import org.ihtsdo.otf.tcc.api.coordinate.EditCoordinate;
import org.ihtsdo.otf.tcc.api.lang.LanguageCode;
import org.ihtsdo.otf.tcc.api.metadata.ComponentType;
import org.ihtsdo.otf.tcc.api.metadata.binding.Snomed;
import org.ihtsdo.otf.tcc.api.metadata.binding.SnomedMetadataRf2;
import org.ihtsdo.otf.tcc.api.metadata.binding.TermAux;
import org.ihtsdo.otf.tcc.api.refex.RefexType;
import org.ihtsdo.otf.tcc.api.store.Ts;
import org.jvnet.hk2.annotations.Service;
import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.ochre.api.Get;
import gov.vha.isaac.ochre.api.LookupService;
import gov.vha.isaac.ochre.api.chronicle.LatestVersion;
import gov.vha.isaac.ochre.api.chronicle.ObjectChronologyType;
import gov.vha.isaac.ochre.api.component.concept.ConceptBuilder;
import gov.vha.isaac.ochre.api.component.concept.ConceptBuilderService;
import gov.vha.isaac.ochre.api.component.concept.ConceptChronology;
import gov.vha.isaac.ochre.api.component.concept.ConceptVersion;
import gov.vha.isaac.ochre.api.component.sememe.SememeChronology;
import gov.vha.isaac.ochre.api.component.sememe.SememeType;
import gov.vha.isaac.ochre.api.component.sememe.version.ComponentNidSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.DescriptionSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.DynamicSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeColumnInfo;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeDataBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeDataType;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeUsageDescriptionBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeUtilityBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeArrayBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeBooleanBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeByteArrayBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeDoubleBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeFloatBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeIntegerBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeLongBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeNidBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeSequenceBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeStringBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeUUIDBI;
import gov.vha.isaac.ochre.model.constants.IsaacMetadataConstants;
import gov.vha.isaac.ochre.model.sememe.dataTypes.DynamicSememeBoolean;
import gov.vha.isaac.ochre.model.sememe.dataTypes.DynamicSememeInteger;
import gov.vha.isaac.ochre.model.sememe.dataTypes.DynamicSememeString;
import gov.vha.isaac.ochre.model.sememe.dataTypes.DynamicSememeUUID;

/**
 * {@link DynamicSememeUtility}
 *
 * Convenience methods related to DynamicSememes.  Implemented as an interface and a singleton to provide 
 * lower level code with access to these methods at runtime via HK2.
  *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
@Service
@Singleton
public class DynamicSememeUtility implements DynamicSememeUtilityBI
{
	
	/**
	 * Read the {@link DynamicSememeUsageDescriptionBI} for the specified assemblage concept
	 */
	@Override
	public DynamicSememeUsageDescriptionBI readDynamicSememeUsageDescription(int assemblageNidOrSequence)
	{
		return DynamicSememeUsageDescription.read(assemblageNidOrSequence);
	}

	/**
	 * Create a new concept using the provided columnName and columnDescription values which is suitable 
	 * for use as a column descriptor within {@link DynamicSememeUsageDescription}.
	 * 
	 * The new concept will be created under the concept {@link DynamicSememe#DYNAMIC_SEMEME_COLUMNS}
	 * 
	 * A complete usage pattern (where both the refex assemblage concept and the column name concept needs
	 * to be created) would look roughly like this:
	 * 
	 * DynamicSememeUsageDescriptionBuilder.createNewDynamicSememeUsageDescriptionConcept(
	 *     "The name of the Refex", 
	 *     "The description of the Refex",
	 *     new DynamicSememeColumnInfo[]{new DynamicSememeColumnInfo(
	 *         0,
	 *         DynamicSememeColumnInfo.createNewDynamicSememeColumnInfoConcept(
	 *             "column name",
	 *             "column description"
	 *             )
	 *         DynamicSememeDataType.STRING,
	 *         new RefexString("default value")
	 *         )}
	 *     )
	 * 
	 * //TODO (artf231856) [REFEX] figure out language details (how we know what language to put on the name/description
	 * * @param vc view coordinate -  highly recommended that you use ViewCoordinates.getMetadataViewCoordinate()
	 * @throws ContradictionException 
	 * @throws InvalidCAB 
	 * @throws IOException 
	 */
	
	public static ConceptChronicleImpl createNewDynamicSememeColumnInfoConcept(String columnName, String columnDescription, ViewCoordinate vc) 
			throws IOException
	{
		if (columnName == null || columnName.length() == 0 || columnDescription == null || columnDescription.length() == 0)
		{
			throw new IOException("Both the column name and column description are required");
		}

		LanguageCode lc = LanguageCode.EN_US;
		UUID isA = Snomed.IS_A.getUuids()[0];
		IdDirective idDir = IdDirective.GENERATE_HASH;
		UUID module = Snomed.CORE_MODULE.getUuids()[0];
		UUID parents[] = new UUID[] { DynamicSememe.DYNAMIC_SEMEME_COLUMNS.getUuids()[0] };

		ConceptCB cab = new ConceptCB(columnName, columnName, lc, isA, idDir, module, null, parents);
		
		DescriptionCAB dCab = new DescriptionCAB(cab.getComponentUuid(),  Snomed.DEFINITION_DESCRIPTION_TYPE.getUuids()[0], LanguageCode.EN, 
				columnDescription, false, IdDirective.GENERATE_HASH);
		dCab.getProperties().put(ComponentProperty.MODULE_ID, module);
		
		RefexCAB rCab = new RefexCAB(RefexType.CID, dCab.getComponentUuid(), 
				Snomed.US_LANGUAGE_REFEX.getUuids()[0], IdDirective.GENERATE_HASH, RefexDirective.EXCLUDE);
		rCab.put(ComponentProperty.COMPONENT_EXTENSION_1_ID, SnomedMetadataRf2.PREFERRED_RF2.getUuids()[0]);
		rCab.getProperties().put(ComponentProperty.MODULE_ID, module);
		
		dCab.addAnnotationBlueprint(rCab);
		
		cab.addDescriptionCAB(dCab);
		
		ConceptChronicleBI newCon = Ts.get().getTerminologyBuilder(
				new EditCoordinate(TermAux.USER.getLenient().getConceptNid(), 
					TermAux.ISAAC_MODULE.getLenient().getNid(), 
					TermAux.WB_AUX_PATH.getLenient().getConceptNid()), 
					vc).construct(cab);
		Ts.get().addUncommitted(newCon);
		Ts.get().commit(/* newCon */);
		
		return newCon;
	}
	
	/**
	 * See {@link DynamicSememeUsageDescriptionBI} for the full details on what this builds.
	 * 
	 * Does all the work to create a new concept that is suitable for use as an Assemblage Concept for a new style Dynamic Sememe.
	 * 
	 * The concept will be created under the concept {@link IsaacMetadataConstants#DYNAMIC_SEMEME_ASSEMBLAGES} if a parent is not specified
	 * 
	 * //TODO (artf231856) [REFEX] figure out language details (how we know what language to put on the name/description
	 * @param refexFSN - The FSN for this refex concept that will be created.
	 * @param refexPreferredTerm - The preferred term for this refex concept that will be created.
	 * @param refexDescription - A user friendly string the explains the overall intended purpose of this refex (what it means, what it stores)
	 * @param columns - The column information for this new refex.  May be an empty list or null.
	 * @param parentConcept  - optional - if null, uses {@link IsaacMetadataConstants#DYNAMIC_SEMEME_ASSEMBLAGES}
	 * @param annotationStyle - true for annotation style storage, false for memberset storage
	 * @param referencedComponentRestriction - optional - may be null - if provided - this restricts the type of object referenced by the nid or 
	 * UUID that is set for the referenced component in an instance of this sememe.  If {@link ObjectChronologyType#UNKNOWN_NID} is passed, it is ignored, as 
	 * if it were null.
	 * @param referencedComponentSubRestriction - optional - may be null - subtype restriction for {@link ObjectChronologyType#SEMEME} restrictions
	 * @return a reference to the newly created sememe item
	 */
	public static DynamicSememeUsageDescription createNewDynamicSememeUsageDescriptionConcept(String sememeFSN, String refexPreferredTerm, 
			String refexDescription, DynamicSememeColumnInfo[] columns, UUID parentConcept, ObjectChronologyType referencedComponentRestriction,
			SememeType referencedComponentSubRestriction)
	{
		LanguageCode lc = LanguageCode.EN_US;
		UUID isA = Snomed.IS_A.getUuids()[0];
		IdDirective idDir = IdDirective.GENERATE_HASH;
		UUID module = TermAux.ISAAC_MODULE.getPrimodialUuid();
		UUID parents[] = new UUID[] { parentConcept == null ? DynamicSememe.DYNAMIC_SEMEME_ASSEMBLAGES.getUuids()[0] : parentConcept };
		UUID path = null; // TODO get the path set right...

		ConceptBuilder cb = LookupService.get().getService(ConceptBuilderService.class).getDefaultConceptBuilder(sememeFSN, null, logicalExpression);
		
		//Look at GenerateUsers
		
		ConceptCB cab = new ConceptCB(refexFSN, refexPreferredTerm, lc, isA, idDir, module, path, parents);
		cab.setAnnotationRefexExtensionIdentity(annotationStyle);
		
		DescriptionCAB dCab = new DescriptionCAB(cab.getComponentUuid(), Snomed.DEFINITION_DESCRIPTION_TYPE.getUuids()[0], lc, refexDescription, true,
				IdDirective.GENERATE_HASH);
		dCab.getProperties().put(ComponentProperty.MODULE_ID, module);
		
		//Mark it as preferred
		RefexCAB rCabPreferred = new RefexCAB(RefexType.CID, dCab.getComponentUuid(), 
				Snomed.US_LANGUAGE_REFEX.getUuids()[0], IdDirective.GENERATE_HASH, RefexDirective.EXCLUDE);
		rCabPreferred.put(ComponentProperty.COMPONENT_EXTENSION_1_ID, SnomedMetadataRf2.PREFERRED_RF2.getUuids()[0]);
		rCabPreferred.getProperties().put(ComponentProperty.MODULE_ID, module);
		dCab.addAnnotationBlueprint(rCabPreferred);
		
		Get.sememeBuilderService().getDyanmicSememeBuilder(referencedComponentNid, assemblageConceptSequence)
		DynamicSememeCAB descriptionMarker = new DynamicSememeCAB(dCab.getComponentUuid(), DynamicSememe.DYNAMIC_SEMEME_DEFINITION_DESCRIPTION.getUuids()[0]);
		dCab.addAnnotationBlueprint(descriptionMarker);
	
		cab.addDescriptionCAB(dCab);
		
		if (columns != null)
		{
			//Ensure that we process in column order - we don't always keep track of that later - we depend on the data being stored in the right order.
			TreeSet<DynamicSememeColumnInfo> sortedColumns = new TreeSet<>(Arrays.asList(columns));
			
			for (DynamicSememeColumnInfo ci : sortedColumns)
			{
				DynamicSememeCAB rCab = new DynamicSememeCAB(cab.getComponentUuid(), DynamicSememe.DYNAMIC_SEMEME_EXTENSION_DEFINITION.getUuids()[0]);
				
				DynamicSememeDataBI[] data = new DynamicSememeDataBI[7];
				
				data[0] = new DynamicSememeInteger(ci.getColumnOrder());
				data[1] = new DynamicSememeUUID(ci.getColumnDescriptionConcept());
				if (DynamicSememeDataType.UNKNOWN == ci.getColumnDataType())
				{
					throw new InvalidCAB("Error in column - if default value is provided, the type cannot be polymorphic");
				}
				data[2] = new DynamicSememeString(ci.getColumnDataType().name());
				data[3] = convertPolymorphicDataColumn(ci.getDefaultColumnValue(), ci.getColumnDataType());
				data[4] = new DynamicSememeBoolean(ci.isColumnRequired());
				data[5] = (ci.getValidator() == null ? null : new DynamicSememeString(ci.getValidator().name()));
				data[6] = (ci.getValidatorData() == null ? null : convertPolymorphicDataColumn(ci.getValidatorData(), ci.getValidatorData().getRefexDataType()));
				rCab.setData(data, null);  //View Coordinate is only used to evaluate validators - but there are no validators assigned to the RefexDefinition refex
				//so we can get away with passing null
				//TODO file a another bug, this API is atrocious.  If you put the annotation on the concept, it gets silently ignored.
				cab.getConceptAttributeAB().addAnnotationBlueprint(rCab);
			}
		}
		
		if (referencedComponentRestriction != null && ComponentType.UNKNOWN != referencedComponentRestriction)
		{
			DynamicSememeCAB rCab = new DynamicSememeCAB(cab.getComponentUuid(), DynamicSememe.DYNAMIC_SEMEME_REFERENCED_COMPONENT_RESTRICTION.getUuids()[0]);
			
			DynamicSememeDataBI[] data = new DynamicSememeDataBI[1];
			data[0] = new DynamicSememeString(referencedComponentRestriction.name());
			rCab.setData(data, null);  //View Coordinate is only used to evaluate validators - but there are no validators assigned to the RefexDefinition refex
			//so we can get away with passing null
			cab.getConceptAttributeAB().addAnnotationBlueprint(rCab);
		}
		
		//Build this on the lowest level path, otherwise, other code that references this will fail (as it doesn't know about custom paths)
		ConceptChronicleBI newCon = Ts.get().getTerminologyBuilder(
				new EditCoordinate(TermAux.USER.getLenient().getNid(), 
						TermAux.ISAAC_MODULE.getLenient().getNid(), 
						DEVELOPMENT.getLenient().getConceptNid()), 
				vc).construct(cab);
		Ts.get().addUncommitted(newCon);
		Ts.get().commit();
		return new DynamicSememeUsageDescription(newCon.getNid());
	}
	
	private static DynamicSememeDataBI convertPolymorphicDataColumn(DynamicSememeDataBI defaultValue, DynamicSememeDataType columnType) 
	{
		DynamicSememeDataBI result;
		
		if (defaultValue != null)
		{
			try
			{
				if (DynamicSememeDataType.BOOLEAN == columnType)
				{
					result = (DynamicSememeBooleanBI)defaultValue;
				}
				else if (DynamicSememeDataType.BYTEARRAY == columnType)
				{
					result = (DynamicSememeByteArrayBI)defaultValue;
				}
				else if (DynamicSememeDataType.DOUBLE == columnType)
				{
					result = (DynamicSememeDoubleBI)defaultValue;
				}
				else if (DynamicSememeDataType.FLOAT == columnType)
				{
					result = (DynamicSememeFloatBI)defaultValue;
				}
				else if (DynamicSememeDataType.INTEGER == columnType)
				{
					result = (DynamicSememeIntegerBI)defaultValue;
				}
				else if (DynamicSememeDataType.LONG == columnType)
				{
					result = (DynamicSememeLongBI)defaultValue;
				}
				else if (DynamicSememeDataType.NID == columnType)
				{
					result = (DynamicSememeNidBI)defaultValue;
				}
				else if (DynamicSememeDataType.STRING == columnType)
				{
					result = (DynamicSememeStringBI)defaultValue;
				}
				else if (DynamicSememeDataType.UUID == columnType)
				{
					result = (DynamicSememeUUIDBI)defaultValue;
				}
				else if (DynamicSememeDataType.ARRAY == columnType)
				{
					result = (DynamicSememeArrayBI<?>)defaultValue;
				}
				else if (DynamicSememeDataType.SEQUENCE== columnType)
				{
					result = (DynamicSememeSequenceBI)defaultValue;
				}
				else if (DynamicSememeDataType.POLYMORPHIC == columnType)
				{
					throw new RuntimeException("Error in column - if default value is provided, the type cannot be polymorphic");
				}
				else
				{
					throw new RuntimeException("Actually, the implementation is broken.  Ooops.");
				}
			}
			catch (ClassCastException e)
			{
				throw new RuntimeException("Error in column - if default value is provided, the type must be compatible with the the column descriptor type");
			}
		}
		else
		{
			result = null;
		}
		return result;
	}

	@Override
	public String[] readDynamicSememeColumnNameDescription(UUID columnDescriptionConcept)
	{
		String columnName = null;
		String columnDescription = null;
		String fsn = null;
		String acceptableSynonym = null;
		String acceptableDefinition = null;
		try
		{
			ConceptChronology<? extends ConceptVersion<?>> cc = Get.conceptService().getConcept(columnDescriptionConcept);
			for (SememeChronology dc : cc.getConceptDescriptionList())
			{
				if (columnName != null && columnDescription != null)
				{
					break;
				}
				
				Optional<LatestVersion<DescriptionSememe>> descriptionVersion = 
						dc.getLatestVersion(DescriptionSememe.class, Get.configurationService().getDefaultStampCoordinate());
				
				if (descriptionVersion.isPresent())
				{
					DescriptionSememe<?> d = descriptionVersion.get().value();
					if (d.getAssemblageSequence() == IsaacMetadataAuxiliaryBinding.FULLY_SPECIFIED_NAME.getConceptSequence())
					{
						fsn = d.getText();
					}
					else if (d.getAssemblageSequence() == IsaacMetadataAuxiliaryBinding.SYNONYM.getConceptSequence())
					{
						AtomicReference<Boolean> isPreferred = new AtomicReference<>();
						Get.sememeService().getSememesForComponentFromAssemblage(d.getNid(), IsaacMetadataAuxiliaryBinding.DESCRIPTION_ACCEPTABILITY.getConceptSequence())
							.forEach(nestedSememe ->
						{
							if (nestedSememe.getSememeType() == SememeType.COMPONENT_NID)
							{
								if (((ComponentNidSememe<?>)nestedSememe).getComponentNid() == IsaacMetadataAuxiliaryBinding.PREFERRED.getNid())
								{
									isPreferred.set(true);
								}
								if (((ComponentNidSememe<?>)nestedSememe).getComponentNid() == IsaacMetadataAuxiliaryBinding.ACCEPTABLE.getNid())
								{
									isPreferred.set(false);
								}
							}
						});
						if (isPreferred.get() != null && isPreferred.get().booleanValue())
						{
							columnName = d.getText();
						}
						else
						{
							acceptableSynonym = d.getText();
						}
					}
					else if (d.getAssemblageSequence() == IsaacMetadataAuxiliaryBinding.DEFINITION_DESCRIPTION_TYPE.getConceptSequence())
					{
						if (OchreUtility.isPreferred(d.getNid())
						{
							columnDescription = d.getText();
						}
						else
						{
							acceptableDefinition = d.getText();
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Failure reading DynamicSememeColumnInfo '" + columnDescriptionConcept + "'", e);
		}
		if (columnName == null)
		{
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "No preferred synonym found on '" + columnDescriptionConcept + "' to use "
					+ "for the column name - using FSN");
			columnName = (fsn == null ? "ERROR - see log" : fsn);
		}
		
		if (columnDescription == null && acceptableDefinition != null)
		{
			columnDescription = acceptableDefinition;
		}
		
		if (columnDescription == null && acceptableSynonym != null)
		{
			columnDescription = acceptableSynonym;
		}
		
		if (columnDescription == null)
		{
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "No preferred or acceptable definition or acceptable synonym found on '" 
					+ columnDescriptionConcept + "' to use for the column description- re-using the the columnName, instead.");
			columnDescription = columnName;
		}
		return new String[] {columnName, columnDescription};
	}
	
	
}
