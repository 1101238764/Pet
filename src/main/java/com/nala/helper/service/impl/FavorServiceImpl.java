package com.nala.helper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nala.helper.condition.FavorCondition;
import com.nala.helper.mapper.FavorMapper;
import com.nala.helper.pojo.Favor;
import com.nala.helper.service.IFavorService;
import com.nala.helper.vo.FavorVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-27
 */
@Slf4j
@Service
public class FavorServiceImpl extends ServiceImpl<FavorMapper, Favor> implements IFavorService {

    @Override
    public boolean collect(FavorCondition condition) {
        Favor favor = Favor.builder()
                .id(IdWorker.getId())
                .content(condition.getContent())
                .type(condition.getType())
                .accountId(condition.getAccountId())
                .remark(condition.getRemark())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        return save(favor);
    }

    @Override
    public List<FavorVO> list(FavorCondition condition) {
        QueryWrapper<Favor> wrapper = buildQueryWrapper(condition);
        List<Favor> list = list(wrapper);
        List<FavorVO> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Favor favor : list) {
                FavorVO vo = new FavorVO();
                BeanUtils.copyProperties(favor, vo);
                vo.setId(favor.getId().toString());
                voList.add(vo);
            }
        }
        log.info("收藏列表查询");
        return voList;
    }

    @Override
    public boolean edit(FavorCondition condition) {
        UpdateWrapper<Favor> wrapper = buildUpdateWrapper(condition);
        return update(wrapper);
    }

    @Override
    public Map<String, List<FavorVO>> listByType(FavorCondition condition) {
        QueryWrapper<Favor> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", condition.getAccountId()).orderByDesc("update_time");
        List<Favor> list = list(wrapper);
        Map<String, List<FavorVO>> typeMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Favor favor : list) {
                List<FavorVO> voList;
                String type = favor.getType();
                if (typeMap.containsKey(type)) {
                    voList = typeMap.get(type);
                } else {
                    voList = new ArrayList<>();
                }
                FavorVO vo = new FavorVO();
                BeanUtils.copyProperties(favor, vo);
                vo.setId(favor.getId().toString());
                voList.add(vo);
                typeMap.put(type, voList);
            }
        }
        return typeMap;
    }

    private QueryWrapper<Favor> buildQueryWrapper(FavorCondition condition) {
        QueryWrapper<Favor> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", condition.getAccountId());
        if (StringUtils.isNotBlank(condition.getContent())) {
            wrapper.like("content", condition.getContent());
        }
        if (StringUtils.isNotBlank(condition.getRemark())) {
            wrapper.like("remark", condition.getRemark());
        }
        if (condition.getType() != null) {
            wrapper.eq("type", condition.getType());
        }
        wrapper.orderByDesc("update_time");
        return wrapper;
    }

    private UpdateWrapper<Favor> buildUpdateWrapper(FavorCondition condition) {
        UpdateWrapper<Favor> wrapper = new UpdateWrapper<>();
        wrapper.eq("account_id", condition.getAccountId())
                .eq("id", condition.getId());
        if (StringUtils.isNotBlank(condition.getContent())) {
            wrapper.set("content", condition.getContent());
        }
        if (StringUtils.isNotBlank(condition.getRemark())) {
            wrapper.set("remark", condition.getRemark());
        }
        if (condition.getType() != null) {
            wrapper.set("type", condition.getType());
        }

        return wrapper;
    }

}
