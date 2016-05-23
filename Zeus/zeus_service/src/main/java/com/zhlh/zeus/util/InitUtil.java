package com.zhlh.zeus.util;

import com.zhlh.zeus.business.channel.ChannelManager;
import com.zhlh.zeus.business.citycode.CityCodeManager;
import com.zhlh.zeus.business.idcard.IdCardManager;
import com.zhlh.zeus.business.remexerror.InsurerErrManager;
import com.zhlh.zeus.business.puw.PUWManager;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/10 09:59
 * @comment
 */
public class InitUtil {

    void initResource() throws Exception {

        ChannelManager.initialize(ChannelManager.class.getResource("/business/channels.json").getPath());

        CityCodeManager.initialize(CityCodeManager.class.getResource("/business/city-code.csv").getPath());

        IdCardManager.initialize(IdCardManager.class.getResource("/business/id-area-code.csv").getPath());

        PUWManager.initialize(PUWManager.class.getResource("/business/puw-rules.json").getPath());

        InsurerErrManager.initialize(InsurerErrManager.class.getResource("/business/remex-error.json").getPath());
    }
}
