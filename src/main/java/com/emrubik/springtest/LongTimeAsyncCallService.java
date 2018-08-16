/*******************************************************************************
 * @(#)LongTimeAsyncCallService.java 2018年8月16日
 *
 * Copyright 2018 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springtest;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2018年8月16日 上午10:11:00
 */
@Service
public class LongTimeAsyncCallService {
    private final int CorePoolSize = 4;

    private final int NeedSeconds = 3;

    private Random random = new Random();

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CorePoolSize);

    public void makeRemoteCallAndUnknownWhenFinish(LongTermTaskCallback callback) {
        System.out.println("完成此任务需要 : " + NeedSeconds + " 秒");

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                callback.callback("长时间异步调用完成.");
            }
        }, 30, TimeUnit.SECONDS);
    }
}
