package util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.html.HtmlEscapers;
import com.google.common.reflect.Reflection;
import com.google.common.xml.XmlEscapers;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author tanghaiyang on 2019/3/11.
 */
@Slf4j
public class GuavaUtils {


    public static void main(String[] args) {
        ArrayList<String> arrayList = Lists.newArrayList("ddd", "ffff");
        ;

        Lists.newArrayList();
        Lists.reverse(arrayList);

//        Collections2.filter();
        List<String> listA = new LinkedList<>();
        List<String> listB = new LinkedList<>();
        List<String> listC = Lists.newArrayList();
        Collections.copy(listA, listB);
        Collections.copy(listA, listC);


        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();

        Sets.union(setA, setB);

        Map<String, String> mapA = new HashMap<>();
        Map<String, String> mapB = new HashMap<>();
        Maps.difference(mapA, mapB);

        Queue<String> queue = new LinkedBlockingQueue<>();
        Queues.newLinkedBlockingDeque();
    }

    @Test
    public void StringUtils() {
//        StringUtils.join(", ", " ");
//        Maps.uniqueIndex();
        XmlEscapers.xmlAttributeEscaper();
        HtmlEscapers.htmlEscaper();
        System.out.println(ClassPath.getClassPath());
        System.out.println(Reflection.getPackageName(GuavaUtils.class));
        ImmutableRangeSet.builder();
    }

    @Test
    public void Joiner(){
        List<String> list = Lists.newArrayList("1", "2", null, "ssss");
        String str = Joiner.on(",").skipNulls().join(list);
        System.out.println(str);
        Iterable<String> list2 = Splitter.on(",").split(str);


    }

    @Test
    public void TreeMapTest(){
        TreeMap<String,Object> treeMap = Maps.newTreeMap();
        treeMap.put("aaa", 10);
        treeMap.put("bbb", 22);
        treeMap.put("ccc", 33);
        log.info(JSON.toJSONString(treeMap));
    }
}
