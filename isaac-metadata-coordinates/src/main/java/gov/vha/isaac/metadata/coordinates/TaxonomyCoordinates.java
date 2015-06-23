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

import gov.vha.isaac.ochre.api.coordinate.LanguageCoordinate;
import gov.vha.isaac.ochre.api.coordinate.StampCoordinate;
import gov.vha.isaac.ochre.api.coordinate.TaxonomyCoordinate;
import gov.vha.isaac.ochre.api.coordinate.PremiseType;
import gov.vha.isaac.ochre.model.coordinate.TaxonomyCoordinateImpl;

/**
 *
 * @author kec
 */
public class TaxonomyCoordinates {
    public static TaxonomyCoordinate getInferredTaxonomyCoordinate(
            StampCoordinate stampCoordinate, LanguageCoordinate languageCoordinate) {
        return new TaxonomyCoordinateImpl(PremiseType.INFERRED, 
                stampCoordinate, languageCoordinate);
    }
    public static TaxonomyCoordinate getStatedTaxonomyCoordinate(
            StampCoordinate stampCoordinate, LanguageCoordinate languageCoordinate) {
        return new TaxonomyCoordinateImpl(PremiseType.STATED, 
                stampCoordinate, languageCoordinate);
    }
}
