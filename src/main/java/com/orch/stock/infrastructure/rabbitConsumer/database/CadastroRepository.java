package com.orch.stock.infrastructure.rabbitConsumer.database;

import com.orch.stock.domain.Cadastro;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

@Repository
@UseClasspathSqlLocator
public interface CadastroRepository {

    @SqlUpdate
    void enviarMensagem(@Bind Cadastro cadastro);
}
