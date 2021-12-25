package com.imooc.concurrency.core.konwledge.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coy
 * @since 2021/12/22
 **/
public class TestOom {
    
    public static void main(String[] args) {
        List<Byte[]> list = new ArrayList<>();
        
        while (true) {
            list.add(new Byte[1024 * 1024]);
        }
        
    }
}
