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
import gov.vha.isaac.ochre.api.Get;
import gov.vha.isaac.ochre.api.coordinate.LanguageCoordinate;
import gov.vha.isaac.ochre.model.coordinate.LanguageCoordinateImpl;

/**
 *
 * @author kec
 */
public class LanguageCoordinates {
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
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.ENGLISH.getUuids());
            case "es":
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.SPANISH.getUuids());
            case "fr": 
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.FRENCH.getUuids());
            case "da":
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.DANISH.getUuids());
            case "pl":
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.POLISH.getUuids());
            case "nl":
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.DUTCH.getUuids());
            case "lt":
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.LITHUANIAN.getUuids());
            case "zh":
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.CHINESE.getUuids());
            case "ja":
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.JAPANESE.getUuids());
            case "sv":
                return Get.identifierService().getNidForUuids(IsaacMetadataAuxiliaryBinding.SWEDISH.getUuids());
            default: 
                throw new UnsupportedOperationException("Can't handle: " + iso639text);
        }
    }
    public static int iso639toConceptSequence(String iso639text) {
        switch (iso639text.toLowerCase()) {
            case "en":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.ENGLISH.getUuids());
            case "es":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.SPANISH.getUuids());
            case "fr": 
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.FRENCH.getUuids());
            case "da":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.DANISH.getUuids());
            case "pl":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.POLISH.getUuids());
            case "nl":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.DUTCH.getUuids());
            case "lt":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.LITHUANIAN.getUuids());
            case "zh":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.CHINESE.getUuids());
            case "ja":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.JAPANESE.getUuids());
            case "sv":
                return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.SWEDISH.getUuids());
            default: 
                throw new UnsupportedOperationException("Can't handle: " + iso639text);
        }
    }
    
    public static String conceptNidToIso639(int nid) {
            if (nid >= 0) {
                nid = Get.identifierService().getConceptNid(nid);
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
            return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.INITIAL_CASE_IS_SIGNIFICANT.getUuids());
        }
        return Get.identifierService().getConceptSequenceForUuids(IsaacMetadataAuxiliaryBinding.INITIAL_CASE_IS_NOT_SIGNIFICANT.getUuids());
    }

    public static boolean conceptIdToCaseSignificance(int id) {
        int nid = Get.identifierService().getConceptNid(id);
        return IsaacMetadataAuxiliaryBinding.INITIAL_CASE_IS_SIGNIFICANT.getNid() == nid;
    }
}
