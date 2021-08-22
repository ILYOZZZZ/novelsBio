package com.ilyozzz.novelsbio.service;

import com.ilyozzz.novelsbio.entity.Attachment;
import com.ilyozzz.novelsbio.entity.Languages;
import com.ilyozzz.novelsbio.entity.NovelHistory;
import com.ilyozzz.novelsbio.entity.NovelHistoryText;
import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqAddNovelHistory;
import com.ilyozzz.novelsbio.repository.LanguageRep;
import com.ilyozzz.novelsbio.repository.NovelHistoryRep;
import com.ilyozzz.novelsbio.repository.NovelHistoryTextRep;
import com.ilyozzz.novelsbio.repository.NovelsRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NovelsHistoryService {
    @Autowired
    NovelsRep novelsRep;

    @Autowired
    NovelHistoryRep novelHistoryRep;

    @Autowired
    LanguageRep languageRep;

    @Autowired
    NovelHistoryTextRep novelHistoryTextRep;


    @Autowired
    AttachmentService attachmentService;



    public ApiResponse saveNovelsHistory(ReqAddNovelHistory reqAddNovelHistory) throws IOException {
        UUID novelHistoryIdIfExists = novelsRep.getNovelHistoryIdIfExists(reqAddNovelHistory.getNovelsId());
        Optional<NovelHistory> byId = novelHistoryRep.findById(novelHistoryIdIfExists);


        if(byId.isPresent()){
            List<NovelHistoryText> novelHistoryTextList = byId.get().getNovelHistoryTextList();

            for (NovelHistoryText novelHistoryText : novelHistoryTextList) {
                if(novelHistoryText.getLanguages().getId()==reqAddNovelHistory.getLangId()){
                    return new ApiResponse("error, alreadt exists" ,false);
                }
            }

        }


        Attachment attachment = attachmentService.saveAttachment(reqAddNovelHistory.getAudio());

        Languages languages = languageRep.findById(reqAddNovelHistory.getLangId()).get();

        NovelHistoryText novelHistoryText = new NovelHistoryText(languages,reqAddNovelHistory.getParagraph(),reqAddNovelHistory.getMainText(),reqAddNovelHistory.getAdditionalText(),reqAddNovelHistory.getAboutText(),attachment,reqAddNovelHistory.getReaderName());

        NovelHistoryText savedHistoryText = novelHistoryTextRep.save(novelHistoryText);


        if(byId.isPresent()){
            List<NovelHistoryText> novelHistoryTextList = byId.get().getNovelHistoryTextList();
            novelHistoryTextList.add(savedHistoryText);
            novelHistoryRep.save(byId.get());
        }else{
            List<NovelHistoryText> novelHistoryTextList = new ArrayList<>();
            novelHistoryTextList.add(savedHistoryText);
            novelHistoryRep.save(new NovelHistory(novelHistoryTextList));
        }

        return new ApiResponse("success", true);
    }






}
