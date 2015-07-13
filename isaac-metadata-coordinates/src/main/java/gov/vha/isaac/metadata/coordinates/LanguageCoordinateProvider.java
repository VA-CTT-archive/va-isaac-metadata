/*
 * Copyright 2015 U.S. Department of Veterans Affairs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.vha.isaac.metadata.coordinates;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.ochre.api.Get;
import gov.vha.isaac.ochre.api.LanguageCoordinateService;
import gov.vha.isaac.ochre.api.chronicle.LatestVersion;
import gov.vha.isaac.ochre.api.component.sememe.SememeChronology;
import gov.vha.isaac.ochre.api.component.sememe.SememeSnapshotService;
import gov.vha.isaac.ochre.api.component.sememe.version.ComponentNidSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.DescriptionSememe;
import gov.vha.isaac.ochre.api.coordinate.LanguageCoordinate;
import gov.vha.isaac.ochre.api.coordinate.StampCoordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author kec
 */
@Service
public class LanguageCoordinateProvider implements LanguageCoordinateService {
    
    @Override
    public LanguageCoordinate getUsEnglishLanguagePreferredTermCoordinate() {
        return LanguageCoordinates.getUsEnglishLanguagePreferredTermCoordinate();
    }
    
    @Override
    public LanguageCoordinate getUsEnglishLanguageFullySpecifiedNameCoordinate() {
        return LanguageCoordinates.getUsEnglishLanguageFullySpecifiedNameCoordinate();
    }
    
    @Override
    public LanguageCoordinate getGbEnglishLanguagePreferredTermCoordinate() {
        return LanguageCoordinates.getGbEnglishLanguagePreferredTermCoordinate();
    }
    
    public static LanguageCoordinate getGbEnglishLanguageFullySpecifiedNameCoordinate() {
        return LanguageCoordinates.getGbEnglishLanguageFullySpecifiedNameCoordinate();
    }
    
    @Override
    public int iso639toConceptNid(String iso639text) {
        return LanguageCoordinates.iso639toConceptNid(iso639text);
    }
    
    @Override
    public int iso639toConceptSequence(String iso639text) {
        return LanguageCoordinates.iso639toConceptSequence(iso639text);
    }
    
    @Override
    public String conceptIdToIso639(int nid) {
        return LanguageCoordinates.conceptNidToIso639(nid);
    }
    
    @Override
    public int caseSignificanceToConceptSequence(boolean initialCaseSignificant) {
        return LanguageCoordinates.caseSignificanceToConceptSequence(initialCaseSignificant);
    }

    @Override
    public boolean conceptIdToCaseSignificance(int id) {
        return LanguageCoordinates.conceptIdToCaseSignificance(id);
    }
    
    

    @Override
    public int getFullySpecifiedConceptSequence() {
        return IsaacMetadataAuxiliaryBinding.FULLY_SPECIFIED_NAME.getSequence();
    }

    @Override
    public int getSynonymConceptSequence() {
        return IsaacMetadataAuxiliaryBinding.SYNONYM.getSequence();
    }

    @Override
    public int getPreferredConceptSequence() {
        return IsaacMetadataAuxiliaryBinding.PREFERRED.getSequence();
    }

    @Override
    public int getAcceptableConceptSequence() {
        return IsaacMetadataAuxiliaryBinding.ACCEPTABLE.getSequence();
    }

    @Override
    public <V extends DescriptionSememe, C extends SememeChronology<V>> Optional<LatestVersion<V>> getSpecifiedDescription(StampCoordinate stampCoordinate, List<C> descriptionList, LanguageCoordinate languageCoordinate) {
        for (int descType: languageCoordinate.getDescriptionTypePreferenceList()) {
            Optional<LatestVersion<V>>  match = getSpecifiedDescription(stampCoordinate, 
            descriptionList,  descType, languageCoordinate);
            if (match.isPresent()) {
                return match;
            }
        }
        return Optional.empty();
    }
    
    @Override
    public <V extends DescriptionSememe, C extends SememeChronology<V>> Optional<LatestVersion<V>> getSpecifiedDescription(StampCoordinate stampCoordinate, 
            List<C> descriptionList, 
            int typeSequence, LanguageCoordinate languageCoordinate) {
        SememeSnapshotService<ComponentNidSememe> acceptabilitySnapshot = Get.sememeService().getSnapshot(ComponentNidSememe.class, stampCoordinate);
        
        List<DescriptionSememe> descriptionsForLanguageOfType = new ArrayList();

        descriptionList.stream().forEach((SememeChronology descriptionChronicle) -> {
            Optional<LatestVersion<V>> latestDescription
                    = descriptionChronicle.getLatestVersion(DescriptionSememe.class, stampCoordinate);
            if (latestDescription.isPresent()) {
                LatestVersion<V> latestDescriptionVersion = latestDescription.get();
                latestDescriptionVersion.versionStream().forEach((descriptionVersion) -> {
                    if (descriptionVersion.getLanguageConceptSequence() == languageCoordinate.getLanugageConceptSequence()) {
                        if (descriptionVersion.getDescriptionTypeConceptSequence() == typeSequence) {
                            descriptionsForLanguageOfType.add(descriptionVersion);
                        }
                    }
                });

            }
        });

        if (descriptionsForLanguageOfType.isEmpty()) {
            return Optional.empty();
        }

        // handle dialect...
        LatestVersion<DescriptionSememe> preferredForDialect = new LatestVersion<>(DescriptionSememe.class);
        IntStream.of(languageCoordinate.getDialectAssemblagePreferenceList()).forEach((dialectAssemblageSequence) -> {
            if (preferredForDialect.value() == null) {
                descriptionsForLanguageOfType.forEach((DescriptionSememe fsn) -> {
                    Stream<LatestVersion<ComponentNidSememe>> acceptability
                            = acceptabilitySnapshot.getLatestSememeVersionsForComponentFromAssemblage(fsn.getNid(), dialectAssemblageSequence);

                    if (acceptability.anyMatch((LatestVersion<ComponentNidSememe> acceptabilityVersion) -> {
                        return acceptabilityVersion.value().getComponentNid() == getPreferredConceptSequence();
                    })) {
                        preferredForDialect.addLatest(fsn);
                    }
                });
            }
        });

        if (preferredForDialect.value() == null) {
            descriptionsForLanguageOfType.forEach((fsn) -> {preferredForDialect.addLatest(fsn);});
        }
        if (preferredForDialect.value() == null) { 
            return Optional.empty();
        }
        return Optional.of((LatestVersion<V>) preferredForDialect);
    }
    
}
