package data.config;

import data.domain.nodes.DailySummary;
import data.domain.nodes.User;

import java.util.List;

/**
 * Created by troy on 5/26/17.
 */
public interface DailySummaryDao {
    public void insert(List<? extends DailySummary> users);
    List<DailySummary> loadAllDailySummaries();
}
