package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.NovelHistory;
import com.ilyozzz.novelsbio.entity.NovelHistoryText;
import com.ilyozzz.novelsbio.resModels.ResHistoryOfNovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface NovelHistoryTextRep extends JpaRepository<NovelHistoryText, UUID> {

    @Query(value = "select nht.paragraph as paragraph , nht.main_text as mainText , nht.id as id , nht.about_text as about , nht.additional_text as additionalText , nht.audio_id as audio, nht.reader_name as readerName from novels join novel_history nh on novels.novel_history_id = nh.id join novel_history_novel_history_text_list nhnhtl on nh.id = nhnhtl.novel_history_id join novel_history_text nht on nhnhtl.novel_history_text_list_id = nht.id where novels.id=:novelId and nht.languages_id=:langId", nativeQuery = true)
    ResHistoryOfNovel getHistoryOfNovel(@Param(value = "novelId")UUID novelId , @Param(value = "langId")Integer langId);


}
