package com.crashlytics.android.core;

import com.crashlytics.android.core.Report.Type;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import p041io.fabric.sdk.android.Fabric;

class InvalidSessionReport implements Report {
    private final Map<String, String> customHeaders = new HashMap(ReportUploader.HEADER_INVALID_CLS_FILE);
    private final File[] files;
    private final String identifier;

    public InvalidSessionReport(String identifier, File[] files) {
        this.files = files;
        this.identifier = identifier;
    }

    public String getFileName() {
        return this.files[0].getName();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public File getFile() {
        return this.files[0];
    }

    public File[] getFiles() {
        return this.files;
    }

    public Map<String, String> getCustomHeaders() {
        return Collections.unmodifiableMap(this.customHeaders);
    }

    public void remove() {
        for (File file : this.files) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Removing invalid report file at " + file.getPath());
            file.delete();
        }
    }

    public Type getType() {
        return Type.JAVA;
    }
}
