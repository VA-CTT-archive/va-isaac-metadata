package gov.vha.isaac.ochre.impl.utility;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.ochre.api.ConceptProxy;
import gov.vha.isaac.ochre.api.Get;
import gov.vha.isaac.ochre.api.chronicle.LatestVersion;
import gov.vha.isaac.ochre.api.component.sememe.SememeChronology;
import gov.vha.isaac.ochre.api.component.sememe.SememeType;
import gov.vha.isaac.ochre.api.component.sememe.version.ComponentNidSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.DescriptionSememe;
import gov.vha.isaac.ochre.api.coordinate.StampCoordinate;
import gov.vha.isaac.ochre.model.sememe.version.LongSememeImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Frills
{
	private static Logger log = LogManager.getLogger();
	
	/**
	 * Find the SCTID for a component (if it has one)
	 * @param componentNid
	 * @param stamp - optional - if not provided uses default from config service
	 * @return the id, if found, or empty (will not return null)
	 */
	public static Optional<Long> getSctId(int componentNid, StampCoordinate stamp)
	{
		try
		{
			Optional<LatestVersion<LongSememeImpl>> sememe = Get.sememeService().getSnapshot(LongSememeImpl.class, 
					stamp == null ? Get.configurationService().getDefaultStampCoordinate() : stamp)
						.getLatestSememeVersionsForComponentFromAssemblage(componentNid, 
								IsaacMetadataAuxiliaryBinding.SNOMED_INTEGER_ID.getConceptSequence()).findFirst();
			if (sememe.isPresent())
			{
				return Optional.of(sememe.get().value().getLongValue());
			}
		}
		catch (Exception e)
		{
			log.error("Unexpected error trying to find SCTID for nid " + componentNid, e);
		}
		return Optional.empty();
	}
	
	
	/**
	 * Determine if a particular description sememe is flagged as preferred IN ANY LANGUAGE.  Returns false if there is no acceptability sememe.
	 * @param descriptionSememeNid
	 * @param stamp - optional - if not provided, uses default from config service
	 * @throws RuntimeException If there is unexpected data (incorrectly) attached to the sememe
	 */
	public static boolean isDescriptionPreferred(int descriptionSememeNid, StampCoordinate stamp) throws RuntimeException
	{
		AtomicReference<Boolean> answer = new AtomicReference<>();
		
		//Ignore the language annotation... treat preferred in any language as good enough for our purpose here...
		Get.sememeService().getSememesForComponent(descriptionSememeNid).forEach(nestedSememe ->
			{
				if (nestedSememe.getSememeType() == SememeType.COMPONENT_NID)
				{
					@SuppressWarnings({ "rawtypes", "unchecked" })
					Optional<LatestVersion<ComponentNidSememe>> latest = ((SememeChronology)nestedSememe).getLatestVersion(ComponentNidSememe.class, 
							stamp == null ? Get.configurationService().getDefaultStampCoordinate() : stamp);
					
					if (latest.isPresent())
					{
						if (latest.get().value().getComponentNid() == IsaacMetadataAuxiliaryBinding.PREFERRED.getNid())
						{
							if (answer.get() != null && answer.get() != true)
							{
								throw new RuntimeException("contradictory annotations about preferred status!");
							}
							answer.set(true);
						}
						else if (latest.get().value().getComponentNid() == IsaacMetadataAuxiliaryBinding.ACCEPTABLE.getNid())
						{
							if (answer.get() != null && answer.get() != false)
							{
								throw new RuntimeException("contradictory annotations about preferred status!");
							}
							answer.set(false);
						}
						else
						{
							throw new RuntimeException("Unexpected component nid!");
						}
						
					}
				}
			});
		if (answer.get() == null)
		{
			log.warn("Description nid {} does not have an acceptability sememe!", descriptionSememeNid);
			return false;
		}
		return answer.get();
	}
	
	/**
	 * Convenience method to extract the latest version of descriptions of the requested type
	 * @param conceptNid The concept to read descriptions for
	 * @param descriptionType expected to be one of {@link IsaacMetadataAuxiliaryBinding#SYNONYM} or 
	 * {@link IsaacMetadataAuxiliaryBinding#FULLY_SPECIFIED_NAME} or {@link IsaacMetadataAuxiliaryBinding#DEFINITION_DESCRIPTION_TYPE}
	 * @param stamp - optional - if not provided gets the default from the config service
	 * @return the descriptions - may be empty, will not be null
	 */
	public static List<DescriptionSememe<?>> getDescriptionsOfType(int conceptNid, ConceptProxy descriptionType,
			StampCoordinate stamp)
	{
		ArrayList<DescriptionSememe<?>> results = new ArrayList<>();
		Get.sememeService().getSememesForComponentFromAssemblage(conceptNid, IsaacMetadataAuxiliaryBinding.DESCRIPTION_ASSEMBLAGE.getConceptSequence())
			.forEach(descriptionC -> 
				{
					if (descriptionC.getSememeType() == SememeType.DESCRIPTION)
					{
						@SuppressWarnings({ "unchecked", "rawtypes" })
						Optional<LatestVersion<DescriptionSememe<?>>> latest = ((SememeChronology)descriptionC).getLatestVersion(DescriptionSememe.class, 
								stamp == null ? Get.configurationService().getDefaultStampCoordinate() : stamp);
						if (latest.isPresent())
						{
							DescriptionSememe<?> ds = latest.get().value();
							if (ds.getDescriptionTypeConceptSequence() == descriptionType.getConceptSequence())
							{
								results.add(ds);
							}
						}
					}
					else
					{
						log.warn("Description attached to concept nid {} is not of the expected type!", conceptNid);
					}
				});
		return results;
	}
}
