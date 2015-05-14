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
package gov.vha.isaac.observable;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.observable.version.ObservableDescriptionImpl;
import gov.vha.isaac.ochre.api.component.concept.description.ConceptDescriptionChronology;
import gov.vha.isaac.ochre.api.observable.description.ObservableDescriptionChronology;
import gov.vha.isaac.ochre.model.description.DescriptionVersionImpl;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kec
 */
public class ObservableDescriptionChronologyImpl 
    extends 
        ObservableChronologyImpl<
            DescriptionVersionImpl, 
            ObservableDescriptionImpl, 
            ConceptDescriptionChronology<DescriptionVersionImpl>> 
    implements ObservableDescriptionChronology<ObservableDescriptionImpl> {
    
    IntegerProperty conceptSequenceProperty;

    public ObservableDescriptionChronologyImpl(
            ConceptDescriptionChronology<DescriptionVersionImpl> chronicledObjectLocal) {
        super(chronicledObjectLocal);
    }

    @Override
    protected ObservableList<? extends ObservableDescriptionImpl> getObservableVersionList() {
        return FXCollections.observableArrayList(
        chronicledObjectLocal.getVersionList().stream().map((desc) -> 
                new ObservableDescriptionImpl(desc)).collect(Collectors.toList()));
    }


    @Override
    public IntegerProperty conceptSequenceProperty() {
        if (conceptSequenceProperty == null) {
            conceptSequenceProperty = new CommitAwareIntegerProperty(this,
                    IsaacMetadataAuxiliaryBinding.CONCEPT_SEQUENCE_FOR_CHRONICLE.toExternalString(),
                    getConceptSequence());
        }
        return conceptSequenceProperty;
    }

    @Override
    public int getConceptSequence() {
        if (conceptSequenceProperty != null) {
            return conceptSequenceProperty.get();
        }
        return chronicledObjectLocal.getConceptSequence();
    }
    
}
