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

import gov.vha.isaac.observable.version.ObservableVersionImpl;
import gov.vha.isaac.ochre.api.component.concept.ConceptChronology;
import gov.vha.isaac.ochre.api.observable.concept.ObservableConceptChronology;
import gov.vha.isaac.ochre.api.observable.description.ObservableDescriptionChronology;
import gov.vha.isaac.ochre.model.ObjectVersionImpl;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author kec
 */
public class ObservableConceptChronologyImpl extends 
        ObservableChronologyImpl<
            ObjectVersionImpl,
            ObservableVersionImpl, 
            ConceptChronology<ObjectVersionImpl>> 
    implements ObservableConceptChronology<ObservableVersionImpl> {

    public ObservableConceptChronologyImpl(ConceptChronology chronicledObjectLocal) {
        super(chronicledObjectLocal);
    }

    @Override
    public ObservableList<ObservableDescriptionChronologyImpl> getConceptDescriptionList() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected ObservableList<? extends ObservableVersionImpl> getObservableVersionList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getConceptSequence() {
        return chronicledObjectLocal.getConceptSequence();
    }


    @Override
    public IntegerProperty conceptSequenceProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListProperty<ObservableDescriptionChronology> conceptDescriptionListProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
