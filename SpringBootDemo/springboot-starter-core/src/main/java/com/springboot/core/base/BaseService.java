package com.springboot.core.base;

import com.github.pagehelper.PageHelper;
import com.springboot.core.base.exception.UpdateFailedException;
import com.springboot.core.constants.Constants;

import com.springboot.core.util.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.springboot.core.base.Mapper;
import javax.annotation.PostConstruct;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author:DuLeYan
 * @Description
 * @Date Created in 15:12 2019/6/28
 */
public abstract class BaseService<T> implements Service<T> {

    @Autowired
    private Mapper<T> mapper;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        this.entityClass = Reflections.getClassGenericType(getClass());
    }

    //
    // insert
    // ----------------------------------------------------------------------------------------------------
    @Transactional(rollbackFor = Exception.class)
    public T insert(T record) {
        mapper.insert(record);
        return record;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<T> insert(List<T> recordList) {
        mapper.insertList(recordList);
        return recordList;
    }

    @Transactional(rollbackFor = Exception.class)
    public T insertSelective(T record) {
        mapper.insertSelective(record);
        return record;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<T> insertSelective(List<T> recordList) {
        // 由于Mapper暂未提供Selective的批量插入，此处循环查询. 当然也可参考InsertListMapper自己实现.
        for(T record : recordList){
            mapper.insertSelective(record);
        }
        return recordList;
    }

    //
    // update
    // ----------------------------------------------------------------------------------------------------
    @Transactional(rollbackFor = Exception.class)
    public T update(T record) {
        int count = mapper.updateByPrimaryKey(record);
        checkUpdate(count, record);
        return record;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<T> update(List<T> recordList) {
        // Mapper暂未提供批量更新，此处循实现
        for(T record : recordList){
            int count = mapper.updateByPrimaryKey(record);
            checkUpdate(count, record);
        }
        return recordList;
    }

    @Transactional(rollbackFor = Exception.class)
    public T updateSelective(T record) {
        int count = mapper.updateByPrimaryKeySelective(record);
        checkUpdate(count, record);
        return record;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<T> updateSelective(List<T> recordList) {
        // Mapper暂未提供批量更新，此处循实现
        for(T record : recordList){
            int count = mapper.updateByPrimaryKeySelective(record);
            checkUpdate(count, record);
        }
        return recordList;
    }

    //
    // delete
    // ----------------------------------------------------------------------------------------------------
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long[] ids) {
        int count = 0;
        for(Long id : ids){
            mapper.deleteByPrimaryKey(id);
            count++;
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(T record) {
        return mapper.delete(record);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(List<T> recordList) {
        int count = 0;
        for(T record : recordList){
            mapper.delete(record);
            count++;
        }
        return count;
    }

    //
    // all operate. insert or update or delete
    // ----------------------------------------------------------------------------------------------------
    @Transactional(rollbackFor = Exception.class)
    public T persist(T record) {
        BaseDTO dto = (BaseDTO) record;
        Assert.notNull(dto.get_operate(), "_operate not be null.");
        switch (dto.get_operate()) {
            case Constants.Operation.ADD:
                insert(record);
                break;
            case Constants.Operation.UPDATE:
                update(record);
                break;
            case Constants.Operation.DELETE:
                delete(record);
                break;
            default:
                break;
        }
        dto.set_operate(null);
        return record;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<T> persist(List<T> recordList) {
        for(T record : recordList){
            BaseDTO dto = (BaseDTO) record;
            Assert.notNull(dto.get_operate(), "_operate not be null.");
            switch (dto.get_operate()) {
                case Constants.Operation.ADD:
                    insert(record);
                    break;
                case Constants.Operation.UPDATE:
                    update(record);
                    break;
                case Constants.Operation.DELETE:
                    delete(record);
                    break;
                default:
                    break;
            }
            dto.set_operate(null);
        }
        return recordList;
    }

    @Transactional(rollbackFor = Exception.class)
    public T persistSelective(T record) {
        BaseDTO dto = (BaseDTO) record;
        Assert.notNull(dto.get_operate(), "_operate not be null.");
        switch (dto.get_operate()) {
            case Constants.Operation.ADD:
                insertSelective(record);
                break;
            case Constants.Operation.UPDATE:
                updateSelective(record);
                break;
            case Constants.Operation.DELETE:
                delete(record);
                break;
            default:
                break;
        }
        return record;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<T> persistSelective(List<T> recordList) {
        for(T record : recordList){
            BaseDTO dto = (BaseDTO) record;
            Assert.notNull(dto.get_operate(), "_operate not be null.");
            switch (dto.get_operate()) {
                case Constants.Operation.ADD:
                    insertSelective(record);
                    break;
                case Constants.Operation.UPDATE:
                    updateSelective(record);
                    break;
                case Constants.Operation.DELETE:
                    delete(record);
                    break;
                default:
                    break;
            }
        }
        return recordList;
    }

    //
    // select
    // ----------------------------------------------------------------------------------------------------
    public T get(Long id) {
        T entity = null;
        try {
            entity = entityClass.newInstance();
            Field idField = Reflections.getFieldByAnnotation(entityClass, Id.class);
            idField.set(entity, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) mapper.selectByPrimaryKey(entity);
    }

    public T get(T record) {
        return (T) mapper.selectOne(record);
    }

    public T get(String key, Object value) {
        T entity = null;
        try {
            entity = entityClass.newInstance();
            Field field = Reflections.getField(entityClass, key);
            field.set(entity, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) mapper.selectOne(entity);
    }

    public List<T> select(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<T> select(T record) {

        return mapper.select(record);
    }

    public List<T> select(String key, Object value) {
        T entity = null;
        try {
            entity = entityClass.newInstance();
            Field field = Reflections.getField(entityClass, key);
            field.set(entity, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapper.select(entity);
    }

    public List<T> select(T record, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(record);
    }

    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public int count(T record) {
        return mapper.selectCount(record);
    }

    /**
     * 检查乐观锁<br>
     * 更新失败时，抛出 UpdateFailedException 异常
     *
     * @param updateCount update,delete 操作返回的值
     * @param record 操作参数
     */
    protected void checkUpdate(int updateCount, Object record) {
        if (updateCount == 0 && record instanceof BaseDTO) {
            BaseDTO baseDTO = (BaseDTO) record;
            if (baseDTO.getVersionNumber() != null) {
                try {
                    throw new UpdateFailedException();
                } catch (UpdateFailedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
