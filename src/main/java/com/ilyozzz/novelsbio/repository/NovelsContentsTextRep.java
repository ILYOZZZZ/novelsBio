package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.NovelsContentText;
import com.ilyozzz.novelsbio.entity.NovelsContents;
import com.ilyozzz.novelsbio.resModels.ResAllContentsPaged;
import com.ilyozzz.novelsbio.resModels.ResContentText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface NovelsContentsTextRep extends JpaRepository<NovelsContentText, UUID> {


    @Query(value = "select nc.id as contentId ,  content_type as contentType , nct.paragraph as paragraph , nct.about_text as about , nc.photo_id as photoId from novels join novels_novels_contents nnc on novels.id = nnc.novels_id join novels_contents nc on nnc.novels_contents_id = nc.id join novels_contents_novels_content_text_list ncnctl on nc.id = ncnctl.novels_contents_id join novels_content_text nct on ncnctl.novels_content_text_list_id = nct.id join content_types ct on nc.content_types_id = ct.id where nct.languages_id=:langId and novels.id=:novelId", nativeQuery = true)
    Page<ResAllContentsPaged> getContentsPaged(@Param(value = "novelId")UUID novelId , @Param(value = "langId") Integer langId  ,Pageable pageable);

    @Query(value = "select nct.id as id , nct.paragraph , nct.main_text , nct.about_text as about , nct.additional_text , nct.audio_or_pdf_file_id as audioOrPdf , nct.reader_name from novels_contents join novels_contents_novels_content_text_list ncnctl on novels_contents.id = ncnctl.novels_contents_id join novels_content_text nct on ncnctl.novels_content_text_list_id = nct.id where novels_contents.id=:contentId and nct.languages_id=:langId",nativeQuery = true)
    ResContentText getCurrentContentText(@Param(value = "contentId") UUID contentId, @Param(value = "langId")Integer langId);


}
