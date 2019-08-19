package com.qing.mapper;


import com.qing.po.Items;
import com.qing.po.ItemsCustom;
import com.qing.po.ItemsVO;
import com.qing.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemsServiceImpl implements ItemsService
{

    @Autowired//(IOC DI)
    private ItemsMapperCustom itemsMapperCustom;

    @Autowired
    private ItemsMapper itemsMapper;


    @Override
    public List<ItemsCustom> queryItemsByNameService(ItemsVO itemsVO) throws Exception
    {
        //通过ItemsCusotm查询数据库
        List<ItemsCustom> itemsCustomList = itemsMapperCustom.queryItemsByName(itemsVO);
        return itemsCustomList;
    }

    /**
     * 调用mapper
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Items queryItemsById(Integer id) throws Exception {
        //return itemsMapperCustom.queryItemsById(id);
        return  itemsMapper.selectByPrimaryKey(id);

    }

    @Override
    public void modifyItems(Integer id,Items items) throws Exception {
        itemsMapper.updateByPrimaryKeyWithBLOBs(items);
    }

    @Override
    public void addItems(ItemsCustom itemsCustom) throws Exception {
        //itemsMapperCustom.modifyItems(id, itemsCustom);
        itemsMapper.insert(itemsCustom);
    }

    //根据ID删除
    @Override
    public void moveItemsById(Integer id) throws Exception {
        itemsMapper.deleteByPrimaryKey(id);
    }

    //根据ID删除一组数据
    @Override
    public void moveItemsByIds(Integer[] ids) throws Exception {
           itemsMapperCustom.deleteItemsByIds(ids);
    }

}
