package org.example.Flow.when;

public class WhenResource {

    /**
     * Parses when-condition from console input.
     *
     * @param line
     * @return
     */
    public String parseCondition(String line){
        String[] parts = line.split("\\(");
        String[] parts2 = parts[1].split("\\)");

        return parts2[0];
    }

    /**
     * Parses when-body from console input.
     *
     * @param line
     * @return
     */
    public String parseBody(String line){
        String[] parts = line.split("\\{");

        return parts[1].replace("}", "").trim();
    }
}
