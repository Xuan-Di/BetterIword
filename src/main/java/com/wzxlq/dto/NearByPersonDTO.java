package com.wzxlq.dto;

import com.wzxlq.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.wzxlq.dto
 * @ClassName: NearByPersonDTO
 * @Author: 王照轩
 * @CreateTime: 2021/4/15 13:22
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearByPersonDTO extends User {
    String distance;
}
