package data.config;

import data.domain.nodes.DailySummary;
import data.domain.nodes.User;
import data.repositories.DailySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by troy on 5/26/17.
 */
@Repository
public class DailySummaryDaoImpl extends JdbcDaoSupport implements  DailySummaryDao {
    @Autowired
    DailySummaryRepository dailySummaryRepository;

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insert(List<? extends DailySummary> users) {
        dailySummaryRepository.save(users);
    }

    @Override
    public List<DailySummary> loadAllDailySummaries() {
        return dailySummaryRepository.findAll();
    }
}
