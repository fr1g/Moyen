package su.kami.moyen.Exchange.Implement;

import su.kami.moyen.Model.Transaction;

import java.util.List;

public interface ILogService {
    List<Transaction> GetPagedLogs(int page);
    List<Transaction> GetUserPagedLogs(int uid, int page);
    void NewLog(int uid, int sid, String add);

}
