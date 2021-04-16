package org.zhire.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Date 2021/4/12 16:57
 * @Author by chenqi
 */
@Slf4j
@Component
public class LanguageUtil {

    private final Map<String, String> areaMap = CollUtil.newHashMap(4096);
    private final Map<String, String> countryMap = CollUtil.newHashMap(256);

    @PostConstruct
    public void initMap() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new ClassPathResource("static/district.txt").getStream()));
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                String[] s = line.split(" ");
//                System.out.println(Arrays.toString(s));
                areaMap.put(s[0], s[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClassPathResource resource = new ClassPathResource("static/countries.json");
        cn.hutool.json.JSON json = JSONUtil.readJSONObject(resource.getFile(), Charset.defaultCharset());
        cn.hutool.json.JSONArray records = ((cn.hutool.json.JSONObject) json).getJSONArray("RECORDS");
        records.forEach(l -> {
            Map map1 = JSONObject.parseObject(JSON.toJSONString(l), Map.class);
            countryMap.put(map1.get("cname").toString(), map1.get("name").toString());
        });
        log.info("areaMapSize:{}", areaMap.size());
        log.info("countryMapSize:{}", countryMap.size());
    }

    /**
     * 根据中文地区获取英文地区
     *
     * @param chName
     * @return
     */
    public String chToEn(String chName) {
        if (StrUtil.isNotBlank(chName)) {
            chName = chName.replace("省", "")
                    .replace("市", "")
                    .replace("区", "")
                    .replace("县", "");
            if (areaMap.containsKey(chName)) {
                return areaMap.get(chName);
            } else if (countryMap.containsKey(chName)) {
                return areaMap.get(chName);
            } else {
                return "";
            }
        }
        return "";
    }

}
