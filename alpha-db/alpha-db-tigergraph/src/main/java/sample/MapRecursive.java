package sample;

import java.util.*;

/**
 * @author tanghaiyang on 2019/2/11.
 */
public class MapRecursive {
    public static void main(String[] args) {

//        String startpoint = "c4";
//        String endpoint = "s3";

        String startpoint = "c2";
        String endpoint = "s1";

        HashMap<String, List<String>> map = buildMap();
        System.out.println(map);

        List<String> tmp;
        List<String> result = new ArrayList<>();

        List<String> keys = new ArrayList<>();
        keys.add(endpoint);

        List<String> ret = new LinkedList<>();
        ret.add(endpoint);

        List<String> tmpkeys = new LinkedList<>();
        while (true){
            tmpkeys.clear();
            getKeys(map, keys, ret, tmpkeys);
            keys.clear();
            keys.addAll(tmpkeys);
//            ret.addAll(keys);
//            break;
            if(ret.contains(startpoint)) { break;}

        }

        System.out.println("ret: " + ret);

    }

    private static void getKeys(HashMap<String, List<String>> map, List<String> keys, List<String> ret, List<String> tmpkeys){
        for(String key: keys) {
            if (map.containsKey(key)) {
                ret.addAll(map.get(key));
                tmpkeys.addAll(map.get(key));
                System.out.println("key: " + map.get(key));
            }
        }

    }

    private static HashMap<String, List<String>> buildMap() {
        HashMap<String, List<String>> map = new HashMap<>();
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        ArrayList<String> arr3 = new ArrayList<>();
        ArrayList<String> arr4 = new ArrayList<>();
        ArrayList<String> arr5 = new ArrayList<>();
        ArrayList<String> arr6 = new ArrayList<>();
        ArrayList<String> arr7 = new ArrayList<>();
        ArrayList<String> arr8 = new ArrayList<>();
        ArrayList<String> arr9 = new ArrayList<>();
        ArrayList<String> arr10 = new ArrayList<>();
        ArrayList<String> arr11 = new ArrayList<>();
        ArrayList<String> arr12 = new ArrayList<>();
        ArrayList<String> arr13 = new ArrayList<>();
        ArrayList<String> arr14 = new ArrayList<>();

        map.put("m8", arr1);
        arr1.add("c3");
        arr1.add("s1");

        map.put("s2", arr2);
        arr2.add("m1");

        map.put("c4", arr3);
        arr3.add("m7");

        map.put("c3", arr4);
        arr4.add("m7");

        map.put("s3", arr5);
        arr5.add("c2");

        map.put("m5", arr6);
        arr6.add("c2");

        map.put("c1", arr7);
        arr7.add("m2");

        map.put("m1", arr8);
        arr8.add("s3");

        map.put("m4", arr9);
        arr9.add("m1");

        map.put("m3", arr10);
        arr10.add("s3");

        map.put("m2", arr11);
        arr11.add("c2");

        map.put("m7", arr12);
        arr12.add("m5");

        map.put("m6", arr13);
        arr13.add("m7");

        map.put("s1", arr14);
        arr14.add("m3");
        return map;

        // {s3=[c2], m1=[s3], m2=[c2], m3=[s3], m4=[m1], m5=[c2], m6=[m7], m7=[m5], m8=[c3, s1], c1=[m2], c3=[m7], c4=[m7], s1=[m3], s2=[m1]}
    }

}

