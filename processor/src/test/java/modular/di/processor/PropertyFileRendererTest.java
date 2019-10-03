package modular.di.processor;

import modular.di.processor.data.ComponentDescriptor;
import modular.di.processor.data.PropertyFileData;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.processing.Filer;

import static org.mockito.Mockito.*;

public class PropertyFileRendererTest {

    PropertyFileData data;
    PropertyFileRenderer renderer;

    @Before
    public void init() {

        Filer filer = mock(Filer.class);

        renderer = new PropertyFileRenderer(".");

        data = new PropertyFileData();
        data.className = "com.example.Service";
        data.componentDescriptor = ComponentDescriptor.fromString("/org/example/Test");
    }

    @Test
    public void renderContentsTest() {

        String text = renderer.renderContents(data);

        //assert text.equals("$class=com.example.Service\n");
    }

}
