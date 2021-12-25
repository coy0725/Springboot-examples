package com.imooc.concurrency.core.konwledge.background;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：     发布逸出
 */
public class ReleaseOverflow {

    private Map<String, String> states;

    public ReleaseOverflow() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getStates() {
        return states;
    }

    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        ReleaseOverflow releaseOverflow = new ReleaseOverflow();
        Map<String, String> states = releaseOverflow.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));

        System.out.println(releaseOverflow.getStatesImproved().get("1"));
        releaseOverflow.getStatesImproved().remove("1");
        System.out.println(releaseOverflow.getStatesImproved().get("1"));

    }
}
