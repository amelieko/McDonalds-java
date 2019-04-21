package p041io.fabric.sdk.android.services.events;

import java.io.File;
import java.io.IOException;
import java.util.List;

/* renamed from: io.fabric.sdk.android.services.events.EventsStorage */
public interface EventsStorage {
    void add(byte[] bArr) throws IOException;

    boolean canWorkingFileStore(int i, int i2);

    void deleteFilesInRollOverDirectory(List<File> list);

    void deleteWorkingFile();

    List<File> getAllFilesInRollOverDirectory();

    List<File> getBatchOfFilesToSend(int i);

    int getWorkingFileUsedSizeInBytes();

    boolean isWorkingFileEmpty();

    void rollOver(String str) throws IOException;
}
