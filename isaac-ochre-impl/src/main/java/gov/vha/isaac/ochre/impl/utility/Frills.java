package gov.vha.isaac.ochre.impl.utility;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.ochre.api.Get;
import gov.vha.isaac.ochre.api.chronicle.LatestVersion;
import gov.vha.isaac.ochre.api.component.sememe.SememeChronology;
import gov.vha.isaac.ochre.api.component.sememe.SememeSnapshotService;
import gov.vha.isaac.ochre.api.component.sememe.SememeType;
import gov.vha.isaac.ochre.api.component.sememe.version.ComponentNidSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.DescriptionSememe;
import gov.vha.isaac.ochre.api.coordinate.StampCoordinate;
import gov.vha.isaac.ochre.model.sememe.version.StringSememeImpl;

public class Frills
{
	private static Logger log = LogManager.getLogger();
	
	/**
	 * Find the SCTID for a component (if it has one)
	 * @param componentNid
	 * @param stamp - optional - if not provided uses default from config service
	 * @return the id, if found, or empty (will not return null)
	 */
	public static Optional<Long> getSctId(int componentNid, StampCoordinate<? extends StampCoordinate<?>> stamp)
	{
		try
		{
			Optional<LatestVersion<StringSememeImpl>> sememe = Get.sememeService().getSnapshot(StringSememeImpl.class, 
					stamp == null ? Get.configurationService().getDefaultStampCoordinate() : stamp)
						.getLatestSememeVersionsForComponentFromAssemblage(componentNid, 
								IsaacMetadataAuxiliaryBinding.SNOMED_INTEGER_ID.getConceptSequence()).findFirst();
			if (sememe.isPresent())
			{
				String temp = sememe.get().value().getString();  //TODO why is this loaded as a string?
				try
				{
					return Optional.of(Long.parseLong(temp));
				}
				catch (NumberFormatException e)
				{
					log.error("The found string '" + temp + "' isn't parseable as a long - as an SCTID should be - in nid " + componentNid);
				}
			}
		}
		catch (Exception e)
		{
			log.error("Unexpected error trying to find SCTID for nid " + componentNid, e);
		}
		return Optional.empty();
	}
	
	
	/**
	 * Determine if a particular description sememe is flagged as preferred.  Returns false if there is no acceptability sememe.
	 * @param descriptionSememeNid
	 * @param stamp - optional - if not provided, uses default from config service
	 * @throws RuntimeException If there is unexpected data (incorrectly) attached to the sememe
	 */
	public static boolean isDescriptionPreferred(int descriptionSememeNid, StampCoordinate<? extends StampCoordinate<?>> stamp) throws RuntimeException
	{
		AtomicReference<Boolean> answer = new AtomicReference<>();
		
		Get.sememeService().getSememesForComponentFromAssemblage(descriptionSememeNid, IsaacMetadataAuxiliaryBinding.DESCRIPTION_ACCEPTABILITY.getConceptSequence())
			.forEach(nestedSememe ->
			{
				if (nestedSememe.getSememeType() == SememeType.COMPONENT_NID)
				{
					@SuppressWarnings({ "rawtypes", "unchecked" })
					Optional<LatestVersion<ComponentNidSememe>> latest = ((SememeChronology)nestedSememe).getLatestVersion(ComponentNidSememe.class, 
							stamp == null ? Get.configurationService().getDefaultStampCoordinate() : stamp);
					
					if (latest.isPresent())
					{
						if (answer.get() != null)
						{
							throw new RuntimeException("multiple annotations about preferred status!");
						}
						if (latest.get().value().getComponentNid() == IsaacMetadataAuxiliaryBinding.PREFERRED.getNid())
						{
							answer.set(true);
						}
						else if (latest.get().value().getComponentNid() == IsaacMetadataAuxiliaryBinding.ACCEPTABLE.getNid())
						{
							answer.set(false);
						}
						else
						{
							throw new RuntimeException("Unexpected component nid!");
						}
						
					}
				}
				else
				{
					throw new RuntimeException("Unexpected - sememe type should have been component on a description_acceptability sememe");
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
	 * @param nid concept nid (must be a nid)
	 * @param stamp - optional (uses default from config service, if not provided)
	 * @return the text of the description, if found
	 */
	@SuppressWarnings("rawtypes")
	public static Optional<String> getPreferredTermForConceptNid(int nid, StampCoordinate<? extends StampCoordinate<?>> stamp)
	{
		SememeSnapshotService<DescriptionSememe> ss = Get.sememeService().getSnapshot(DescriptionSememe.class, 
				stamp == null ? Get.configurationService().getDefaultStampCoordinate() : stamp); 
		
		Stream<LatestVersion<DescriptionSememe>> descriptions = ss.getLatestDescriptionVersionsForComponent(nid);
		Optional<LatestVersion<DescriptionSememe>> desc = descriptions.filter((LatestVersion<DescriptionSememe> d) -> 
		{
			if (d.value().getDescriptionTypeConceptSequence() == IsaacMetadataAuxiliaryBinding.SYNONYM.getConceptSequence()
					&& isDescriptionPreferred(d.value().getNid(), stamp)) 
			{
				return true;
			}
			return false;
		}).findFirst();
		
		if (desc.isPresent())
		{
			return Optional.of(desc.get().value().getText());
		}
		else return Optional.empty();
	}
}
