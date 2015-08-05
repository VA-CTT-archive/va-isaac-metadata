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
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.vha.isaac.ochre.impl.sememe;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.ochre.api.Get;
import gov.vha.isaac.ochre.api.component.concept.ConceptChronology;
import gov.vha.isaac.ochre.api.component.concept.ConceptVersion;
import gov.vha.isaac.ochre.api.component.sememe.SememeChronology;
import gov.vha.isaac.ochre.api.component.sememe.SememeType;
import gov.vha.isaac.ochre.api.component.sememe.version.ComponentNidSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.DescriptionSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeColumnInfoBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeDataBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeDataType;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeUsageDescriptionBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeValidatorType;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeFloatBI;
import gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.dataTypes.DynamicSememeStringBI;


/**
 * {@link DynamicSememeColumnInfo}
 * 
 * A user friendly class for containing the information parsed out of the Assemblage concepts which defines the DynamicSememe.
 * See the class description for {@link DynamicSememeUsageDescriptionBI} for more details.
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */

public class DynamicSememeColumnInfo implements DynamicSememeColumnInfoBI
{
	private UUID columnDescriptionConceptUUID_;
	private transient String columnName_;
	private transient String columnDescription_;
	private int columnOrder_;
	private UUID assemblageConcept_;
	private DynamicSememeDataType columnDataType_;
	private DynamicSememeDataBI defaultData_;
	private boolean columnRequired_;
	private DynamicSememeValidatorType validatorType_;
	private DynamicSememeDataBI validatorData_;

	/**
	 * Useful for building up a new one step by step
	 */
	public DynamicSememeColumnInfo()
	{
	}
	
	/**
	 * calls {@link #DynamicSememeColumnInfo(UUID, int, UUID, DynamicSememeDataType, DynamicSememeDataBI, Boolean, DynamicSememeValidatorType, DynamicSememeDataBI)
	 * with a null assemblage concept
	 */
	public DynamicSememeColumnInfo(int columnOrder, UUID columnDescriptionConcept, DynamicSememeDataType columnDataType, DynamicSememeDataBI defaultData, Boolean columnRequired,
			DynamicSememeValidatorType validatorType, DynamicSememeDataBI validatorData)
	{
		this(null, columnOrder, columnDescriptionConcept, columnDataType, defaultData, columnRequired, validatorType, validatorData);
	}
	
