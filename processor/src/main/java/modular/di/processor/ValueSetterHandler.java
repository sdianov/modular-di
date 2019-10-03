package modular.di.processor;

import modular.di.annotations.DiCollectionOperation;
import modular.di.annotations.DiValues;
import modular.di.processor.data.PropertyRecordData;
import modular.di.processor.data.SetterInfo;

import java.util.Arrays;

public class ValueSetterHandler implements SetterHandler<DiValues> {

    private DiValues annotation;

    public ValueSetterHandler(DiValues pAnnotation) {
        this.annotation = pAnnotation;
    }

    @Override
    public PropertyRecordData processRecord(SetterInfo setterInfo) throws ProcessingException {
        final PropertyRecordData record = new PropertyRecordData();
        record.name = setterInfo.beanName;

        boolean processed = false;

        if (!"".equals(annotation.value())) {

            if (!DiCollectionOperation.SET.equals(annotation.operation()))
                throw new ProcessingException("simple value is incompatible with ADD or REMOVE operations ");


            // todo: check setter type
            record.values.add(annotation.value());
            processed = true;
        }

        if (annotation.list().length > 0) {
            if (processed)
                throw new ProcessingException("Only one of 'value', 'list', 'map' or 'propertyRef' is allowed ");

            // todo: check setter type is array or list

            record.operation = AnnotationUtils.operatorSymbols.get(annotation.operation());

            record.values = Arrays.asList(annotation.list());
            processed = true;
        }

        if (annotation.map().length > 0) {
            if (processed)
                throw new ProcessingException("Only one of 'value', 'list', 'map' or 'propertyRef' is allowed ");

            // todo: check setter type is Map

            record.operation = AnnotationUtils.operatorSymbols.get(annotation.operation());

            for (DiValues.MapValue value : annotation.map()) {
                record.values.add(value.key() + "=" + value.value());
            }
            processed = true;
        }

        return record;
    }
}
