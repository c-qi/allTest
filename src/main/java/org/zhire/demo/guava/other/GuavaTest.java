package org.zhire.demo.guava.other;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;
import org.zhire.pojo.User;

import java.util.*;

/**
 * @Date 2020/12/28 11:16
 * @Author by chenqi
 */
public class GuavaTest {

    /**
     * 谷歌集合工具类的使用
     */
    @Test
    public void testGoo() {
        // 普通Collection的创建
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

        // 不变Collection的创建
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");

        // 创建一个map key是字符串 value 是Integer集合
        Multimap<String, Integer> map2 = ArrayListMultimap.create();
        map2.put("aa", 1);
        map2.put("aa", 2);
        map2.put("bb", 3);
        map2.put("bb", 4);
        System.out.println("创建一个map,key是字符串:" + JSON.toJSONString(map2.asMap()));
        System.out.println("创建一个map,key是字符串:" + map2.get("aa"));  // [1, 2]


        // 无序 + 可重复 set
        Multiset<String> set2 = HashMultiset.create();
        set2.add("aaaa");
        set2.add("aaaa");
        System.out.println(JSON.toJSONString(set2));

        // key可以重复 map
        Multimap<String, String> mapp = ArrayListMultimap.create();
        mapp.put("name", "cq");
        mapp.put("name", "cq2");
        System.out.println(JSON.toJSONString(mapp.asMap()));

        // 双向Map(Bidirectional Map) 键与值都不能重复
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("name", "cq");
        biMap.put("name", "cq2");
        biMap.put("age", "2");
        // biMap.put("age2", "2");
        System.out.println(JSON.toJSONString(biMap));

        // 双键的Map Map--> Table-->rowKey+columnKey+value  // 类似和sql中的联合主键
        Table<String, String, String> tables = HashBasedTable.create();
        tables.put("id", "name", "cq");
        tables.put("id", "name", "cq");
        tables.put("id2", "name2", "cq2");
        System.out.println(tables.values());

        // 集合转字符串
        List<String> lists = new ArrayList();
        lists.add("aa");
        lists.add("bb");
        lists.add("cc");
        String result = Joiner.on(",").join(lists);
        System.out.println(result);

        // map转字符串
        Map<String, Integer> maps = Maps.newHashMap();
        maps.put("xiaoming", 12);
        maps.put("xiaohong", 13);
        String result2 = Joiner.on(",").withKeyValueSeparator("=").join(maps);
        System.out.println(result2);

        // 字符串转集合
        String str = "1,2,3";
        List<String> listss = Splitter.on(",").splitToList(str);
        System.out.println(listss);

        // 字符串转map
        String strs = "xiaoming=11,xiaohong=23";
        Map<String, String> mapss = Splitter.on(",").withKeyValueSeparator("=").split(strs);
        System.out.println(JSON.toJSONString(mapss));

        // set的交集, 并集, 差集
        HashSet<Integer> setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet<Integer> setB = Sets.newHashSet(4, 5, 6, 7, 8);

        Sets.SetView<Integer> union = Sets.union(setA, setB);
        System.out.println("union:");
        for (Integer integer : union) {
            // union 并集:12345867
            System.out.print(integer + " ");
        }
        System.out.println();

        Sets.SetView<Integer> difference = Sets.difference(setA, setB);
        System.out.println("difference:");
        for (Integer integer : difference) {
            // difference 差集:123
            System.out.print(integer + " ");
        }
        System.out.println();

        Sets.SetView<Integer> intersection = Sets.intersection(setA, setB);
        System.out.println("intersection:");
        for (Integer integer : intersection) {
            // intersection 交集:45
            System.out.print(integer + " ");
        }
        System.out.println();

        // map的交集，并集，差集
        HashMap<String, Integer> mapA = Maps.newHashMap();
        mapA.put("a", 1);
        mapA.put("b", 2);
        mapA.put("c", 3);

        HashMap<String, Integer> mapB = Maps.newHashMap();
        mapB.put("b", 20);
        mapB.put("c", 3);
        mapB.put("d", 4);

        MapDifference differenceMap = Maps.difference(mapA, mapB);
        differenceMap.areEqual();

        Map entriesDiffering = differenceMap.entriesDiffering();
        Map entriesOnlyLeft = differenceMap.entriesOnlyOnLeft();
        Map entriesOnlyRight = differenceMap.entriesOnlyOnRight();
        Map entriesInCommon = differenceMap.entriesInCommon();

        // {b=(2, 20)}
        System.out.println(entriesDiffering);
        // {a=1}
        System.out.println(entriesOnlyLeft);
        // {d=4}
        System.out.println(entriesOnlyRight);
        // {c=3}
        System.out.println(entriesInCommon);

    }

