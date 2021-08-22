package com.ilyozzz.novelsbio.entity;


import com.ilyozzz.novelsbio.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ContentTypes extends AbsEntity {

    private String contentType;

    @ManyToOne
    private Languages languages;

}
