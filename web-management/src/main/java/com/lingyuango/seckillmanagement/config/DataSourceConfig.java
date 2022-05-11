package com.lingyuango.seckillmanagement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.erupt.core.prop.EruptProp;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig implements InitializingBean {
    /**
     * yml中erupt的多数据源配置
     */
    private final EruptProp eruptProp;

    /**
     * jdbcTemplate池（erupt数据源名称 -> jdbcTemplate）
     */
    private static final HashMap<String, JdbcTemplate> jdbcTemplatePool = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        // erupt的多数据源配置获取所有数据源信息
        var dbs = eruptProp.getDbs();

        // 将数据源信息配置成jdbcTemplate并放在map中
        for (var db : dbs) {
            var eruptDatasourceProps = db.getDatasource();

            var eruptDataSourceName = eruptDatasourceProps.getName();
            var jdbcTemplate = new JdbcTemplate(new HikariDataSource(new HikariConfig() {{
                setJdbcUrl(eruptDatasourceProps.getUrl());
                setUsername(eruptDatasourceProps.getUsername());
                setPassword(eruptDatasourceProps.getPassword());
            }}));

            jdbcTemplatePool.put(eruptDataSourceName, jdbcTemplate);
        }
    }

    /**
     * 通过erupt数据源名称获取jdbcTemplate
     * @param eruptDataSourceName erupt数据源名称
     * @return 查询到的jdbcTemplate
     */
    public static JdbcTemplate getJdbcTemplate(String eruptDataSourceName) {
        return Objects.requireNonNull(jdbcTemplatePool.get(eruptDataSourceName));
    }
}
