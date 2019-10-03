package modular.di.processor;

import modular.di.annotations.DiInject;
import modular.di.processor.data.ComponentDescriptor;
import modular.di.processor.data.PropertyRecordData;
import modular.di.processor.data.SetterInfo;

public class InjectSetterHandler implements SetterHandler<DiInject> {

    private final DiInject annotation;

    public InjectSetterHandler(DiInject pAnnotation) {
        this.annotation = pAnnotation;
    }

    @Override
    public PropertyRecordData processRecord(SetterInfo setterInfo) {
        final PropertyRecordData record = new PropertyRecordData();

        record.name = setterInfo.beanName;

        ComponentDescriptor toInject = ComponentDescriptor.fromStringOrParameterType(
                annotation.name(), setterInfo.parameterType);

        record.values.add(toInject.toString());

        return record;
    }
}
