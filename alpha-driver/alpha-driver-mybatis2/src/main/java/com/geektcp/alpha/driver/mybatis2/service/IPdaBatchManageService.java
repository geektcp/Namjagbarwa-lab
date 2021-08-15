package com.geektcp.alpha.driver.mybatis2.service;

import com.geektcp.alpha.driver.mybatis2.model.qo.PdaBatchManageQo;
import com.geektcp.alpha.driver.mybatis2.model.vo.PageResponseDTO;
import com.geektcp.alpha.driver.mybatis2.model.vo.PdaBatchManageVo;

/**
 * @author haiyang on 2020-03-30 13:09
 */
public interface IPdaBatchManageService {

    PageResponseDTO<PdaBatchManageVo> findPage(PdaBatchManageQo pdaBatchManageQo);

}
