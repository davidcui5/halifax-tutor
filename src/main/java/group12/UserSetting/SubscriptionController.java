package group12.UserSetting;

import group12.data_access.GetPlanSQLOperation;
import group12.data_access.Subscribe_Plan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

public class SubscriptionController {
    @PostMapping(path = "/plan1")
    @ResponseBody
    public ArrayList<Subscribe_Plan> SendPlan(@RequestBody ArrayList<Subscribe_Plan> plans) {
        ArrayList<Subscribe_Plan> planArrayList = new ArrayList<Subscribe_Plan>();

        GetPlanSQLOperation getPlanSQLOperation1 = new GetPlanSQLOperation(1);
        planArrayList.add ((Subscribe_Plan) getPlanSQLOperation1.executeMysqlQuery());

        GetPlanSQLOperation getPlanSQLOperation2 = new GetPlanSQLOperation(2);
        planArrayList.add ((Subscribe_Plan) getPlanSQLOperation2.executeMysqlQuery());

        GetPlanSQLOperation getPlanSQLOperation3 = new GetPlanSQLOperation(3);
        planArrayList.add ((Subscribe_Plan) getPlanSQLOperation3.executeMysqlQuery());

        GetPlanSQLOperation getPlanSQLOperation4 = new GetPlanSQLOperation(4);
        planArrayList.add ((Subscribe_Plan) getPlanSQLOperation4.executeMysqlQuery());

        return planArrayList;
    }


}