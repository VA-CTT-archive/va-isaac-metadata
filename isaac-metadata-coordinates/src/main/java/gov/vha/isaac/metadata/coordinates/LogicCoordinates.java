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
import gov.vha.isaac.ochre.api.coordinate.LogicCoordinate;
import gov.vha.isaac.ochre.model.coordinate.LogicCoordinateLazyBinding;

/**
 *
 * @author kec
 */
public class LogicCoordinates {
    
    public static LogicCoordinate getStandardElProfile() {
        return new LogicCoordinateLazyBinding(IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_STATED_FORM, 
                IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS_INFERRED_FORM, 
                IsaacMetadataAuxiliaryBinding.EL_PLUS_PLUS, 
                IsaacMetadataAuxiliaryBinding.SNOROCKET);
    }
}
