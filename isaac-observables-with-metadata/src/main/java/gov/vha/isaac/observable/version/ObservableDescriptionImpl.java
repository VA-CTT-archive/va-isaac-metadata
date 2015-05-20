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
package gov.vha.isaac.observable.version;

import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.observable.CommitAwareIntegerProperty;
import gov.vha.isaac.observable.CommitAwareStringProperty;
import gov.vha.isaac.ochre.api.observable.description.ObservableDescription;
import gov.vha.isaac.ochre.model.description.DescriptionVersionImpl;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kec
 */
public class ObservableDescriptionImpl 
    extends ObservableVersionImpl<DescriptionVersionImpl> 
    implements ObservableDescription {
    
    IntegerProperty caseSignificanceConceptSequenceProperty;
    IntegerProperty languageConceptSequenceProperty;
    StringProperty textProperty;
    IntegerProperty descriptionTypeConceptSequenceProperty;
    

    public ObservableDescriptionImpl(DescriptionVersionImpl stampedVersion) {
        super(stampedVersion);
    }
    
    @Override
    public IntegerProperty caseSignificanceConceptSequenceProperty() {
        if (caseSignificanceConceptSequenceProperty == null) {
            caseSignificanceConceptSequenceProperty = new CommitAwareIntegerProperty(this,
                    IsaacMetadataAuxiliaryBinding.CASE_SIGNIFICANCE_CONCEPT_SEQUENCE_FOR_DESCRIPTION.toExternalString(),
                    getCaseSignificanceConceptSequence());
        }
        return caseSignificanceConceptSequenceProperty;
    }

    @Override
    public IntegerProperty languageConceptSequenceProperty() {
        if (languageConceptSequenceProperty == null) {
            languageConceptSequenceProperty = new CommitAwareIntegerProperty(this,
                    IsaacMetadataAuxiliaryBinding.LANGUAGE_CONCEPT_SEQUENCE_FOR_DESCRIPTION.toExternalString(),
                    getLanguageConceptSequence());
        }
        return languageConceptSequenceProperty;
    }

    @Override
    public StringProperty textProperty() {
        if (textProperty == null) {
            textProperty = new CommitAwareStringProperty(this,
                    IsaacMetadataAuxiliaryBinding.TEXT_FOR_DESCRIPTION.toExternalString(),
                    getText());
        }
        return textProperty;
   }

    @Override
    public IntegerProperty descriptionTypeConceptSequenceProperty() {
        if (descriptionTypeConceptSequenceProperty == null) {
            descriptionTypeConceptSequenceProperty = new CommitAwareIntegerProperty(this,
                    IsaacMetadataAuxiliaryBinding.DESCRIPTION_TYPE_FOR_DESCRIPTION.toExternalString(),
                    getDescriptionTypeConceptSequence());
        }
        return descriptionTypeConceptSequenceProperty;
    }

   @Override
    public int getCaseSignificanceConceptSequence() {
        if (caseSignificanceConceptSequenceProperty != null) {
            return caseSignificanceConceptSequenceProperty.get();
        }
        return stampedVersion.getCaseSignificanceConceptSequence();
    }
    @Override
    public void setCaseSignificanceConceptSequence(int caseSignificanceConceptSequence) {
        if (caseSignificanceConceptSequenceProperty != null) {
            caseSignificanceConceptSequenceProperty.set(caseSignificanceConceptSequence);
        } else {
            stampedVersion.setCaseSignificanceConceptSequence(caseSignificanceConceptSequence);
        }
    }

    @Override
    public int getLanguageConceptSequence() {
        if (languageConceptSequenceProperty != null) {
            return languageConceptSequenceProperty.get();
        }
        return stampedVersion.getLanguageConceptSequence();
    }
    @Override
    public void setLanguageConceptSequence(int languageConceptSequence) {
        if (languageConceptSequenceProperty != null) {
            languageConceptSequenceProperty.set(languageConceptSequence);
        } else {
            stampedVersion.setLanguageConceptSequence(languageConceptSequence);
        }
    }


    @Override
    public String getText() {
        if (textProperty != null) {
            return textProperty.get();
        }
        return stampedVersion.getText();
    }

    @Override
    public void setText(String text) {
        if (textProperty != null) {
            textProperty.set(text);
        }
        stampedVersion.setText(text);
    }
    
    @Override
    public int getDescriptionTypeConceptSequence() {
        if (descriptionTypeConceptSequenceProperty != null) {
            return descriptionTypeConceptSequenceProperty.get();
        }
        return stampedVersion.getDescriptionTypeConceptSequence();
    }

    @Override
    public void setDescriptionTypeConceptSequence(int descriptionTypeConceptSequence) {
        if (descriptionTypeConceptSequenceProperty != null) {
            descriptionTypeConceptSequenceProperty.set(descriptionTypeConceptSequence);
        }
        stampedVersion.setDescriptionTypeConceptSequence(descriptionTypeConceptSequence);
    }
    
}
