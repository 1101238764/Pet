package com.nala.helper.netty.websocket.serivce.impl;

import com.nala.helper.constants.LockType;
import com.nala.helper.constants.Symbol;
import com.nala.helper.netty.websocket.serivce.NoteNotifyService;
import com.nala.helper.service.ILockService;
import com.nala.helper.service.INoteService;
import com.nala.helper.vo.NoteVO;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 备忘录业务处理
 *
 * @author 彭术成
 * @since 2022/1/10
 */
@Component
@Slf4j
public class NoteNotifyServiceImpl implements NoteNotifyService {

    @Resource
    private INoteService noteService;
    @Resource
    private ILockService lockService;

    private static final String LOCK = LockType.NOTE_NOTIFY_LOCK;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<NoteVO> notify(String phone) {
        List<NoteVO> voList = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(Symbol.ONE);
        try {
            // 锁资源
            boolean lock = lockService.lock(LOCK, tomorrow.toInstant(ZoneOffset.of("+8")).toEpochMilli());
            if (lock) {
                if (noteService.count(phone) > 0) {
                    voList = noteService.noteList(phone);
                }
            }
        } catch (Exception e) {
            log.error("备忘录提醒服务异常");
            lockService.remove(LOCK);
        }
        return voList;
    }
}
