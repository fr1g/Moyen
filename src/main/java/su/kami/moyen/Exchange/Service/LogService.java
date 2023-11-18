package su.kami.moyen.Exchange.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.kami.moyen.Exchange.Implement.ILogService;
import su.kami.moyen.Exchange.Mapper.ITransactionMapper;
import su.kami.moyen.Helper.PageHelper;
import su.kami.moyen.Model.Transaction;

import java.util.List;

@Service
@Transactional
public class LogService implements ILogService {

    @Autowired
    ITransactionMapper _tm;

    @Override
    public List<Transaction> GetPagedLogs(int page) {
        return _tm.GetPagedLogs(PageHelper.P(page));
    }

    @Override
    public List<Transaction> GetUserPagedLogs(int uid, int page) {
        return _tm.GetUserPagedLogs(uid, PageHelper.ParsePage(page));
    }

    @Override
    public void NewLog(int uid, int sid, String add) {
        _tm.NewLog(uid, sid, add);
    }


}