    /**
     * range
     */
    @Test
    public void testRange() {
        System.out.println("open:" + Range.open(1, 10));
        System.out.println("closed:" + Range.closed(1, 10));
        System.out.println("closedOpen:" + Range.closedOpen(1, 10));
        System.out.println("openClosed:" + Range.openClosed(1, 10));
        System.out.println("greaterThan:" + Range.greaterThan(10));
        System.out.println("atLeast:" + Range.atLeast(10));
        System.out.println("lessThan:" + Range.lessThan(10));
        System.out.println("atMost:" + Range.atMost(10));
        System.out.println("all:" + Range.all());
        System.out.println("closed:" + Range.closed(10, 10));
        System.out.println("closedOpen:" + Range.closedOpen(10, 10));
        // 会抛出异常
        // System.out.println("open:" + Range.open(10, 10));
    }

    @Test
    public void testWordCount() {
        String strWorld = "wer|dfd|dd|dfd|dda|de|dr";
        String[] words = strWorld.split("\\|");
        Map<String, Integer> countMap = new HashMap();
        for (String word : words) {
            Integer count = countMap.get(word);
            if (count == null) {
                countMap.put(word, 1);
            } else {
                countMap.put(word, count + 1);
            }
        }
        System.out.println("countMap：");
        for (String key : countMap.keySet()) {
            System.out.println(key + " count：" + countMap.get(key));
        }
    }

    @Test
    public void testMultsetWordCount() {
        String strWorld = "wer|dfd|dd|dfd|dda|de|dr";
        String[] words = strWorld.split("\\|");
        List<String> wordList = new ArrayList<>(Arrays.asList(words));

        Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(wordList);
        System.out.println(JSON.toJSONString(wordsMultiset));

        for (String key : wordsMultiset.elementSet()) {
            System.out.println(key + " count：" + wordsMultiset.count(key));
        }
    }

    @Test
    public void preconditions() throws Exception {

        getPersonByPrecondition(8, "cq");

        try {
            getPersonByPrecondition(-9, "cq");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            getPersonByPrecondition(8, "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            getPersonByPrecondition(8, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getPersonByPrecondition(int age, String name) throws Exception {
        Preconditions.checkNotNull(name, "neme为null");
        Preconditions.checkArgument(name.length() > 0, "neme为空");
        Preconditions.checkArgument(age > 0, "age 必须大于0");
        System.out.println("a person age:" + age + ",neme:" + name);
    }

    @Test
    public void t() {
        Multimap<Integer, User> map2 = ArrayListMultimap.create();
        map2.put(1, new User("cq"));
        map2.put(1, new User("cqq"));
        map2.put(2, new User("cq2q"));
        map2.put(2, new User("cq22q"));
        System.out.println(JSON.toJSONString(map2.asMap()));

        System.out.println(JSON.toJSONString(getMap("chenqi", "18")));
        System.out.println(JSON.toJSONString(getMap("chenqi2", "13")));
    }

    private Map getMap(String s, String s2) {
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", s, "k2", s2);
        return iMap;
    }

    @Test
    public void tr() {
        ImmutableSet<String> imSet = ImmutableSet.of("peida", "jerry", "harry", "lisa");
        System.out.println("imSet：" + imSet);
        ImmutableList<String> imlist = ImmutableList.copyOf(imSet);
        System.out.println("imlist：" + imlist);
        ImmutableSortedSet<String> imSortSet = ImmutableSortedSet.copyOf(imSet);
        System.out.println("imSortSet：" + imSortSet);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "x");
        }
        System.out.println("list：" + list);
        ImmutableList<String> imInfolist = ImmutableList.copyOf(list.subList(2, 18));
        System.out.println("imInfolist：" + imInfolist);
        int imInfolistSize = imInfolist.size();
        System.out.println("imInfolistSize：" + imInfolistSize);
        ImmutableSet<String> imInfoSet = ImmutableSet.copyOf(imInfolist.subList(2, imInfolistSize - 3));
        System.out.println("imInfoSet：" + imInfoSet);
    }

    @Test
    public void rer() {
        ImmutableSet<Integer> set = ImmutableSet.of(2, 1, 0, 2, -1, 99);
        ImmutableSortedSet<Integer> copy = ImmutableSortedSet.copyOf(set);
        System.out.println(copy);
    }

    private final RateLimiter rateLimiter = RateLimiter.create(100);

