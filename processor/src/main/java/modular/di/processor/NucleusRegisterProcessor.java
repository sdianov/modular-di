package modular.di.processor;

import modular.di.annotations.DiComponent;
import modular.di.annotations.DiRegister;
import modular.di.processor.data.ComponentDescriptor;
import modular.di.processor.data.ComponentPropertyDescriptor;
import modular.di.processor.data.PropertyFileData;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes({
        "com.github.sdianov.atgannotations.NucleusRegister"
})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(AnnotationUtils.ATG_GEN_OPTION)
public class NucleusRegisterProcessor extends BaseProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Multimap<ComponentPropertyDescriptor, ComponentDescriptor> registryMap = HashMultimap.create();

        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {

                if (element.getAnnotation(DiComponent.class) == null) {
                    logError("NucleusRegister annotation should be used with NucleusComponent");
                    return true;
                }
                DiRegister registerAnnotation = element.getAnnotation(DiRegister.class);
                DiComponent componentAnnotation = element.getAnnotation(DiComponent.class);


                ComponentPropertyDescriptor registry = new ComponentPropertyDescriptor(
                        ComponentDescriptor.fromString(registerAnnotation.registryName()),
                        registerAnnotation.propertyName()
                );

                registryMap.put(registry, ComponentDescriptor.fromString(componentAnnotation.name()));
            }
        }

        PropertyFileRenderer renderer = new PropertyFileRenderer(getGenerationPath());

        for (ComponentPropertyDescriptor propertyDescriptor : registryMap.keySet()) {
            PropertyFileData propertyFileData = new PropertyFileData();
            propertyFileData.componentDescriptor = propertyDescriptor.getComponent();

            try {
                renderer.renderFile(propertyFileData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
