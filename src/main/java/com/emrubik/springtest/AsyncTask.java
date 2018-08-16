/*******************************************************************************
 * @(#)Ctx.java 2018年8月16日
 *
 * Copyright 2018 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springtest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2018年8月16日 上午11:27:34
 */
@Component
public class AsyncTask {
    
    /**
     * 
     * 模拟sdk 
     * 返回应答后 处理
     * 从Container.callServiceMap 根据 seq 获取 回调方法，执行业务
     * 调用回调
     */
    
    @Async("processExecutor")
    public void start() {
        try {
            System.out.println("开始");
            Thread.sleep(10000);
            System.out.println("输出========间隔");
            System.out.println("开始执行");
            if(Container.callServiceMap.size()>0){
                for(Map.Entry<String,AsyncCallService> entry: Container.callServiceMap.entrySet()){
                    Thread.sleep(3 * 1000);
                    AsyncCallService callService = entry.getValue();
                    callService.makeRemoteCallAndUnknownWhenFinish();
                };
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
