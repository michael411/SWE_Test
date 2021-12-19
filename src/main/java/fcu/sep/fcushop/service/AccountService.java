package fcu.sep.fcushop.service;
import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;

import java.util.List;

@Service
public class AccountService {
	@Autowired
	private Sql2oDbHandler sql2oDbHandler;
	public List<Account> getAccounts() {
		try (Connection connection = sql2oDbHandler.getConnector().open()) {
			String query = "select EMAIL email, PASSWORD password"
			+ " from ACCOUNT_BASIC";
			return connection.createQuery(query).executeAndFetch(Account.class);
		}
	}
	public String addAccount(String email,String password) {
		String returnMessage;
		try (Connection connection = sql2oDbHandler.getConnector().open()) {
			String query = String.format("INSERT INTO `fcu_shop`.`account_basic` (`email`, `password`) VALUES ('%s','%s');", email, password);
			System.out.println(query);
			connection.createQuery(query, true).executeUpdate().getKey();

			returnMessage = query + "寫入成功";

		} catch (Exception ex)// 除了SQLException以外之錯誤
		{
			returnMessage = "錯誤訊息:" + ex.getMessage();
		}
		return returnMessage;
	}
	public Object checkLogin(Account account) {
		//檢查登入 陽春版

		try (Connection connection = sql2oDbHandler.getConnector().open()) {
			String query =  String.format("SELECT * FROM fcu_shop.account_basic WHERE email='%s' and password='%s';", account.getEmail(), account.getPassword());;
			System.out.println(query);
			return connection.createQuery(query).executeScalar();
		}
	}

}
