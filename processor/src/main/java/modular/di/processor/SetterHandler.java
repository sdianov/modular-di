package modular.di.processor;

import modular.di.processor.data.PropertyRecordData;
import modular.di.processor.data.SetterInfo;

import java.lang.annotation.Annotation;

public interface SetterHandler<T extends Annotation> {

    PropertyRecordData processRecord(SetterInfo setterInfo) throws ProcessingException;

}
