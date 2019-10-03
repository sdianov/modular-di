package modular.di.tests;

import modular.di.processor.data.ComponentDescriptor;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComponentDescriptorTest {

    @Test
    public void testFromString(){
        ComponentDescriptor name = ComponentDescriptor.fromString("/org/example/TestComponent");

        assertEquals(name.toString(), "/org/example/TestComponent");
    }

    @Test
    public void testFromClassName(){
        ComponentDescriptor name = ComponentDescriptor.fromClassName("org.example.TestClass");

        assertEquals(name.toString(), "/org/example/TestClass");
    }
}
