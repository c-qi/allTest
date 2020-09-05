package org.zhire.demo.canal;

import lombok.extern.slf4j.Slf4j;
import top.javatool.canal.client.context.CanalContext;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.model.CanalModel;

import java.util.Map;

/**
 * @author chenqi
 */
//@Slf4j
//@Component
//@CanalTable("user_info")
//public class UserCanal implements EntryHandler<UserInfo> {
//
//    @Override
//    public void insert(UserInfo o) {
//        log.info("insert：{}", JSON.toJSONString(o));
//
//    }
//    @Override
//    public void update(UserInfo before, UserInfo after) {
//        log.info("updateBefore:{}", JSON.toJSONString(before));
//        log.info("updateAfter:{}", JSON.toJSONString(after));
//
//    }
//    @Override
//    public void delete(UserInfo o) {
//        log.info("delete:{}", JSON.toJSONString(o));
//
//    }
//}
//@CanalTable(value = "all")
//@Component
@Slf4j
class DefaultEntryHandler implements EntryHandler<Map<String, String>> {
    @Override
    public void insert(Map<String, String> map) {
        CanalModel canalModel = CanalContext.getModel();
        log.info("canalModel:{}", canalModel);
        log.info("insert message  {}", map);
    }

    @Override
    public void update(Map<String, String> before, Map<String, String> after) {
        CanalModel canalModel = CanalContext.getModel();
        log.info("canalModel:{}", canalModel);
        log.info("update before {} ", before);
        log.info("update after {}", after);
    }

    @Override
    public void delete(Map<String, String> map) {
        CanalModel canalModel = CanalContext.getModel();
        log.info("canalModel:{}", canalModel);
        log.info("delete  {}", map);
    }
}
