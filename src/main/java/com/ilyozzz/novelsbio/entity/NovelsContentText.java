package com.ilyozzz.novelsbio.entity;

import com.ilyozzz.novelsbio.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class NovelsContentText  extends AbsEntity {
    @ManyToOne
    private Languages languages;

    private String paragraph;
    private String mainText;
    private String additionalText;
    private String aboutText;

    @OneToOne
    private Attachment audioOrPdfFile;


    private String readerName   ;
}
