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
                return IsaacMetadataAuxiliaryBinding.ENGLISH.getNid();
            case "es":
                return IsaacMetadataAuxiliaryBinding.SPANISH.getNid();
            case "fr": 
                return IsaacMetadataAuxiliaryBinding.FRENCH.getNid();
            case "da":
                return IsaacMetadataAuxiliaryBinding.DANISH.getNid();
            case "pl":
                return IsaacMetadataAuxiliaryBinding.POLISH.getNid();
            case "nl":
                return IsaacMetadataAuxiliaryBinding.DUTCH.getNid();
            case "lt":
                return IsaacMetadataAuxiliaryBinding.LITHUANIAN.getNid();
            case "zh":
                return IsaacMetadataAuxiliaryBinding.CHINESE.getNid();
            case "ja":
                return IsaacMetadataAuxiliaryBinding.JAPANESE.getNid();
            case "sv":
                return IsaacMetadataAuxiliaryBinding.SWEDISH.getNid();
            default: 
                throw new UnsupportedOperationException("Can't handle: " + iso639text);
        }
    }
    
    public static String conceptNidToIso639(int nid) {
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
    
    
}
