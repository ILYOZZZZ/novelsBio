package com.ilyozzz.novelsbio.resModels;

import java.util.UUID;

public interface ResContentText {
    UUID getId();
    String getParagraph();
    String getMainText();
    String getAbout();
    String getAdditionalText();
    UUID getAudioOrPdf();
    String getReaderName();
}
