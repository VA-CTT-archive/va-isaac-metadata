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

import gov.vha.isaac.observable.version.ObservableSememeImpl;
import gov.vha.isaac.ochre.api.State;
import gov.vha.isaac.ochre.api.component.sememe.SememeChronology;
import gov.vha.isaac.ochre.api.component.sememe.SememeType;
import gov.vha.isaac.ochre.api.component.sememe.version.ComponentNidSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.ConceptSequenceSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.ConceptSequenceTimeSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.DynamicSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.LogicGraphSememe;
import gov.vha.isaac.ochre.api.component.sememe.version.SememeVersion;
import gov.vha.isaac.ochre.api.component.sememe.version.StringSememe;
import gov.vha.isaac.ochre.api.coordinate.EditCoordinate;
import gov.vha.isaac.ochre.api.observable.sememe.ObservableSememeChronology;
import gov.vha.isaac.ochre.model.sememe.version.ComponentNidSememeImpl;
import gov.vha.isaac.ochre.model.sememe.version.ConceptSequenceSememeImpl;
import gov.vha.isaac.ochre.model.sememe.version.ConceptSequenceTimeSememeImpl;
import gov.vha.isaac.ochre.model.sememe.version.DynamicSememeImpl;
import gov.vha.isaac.ochre.model.sememe.version.LogicGraphSememeImpl;
import gov.vha.isaac.ochre.model.sememe.version.SememeVersionImpl;
import gov.vha.isaac.ochre.model.sememe.version.StringSememeImpl;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author kec
 * @param <SV>
 * @param <OV>
 * @param <C>
 */
public class ObservableSememeChronologyImpl<
        SV extends SememeVersionImpl,
        OV extends ObservableSememeImpl, 
        C extends SememeChronology<SV>>
    extends ObservableChronologyImpl<SV, OV, C> 

    implements ObservableSememeChronology<OV> {
 
    public ObservableSememeChronologyImpl(C chronicledObjectLocal) {
        super(chronicledObjectLocal);
    }

    @Override
    protected ObservableList<? extends OV> getObservableVersionList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntegerProperty sememeSequenceProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntegerProperty assemblageSequenceProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntegerProperty referencedComponentNidProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private <M extends OV> Class<SV> getSvForOv(Class<M> type) {
        if (type.isAssignableFrom(LogicGraphSememe.class)) {
            return (Class<SV>) LogicGraphSememeImpl.class;
        }
        if (type.isAssignableFrom(StringSememe.class)) {
            return (Class<SV>) StringSememeImpl.class;
        }
        if (type.isAssignableFrom(DynamicSememe.class)) {
            return (Class<SV>) DynamicSememeImpl.class;
        }
        if (type.isAssignableFrom(ConceptSequenceTimeSememe.class)) {
            return (Class<SV>) ConceptSequenceTimeSememeImpl.class;
        }
        if (type.isAssignableFrom(ConceptSequenceSememe.class)) {
            return (Class<SV>) ConceptSequenceSememeImpl.class;
        }
        if (type.isAssignableFrom(ComponentNidSememe.class)) {
            return (Class<SV>) ComponentNidSememeImpl.class;
        }
        if (type.isAssignableFrom(SememeVersion.class)) {
            return (Class<SV>) SememeVersionImpl.class;
        }
        throw new UnsupportedOperationException("Can't convert " + type);
    }

        private OV wrapInObservable(SV sememeVersion) {
        throw new UnsupportedOperationException("Can't convert " + sememeVersion);
    }

    @Override
    public <M extends OV> M createMutableUncommittedVersion(Class<M> type, State status, EditCoordinate ec) {
        SV sememeVersion = chronicledObjectLocal.createMutableUncommittedVersion(getSvForOv(type), status, ec);
        return (M) wrapInObservable(sememeVersion);
    }

    @Override
    public <M extends OV> M createMutableStampedVersion(Class<M> type, int stampSequence) {
        SV mutableStampedVersion = chronicledObjectLocal.createMutableStampedVersion(getSvForOv(type), stampSequence);
        return (M) wrapInObservable(mutableStampedVersion);
    }

    @Override
    public SememeType getSememeType() {
        return chronicledObjectLocal.getSememeType();
    }

    @Override
    public int getSememeSequence() {
        return chronicledObjectLocal.getSememeSequence();
    }

    @Override
    public int getAssemblageSequence() {
        return chronicledObjectLocal.getAssemblageSequence();
    }

    @Override
    public int getReferencedComponentNid() {
        return chronicledObjectLocal.getReferencedComponentNid();
    }
}
