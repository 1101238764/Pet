package com.nala.helper.netty.websocket.serivce;

import com.nala.helper.vo.NoteVO;
import java.util.List;

/**
 * 备忘录业务处理服务
 *
 * @author 彭术成
 * @since 2022/1/10
 */
public interface NoteNotifyService {

    /**
     * 用户备忘录提醒
     *
     * @param accId 账号
     * @return 处理结果
     */
    List<NoteVO> notify(String accId);
}