	/**
	 * Create this object by reading the columnName and columnDescription from the provided columnDescriptionConcept.
	 * 
	 * If a suitable concept to use for the column Name/Description does not yet exist, see 
	 * {@link DynamicSememeColumnInfo#createNewDynamicSememeColumnInfoConcept(String, String)}
	 * 
	 * and pass the result in here.
	 * 
	 * @param assemblageConcept - the assemblage concept that this was read from (or null, if not yet part of an assemblage)
	 * @param columnOrder - the column order as defined in the assemblage concept
	 * @param columnDescriptionConcept - The concept where columnName and columnDescription should be read from
	 * @param columnDataType - the data type as defined in the assemblage concept
	 * @param defaultData - The type of this Object must align with the data type specified in columnDataType.  For example, 
	 * if columnDataType is set to {@link DynamicSememeDataType#FLOAT} then this field must be a {@link DynamicSememeFloatBI}.
	 * @param columnRequired - Is this column required when creating an instance of the refex?  True for yes, false or null for no.
	 * @param validatorType - The Validator to use when creating an instance of this Refex.  Null for no validator
	 * @param validatorData - The data required to execute the validatorType specified.  The format and type of this will depend on the 
	 * validatorType field.  See {@link DynamicSememeValidatorType} for details on the valid data for this field.  Should be null when validatorType is null. 
	 */
	public DynamicSememeColumnInfo(UUID assemblageConcept, int columnOrder, UUID columnDescriptionConcept, DynamicSememeDataType columnDataType, DynamicSememeDataBI defaultData,
			Boolean columnRequired, DynamicSememeValidatorType validatorType, DynamicSememeDataBI validatorData)
	{
		assemblageConcept_ = assemblageConcept;
		columnOrder_ = columnOrder;
		columnDescriptionConceptUUID_ = columnDescriptionConcept;
		columnDataType_ = columnDataType;
		defaultData_ = defaultData;
		columnRequired_ = (columnRequired == null ? false : columnRequired);
		validatorType_ = validatorType;
		validatorData_ = validatorData;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#setAssemblageConcept(java.util.UUID)
	 */
	@Override
	public void setAssemblageConcept(UUID assemblageConcept)
	{
		assemblageConcept_ = assemblageConcept;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#setColumnOrder(int)
	 */
	@Override
	public void setColumnOrder(int columnOrder)
	{
		columnOrder_ = columnOrder;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#setColumnDescriptionConcept(java.util.UUID)
	 */
	@Override
	public void setColumnDescriptionConcept(UUID columnDescriptionConcept)
	{
		columnDescriptionConceptUUID_ = columnDescriptionConcept;
		columnName_ = null;
		columnDescription_ = null;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#setColumnDataType(gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeDataType)
	 */
	@Override
	public void setColumnDataType(DynamicSememeDataType columnDataType)
	{
		columnDataType_ = columnDataType;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#setColumnDefaultData(gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeDataBI)
	 */
	@Override
	public void setColumnDefaultData(DynamicSememeDataBI defaultData)
	{
		defaultData_ = defaultData;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#setColumnRequired(boolean)
	 */
	@Override
	public void setColumnRequired(boolean columnRequired)
	{
		columnRequired_ = columnRequired;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#setValidatorType(gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeValidatorType)
	 */
	@Override
	public void setValidatorType(DynamicSememeValidatorType validatorType)
	{
		validatorType_ = validatorType;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#setValidatorData(gov.vha.isaac.ochre.api.component.sememe.version.dynamicSememe.DynamicSememeDataBI)
	 */
	@Override
	public void setValidatorData(DynamicSememeDataBI validatorData)
	{
		validatorData_ = validatorData;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getColumnName()
	 */
	@Override
	public String getColumnName()
	{
		if (columnName_ == null)
		{
			read();
		}
		return columnName_;
	}

	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getColumnDescription()
	 */
	@Override
	public String getColumnDescription()
	{
		if (columnDescription_ == null)
		{
			read();
		}
		return columnDescription_;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getAssemblageConcept()
	 */
	@Override
	public UUID getAssemblageConcept()
	{
		return assemblageConcept_;
	}

	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getColumnOrder()
	 */
	@Override
	public int getColumnOrder()
	{
		return columnOrder_;
	}

	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getColumnDataType()
	 */
	@Override
	public DynamicSememeDataType getColumnDataType()
	{
		return columnDataType_;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getDefaultColumnValue()
	 */
	@Override
	public DynamicSememeDataBI getDefaultColumnValue()
	{
		//Handle folks sending empty strings gracefully
		if (defaultData_ != null && defaultData_ instanceof DynamicSememeStringBI && ((DynamicSememeStringBI)defaultData_).getDataString().length() == 0)
		{
			return null;
		}
		return defaultData_;
	}

	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#isColumnRequired()
	 */
	@Override
	public boolean isColumnRequired()
	{
		return columnRequired_;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getValidator()
	 */
	@Override
	public DynamicSememeValidatorType getValidator()
	{
		return validatorType_;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getValidatorData()
	 */
	@Override
	public DynamicSememeDataBI getValidatorData()
	{
		return validatorData_;
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#getColumnDescriptionConcept()
	 */
	@Override
	public UUID getColumnDescriptionConcept()
	{
		return columnDescriptionConceptUUID_;
	}
	
	private void read()
	{
		//TODO (artf231856) [REFEX] figure out language details
		String fsn = null;
		String acceptableSynonym = null;
		String acceptableDefinition = null;
		try
		{
			ConceptChronology<? extends ConceptVersion<?>> cc = Get.conceptService().getConcept(columnDescriptionConceptUUID_);
			for (@SuppressWarnings("rawtypes") SememeChronology<? extends DescriptionSememe> dc : cc.getConceptDescriptionList())
			{
				if (columnName_ != null && columnDescription_ != null)
				{
					break;
				}
				for (DescriptionSememe<?> d : dc.getVersionList())
				{
					if (columnName_ != null && columnDescription_ != null)
					{
						break;
					}
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
							columnName_ = d.getText();
						}
						else
						{
							acceptableSynonym = d.getText();
						}
					}
					else if (d.getAssemblageSequence() == IsaacMetadataAuxiliaryBinding.DEFINITION_DESCRIPTION_TYPE.getConceptSequence())
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
							columnDescription_ = d.getText();
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
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Failure reading DynamicSememeColumnInfo '" + columnDescriptionConceptUUID_ + "'", e);
		}
		if (columnName_ == null)
		{
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "No preferred synonym found on '" + columnDescriptionConceptUUID_ + "' to use "
					+ "for the column name - using FSN");
			columnName_ = (fsn == null ? "ERROR - see log" : fsn);
		}
		
		if (columnDescription_ == null && acceptableDefinition != null)
		{
			columnDescription_ = acceptableDefinition;
		}
		
		if (columnDescription_ == null && acceptableSynonym != null)
		{
			columnDescription_ = acceptableSynonym;
		}
		
		if (columnDescription_ == null)
		{
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "No preferred or acceptable definition or acceptable synonym found on '" 
					+ columnDescriptionConceptUUID_ + "' to use for the column description- re-using the the columnName, instead.");
			columnDescription_ = columnName_;
		}
	}
	
	/* (non-Javadoc)
	 * @see gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfoBI#compareTo(gov.vha.isaac.ochre.impl.sememe.DynamicSememeColumnInfo)
	 */
	@Override
	public int compareTo(DynamicSememeColumnInfoBI o)
	{
		return Integer.compare(this.getColumnOrder(), o.getColumnOrder());
	}
}
