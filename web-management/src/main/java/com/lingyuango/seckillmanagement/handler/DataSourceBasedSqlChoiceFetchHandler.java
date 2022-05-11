package com.lingyuango.seckillmanagement.handler;

import com.lingyuango.seckillmanagement.config.DataSourceConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.erupt.annotation.fun.ChoiceFetchHandler;
import xyz.erupt.annotation.fun.VLModel;
import xyz.erupt.core.util.EruptAssert;
import xyz.erupt.upms.cache.CaffeineEruptCache;
import xyz.erupt.upms.constant.FetchConst;
import xyz.erupt.upms.handler.SqlChoiceFetchHandler;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基于数据源的SqlChoiceFetchHandler
 * @author Jmc
 */
public class DataSourceBasedSqlChoiceFetchHandler implements ChoiceFetchHandler {
    private final CaffeineEruptCache<List<VLModel>> sqlCache = new CaffeineEruptCache<>();

    /**
     * 获取sql语句执行结果
     * @param params 第一个参数是erupt数据源名称，第二个参数是sql语句，第三个参数是超时时间
     */
    @Override
    public List<VLModel> fetch(String[] params) {
        // 检查params是否非空
        EruptAssert.notNull(params, DataSourceBasedSqlChoiceFetchHandler.class.getSimpleName() + " → params not found");

        // 通过Erupt数据源名称获取jdbcTemplate
        var jdbcTemplate = DataSourceConfig.getJdbcTemplate(params[0]);

        // 设置sql超时时间
        sqlCache.init(params.length == 3 ? Long.parseLong(params[2]) : FetchConst.DEFAULT_CACHE_TIME);

        // 执行sql并返回结果
        return sqlCache.get(
                DataSourceBasedSqlChoiceFetchHandler.class.getName() + ":" + params[1],
                (key) -> jdbcTemplate.query(params[1], (rs, i) -> {
            if (rs.getMetaData().getColumnCount() == 1) {
                return new VLModel(rs.getString(1), rs.getString(1));
            } else {
                return new VLModel(rs.getString(1), rs.getString(2));
            }
        }));
    }
}