    /**
     * guava限流
     */
    @Test
    public void curr() {
        getIn(1);
        getIn(2);
        getIn(3);
        getIn(4);
        getIn(5);
        getIn(6);
        getIn(7);
        getIn(8);
        getIn(9);
    }

    public Integer getIn(int a) {
        System.out.println(Thread.currentThread().getId() + " 等待时间:" + rateLimiter.acquire());
        int r = a * a;
        System.out.println(Thread.currentThread().getId() + "：" + r);
        try {
//            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    @Test
    public void join() {
        //Join可以连接list、数组中的元素
        String str = Joiner.on(",").join(Lists.newArrayList("a", "b", "c"));
        String str1 = Joiner.on("#").join(new String[]{"a", "b", "c"});
        //指定第一个、第二个字符串，再跟list中的元素进行连接
        String str2 = Joiner.on("@").join("first", "second", Lists.newArrayList("a", "b", "c"));
        System.out.println(str);
        System.out.println(str1);
        System.out.println(str2);

        //跳过list中的空值，这里的空值是指null，并不包括空字符串
        String str3 = Joiner.on(",").skipNulls().join(Lists.newArrayList("a", "b", " ", null, "c", null));
        System.out.println(str3);

        //useForNull(str)用指定的字符串来替换空值
        String str4 = Joiner.on(",").useForNull("#").join(Lists.newArrayList("a", "b", " ", null, "c", null));
        System.out.println(str4);


        Map<String, String> map = Maps.newHashMap();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        //withKeyValueSeparator用:来连接map的key和value，再将连接好的key和value用逗号分隔
        String str5 = Joiner.on(",").withKeyValueSeparator(":").join(map);
        System.out.println(str5);
    }

    @Test
    public void splitterTest() {

        List<String> list = Splitter.on(",").splitToList("a,b,c,d");
        System.out.println(list);

        Splitter.on(",").split("a,b,c,d").forEach(s -> {
            System.out.println(s);
        });

        //过滤掉空字符串，但不包括空格
        List<String> list1 = Splitter.on(",").omitEmptyStrings().splitToList("a,b,, ,c,d");
        System.out.println("list1:" + list1);  //输出：[a, b,  ,c, d]

        //去掉字符串中的空格，再进行过滤空元素
        List<String> list2 = Splitter.on(",").omitEmptyStrings().trimResults().splitToList("a,b,, ,c,d");
        System.out.println("list2:" + list2); //输出 [a, b, c, d]

        //limit表示最多把字符串分隔成多少份
        List<String> list3 = Splitter.on("#").omitEmptyStrings().trimResults().limit(2).splitToList("a#b#c#d");
        System.out.println("list3:" + list3);

        //将字串还原成map，是Joiner的逆向操作，注意：字符串的格式必须满足“a:1#b:2”这种格式，格式不对会导致还原map失败
        Map<String, String> map = Splitter.on("#").omitEmptyStrings().trimResults().withKeyValueSeparator(":").split("a:1#b:2");
        System.out.println("map:" + map);

    }

    @Test
    public void charMatcherTest() {
        String str = "aj\tld1\b23aAbCs  kF45JAb  c56sl";
        //移除str中的a
        String a = CharMatcher.is('a').removeFrom(str);
        System.out.println(a);
        //移除str中的a
        String a1 = CharMatcher.isNot('a').retainFrom(str);
        System.out.println(a1);
        //保留str中的a,b,c字符
        String abc = CharMatcher.anyOf("abc").retainFrom(str);
        System.out.println(abc);
        //保留str中的a,b,c字符
        String abc1 = CharMatcher.noneOf("abc").removeFrom(str);
        System.out.println(abc1);
        //匹配str中的a-j的字母，全部替换成数字6
        String s = CharMatcher.inRange('a', 'j').replaceFrom(str, "6");
        System.out.println(s);
        //去str中的空格
        String s1 = CharMatcher.breakingWhitespace().removeFrom(str);
        System.out.println(s1);
        //去掉str中的数字
        String s2 = CharMatcher.digit().removeFrom(str);
        System.out.println(s2);
        //去掉控制字符(\t,\n,\b...)
        String s3 = CharMatcher.javaIsoControl().removeFrom(str);
        System.out.println(s3);
        //获取str中的小写字母
        String s4 = CharMatcher.javaLowerCase().retainFrom(str);
        System.out.println(s4);
        //获取str中的大写字母
        String s5 = CharMatcher.javaUpperCase().retainFrom(str);
        System.out.println(s5);
        //组合条件：获取str中的大写字母和数字
        System.out.println(CharMatcher.javaUpperCase().or(CharMatcher.digit()).retainFrom(str));
    }

}
