package com.ilyozzz.novelsbio.resModels;

import java.util.UUID;

public interface ResHistoryOfNovel {
    UUID getId();
    String getParagraph();
    String getMainText();
    String getAbout();
    String getAdditionalText();
    UUID getAudio();
    String getReaderName();

}
