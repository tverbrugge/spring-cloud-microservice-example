package data.domain.nodes;

import data.config.DailySummaryDao;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by troy on 5/26/17.
 */
public class DailySummaryWriter implements ItemWriter<DailySummary> {

    private final DailySummaryDao dailySummaryDao;

    public DailySummaryWriter(DailySummaryDao userDao) {
        this.dailySummaryDao = userDao;
    }

    @Override
    public void write(List<? extends DailySummary> list) throws Exception {
        dailySummaryDao.insert(list);
    }
}
