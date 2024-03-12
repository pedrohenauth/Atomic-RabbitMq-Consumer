package com.orch.stock.config;

import com.orch.stock.domain.Cadastro;
import com.orch.stock.infrastructure.rabbitConsumer.database.CadastroRepository;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.enums.EnumByName;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.sql.DataSource;
import java.util.List;

@EnableWebMvc
@Configuration
public class CadastroNewConfiguration implements WebMvcConfigurer {

    @Bean
    public Jdbi jdbi(DataSource dataSource, List<JdbiPlugin> plugins, List<RowMapper<?>> rowMappers){
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(dataSourceProxy);
        plugins.forEach(jdbi::installPlugin);
        rowMappers.forEach(jdbi::registerRowMapper);
        return jdbi;
    }
    @Bean
    public JdbiPlugin sqlObjectPlugin(){
        return new SqlObjectPlugin();
    }

    @Bean
    public RequestMappingHandlerAdapter handlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

    @Bean
    public CadastroRepository cadastroRepository(Jdbi jdbi){
        return jdbi.onDemand(CadastroRepository.class);
    }

}
