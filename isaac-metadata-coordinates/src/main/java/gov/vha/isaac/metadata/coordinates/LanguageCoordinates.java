/*
 * Copyright 2015 kec.
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
import gov.vha.isaac.ochre.api.IdentifierService;
import gov.vha.isaac.ochre.api.LookupService;
import gov.vha.isaac.ochre.api.coordinate.LanguageCoordinate;
import gov.vha.isaac.ochre.model.coordinate.LanguageCoordinateImpl;

/**
 *
 * @author kec
 */
public class LanguageCoordinates {
    private static final IdentifierService identifierService = 
            LookupService.getService(IdentifierService.class);
    public static LanguageCoordinate getUsEnglishLanguagePreferredTermCoordinate() {
        int languageSequence = IsaacMetadataAuxiliaryBinding.ENGLISH.getSequence();
        int[] dialectAssemblagePreferenceList = new int[] {
            IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT.getSequence(),
            IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT.getSequence()
        };
        int[] descriptionTypePreferenceList = new int[] {
            IsaacMetadataAuxiliaryBinding.SYNONYM.getSequence(),
            IsaacMetadataAuxiliaryBinding.FULLY_SPECIFIED_NAME.getSequence()
        };
        
        return new LanguageCoordinateImpl(languageSequence, 
                dialectAssemblagePreferenceList, descriptionTypePreferenceList);
    }

    public static LanguageCoordinate getUsEnglishLanguageFullySpecifiedNameCoordinate() {
        int languageSequence = IsaacMetadataAuxiliaryBinding.ENGLISH.getSequence();
        int[] dialectAssemblagePreferenceList = new int[] {
            IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT.getSequence(),
            IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT.getSequence()
        };
        int[] descriptionTypePreferenceList = new int[] {
            IsaacMetadataAuxiliaryBinding.FULLY_SPECIFIED_NAME.getSequence(),
            IsaacMetadataAuxiliaryBinding.SYNONYM.getSequence()
        };
        
        return new LanguageCoordinateImpl(languageSequence, 
                dialectAssemblagePreferenceList, descriptionTypePreferenceList);
    }
    public static LanguageCoordinate getGbEnglishLanguagePreferredTermCoordinate() {
        int languageSequence = IsaacMetadataAuxiliaryBinding.ENGLISH.getSequence();
        int[] dialectAssemblagePreferenceList = new int[] {
            IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT.getSequence(),
            IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT.getSequence()
        };
        int[] descriptionTypePreferenceList = new int[] {
            IsaacMetadataAuxiliaryBinding.SYNONYM.getSequence(),
            IsaacMetadataAuxiliaryBinding.FULLY_SPECIFIED_NAME.getSequence()
        };
        
        return new LanguageCoordinateImpl(languageSequence, 
                dialectAssemblagePreferenceList, descriptionTypePreferenceList);
    }

    public static LanguageCoordinate getGbEnglishLanguageFullySpecifiedNameCoordinate() {
        int languageSequence = IsaacMetadataAuxiliaryBinding.ENGLISH.getSequence();
        int[] dialectAssemblagePreferenceList = new int[] {
            IsaacMetadataAuxiliaryBinding.GB_ENGLISH_DIALECT.getSequence(),
            IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT.getSequence()
        };
        int[] descriptionTypePreferenceList = new int[] {
            IsaacMetadataAuxiliaryBinding.FULLY_SPECIFIED_NAME.getSequence(),
            IsaacMetadataAuxiliaryBinding.SYNONYM.getSequence()
        };
        
        return new LanguageCoordinateImpl(languageSequence, 
                dialectAssemblagePreferenceList, descriptionTypePreferenceList);
    }
    
    public static int iso639toConceptNid(String iso639text) {
        switch (iso639text.toLowerCase()) {
            case "en":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.ENGLISH.getUuids());
            case "es":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.SPANISH.getUuids());
            case "fr": 
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.FRENCH.getUuids());
            case "da":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.DANISH.getUuids());
            case "pl":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.POLISH.getUuids());
            case "nl":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.DUTCH.getUuids());
            case "lt":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.LITHUANIAN.getUuids());
            case "zh":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.CHINESE.getUuids());
            case "ja":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.JAPANESE.getUuids());
            case "sv":
                return identifierService.getNidForUuids(IsaacMetadataAuxiliaryBinding.SWEDISH.getUuids());
            default: 
                throw new UnsupportedOperationException("Can't handle: " + iso639text);
        }
    }
    public static int iso639toConceptSequence(String iso639text) {
        switch (iso639text.toLowerCase()) {
            case "en":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.ENGLISH.getUuids());
            case "es":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.SPANISH.getUuids());
            case "fr": 
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.FRENCH.getUuids());
            case "da":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.DANISH.getUuids());
            case "pl":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.POLISH.getUuids());
            case "nl":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.DUTCH.getUuids());
            case "lt":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.LITHUANIAN.getUuids());
            case "zh":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.CHINESE.getUuids());
            case "ja":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.JAPANESE.getUuids());
            case "sv":
                return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.SWEDISH.getUuids());
            default: 
                throw new UnsupportedOperationException("Can't handle: " + iso639text);
        }
    }
    
    public static String conceptNidToIso639(int nid) {
            if (nid >= 0) {
                nid = identifierService.getConceptNid(nid);
            }
            if (IsaacMetadataAuxiliaryBinding.ENGLISH.getNid() == nid) {
                return "en";
            }
            if (IsaacMetadataAuxiliaryBinding.SPANISH.getNid() == nid) {
                return "es";
            }
            if (IsaacMetadataAuxiliaryBinding.FRENCH.getNid() == nid) {
                return "fr";
            }
            if (IsaacMetadataAuxiliaryBinding.DANISH.getNid() == nid) {
                return "da";
            }
            if (IsaacMetadataAuxiliaryBinding.POLISH.getNid() == nid) {
                return "pl";
            }
            if (IsaacMetadataAuxiliaryBinding.DUTCH.getNid() == nid) {
                return "nl";
            }
            if (IsaacMetadataAuxiliaryBinding.LITHUANIAN.getNid() == nid) {
                return "lt";
            }
            if (IsaacMetadataAuxiliaryBinding.CHINESE.getNid() == nid) {
                return "zh";
            }
            if (IsaacMetadataAuxiliaryBinding.JAPANESE.getNid() == nid) {
                return "ja";
            }
            if (IsaacMetadataAuxiliaryBinding.SWEDISH.getNid() == nid) {
                return "sv";
            }
            throw new UnsupportedOperationException("Can't handle: " + nid);
    }
    
    public static int caseSignificanceToConceptSequence(boolean initialCaseSignificant) {
        if (initialCaseSignificant) {
            return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.INITIAL_CASE_IS_SIGNIFICANT.getUuids());
        }
        return identifierService.getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.INITIAL_CASE_IS_NOT_SIGNIFICANT.getUuids());
    }
}
