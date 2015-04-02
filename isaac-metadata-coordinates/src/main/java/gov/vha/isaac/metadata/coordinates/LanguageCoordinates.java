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
    
}
