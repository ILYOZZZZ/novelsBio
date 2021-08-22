package com.ilyozzz.novelsbio.resModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResContentsWithHistory {
    private Page<ResAllContentsPaged> resAllContentsPagedPage;
    private ResHistoryOfNovel resHistoryOfNovel;


}
