package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Admin;
import app.withyou.ahometoshare.model.Article;
import app.withyou.ahometoshare.model.HomeRequest;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface AdminService {

    public Admin selectAdminByUsername(String username);

    public boolean deleteHostByAdmin(Integer hostId);

    public boolean deleteRenterByAdmin(Integer renterId);

    public Map<String, Long> getReferralSourceAggregation();

    public Map<String, Long> selectLoginHostRecordGroupByMonth();

    public Map<String, Long> selectLoginRenterRecordGroupByMonth();

    public Map<String, Long> selectHostAggregatedData();

    public Map<String, Long> selectRenterAggregatedData();

    public List<Article> getAllArticleBrief();

    public Pair<Boolean, String > updateArticle(Article article);

    Pair<Boolean, String> updatePassword(String oldPassword, String confirmPassword);

    List<HomeRequest> getAllHomeRequests();

    Pair<Boolean, String> deleteHomeRequest(Integer id);
}
