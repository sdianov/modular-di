package modular.di.processor;

import modular.di.processor.data.PropertyFileData;
import modular.di.processor.data.PropertyRecordData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static modular.di.processor.AnnotationUtils.isNullOrBlank;


public class PropertyFileRenderer {

    private final String generationPath;

    public PropertyFileRenderer(String pGenPath) {
        generationPath = pGenPath;
    }

    String renderContents(PropertyFileData data) {
        final StringBuilder sb = new StringBuilder();

        for (String c : data.headerComments) {
            sb.append("# ").append(c).append("\n");
        }
        sb.append("$class=").append(data.className).append("\n");

        if (data.scope != null && !data.scope.isEmpty()) {
            sb.append("$scope=").append(data.scope).append("\n");
        }

        if (!isNullOrBlank(data.description)) {
            sb.append("$description=").append(data.description).append("\n");
        }

        sb.append("\n");
        if (data.rawLines != null && data.rawLines.size() > 0) {
            for (String line : data.rawLines) {
                sb.append(line).append("\n");
            }
            sb.append("\n");
        }

        for (PropertyRecordData record : data.properties) {
            sb.append(record.name);

            sb.append(record.operation);
            sb.append("=");

            if (record.values.size() > 1) {
                for (int i = 0; i < record.values.size(); i++) {
                    String value = record.values.get(i);
                    sb.append("\\\n");
                    sb.append("\t").append(value);

                    if (i < record.values.size() - 1)
                        sb.append(",");
                }
                sb.append("\n\n");
            } else {
                sb.append(record.values.get(0)).append("\n\n");
            }
        }

        return sb.toString();
    }

    public void renderFile(PropertyFileData data) throws IOException {
        String s = renderContents(data);

        String filePath = Paths.get(generationPath, data.componentDescriptor.fullPath()).toString();
        File dir = new File(filePath);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Unable to create directory:" + dir.getAbsolutePath());
        }

        Path propertyPath = Paths.get(filePath, data.componentDescriptor.getName() + ".properties");

        Files.writeString(propertyPath, s, Charset.defaultCharset());

    }

}
