package group12.UserSetting;

import group12.data_access.GetPlanSQLOperation;
import group12.data_access.Subscribe_Plan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class SubscriptionController {
    @PostMapping(path = "/plan1")
    @ResponseBody
    public Subscribe_Plan SendPlan(@RequestBody Subscribe_Plan plan) {
        GetPlanSQLOperation getPlanSQLOperation = new GetPlanSQLOperation(1);
        getPlanSQLOperation.executeMysqlQuery();

    }
}