/*******************************************************************************
 * @(#)AsyController.java 2018年8月16日
 *
 * Copyright 2018 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2018年8月16日 上午10:08:47
 */
@RestController
public class AsyController {
    
    @Autowired
    LongTimeAsyncCallService longTimeAsyncCallService;
    
    @Autowired
    private AsyncTask asyncTask;
    
    @GetMapping( "/asynctask")
    public DeferredResult<Object> asyncTask() {
        DeferredResult<Object> deferredResult = new DeferredResult<Object>();
        System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
        longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
            @Override
            public void callback(Object result) {
                System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
                deferredResult.setResult(result);
            }
        });
        return deferredResult;
    }
    
    /**
     * 测试 方法   
     * asyncTask2 
     * 
     * 1、 controller 方法内定义   DeferredResult<Object> 异步返回类
     * 2、 定义回调方法  LongTermTaskCallback
     * 3、AsyncCallService 创建 异步执行类，通过构造将 回调方法传入
     * 4、将 seq 和 AsyncCallService 存入 Container.callServiceMap
     * 5、在asyncTask类中  模拟   业务处理， 调用方法。Container.callServiceMap.get(key) 获取 执行的 AsyncCallService调用makeRemoteCallAndUnknownWhenFinish
     * @param seq
     * @return TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     */
    @GetMapping( "/asynctask2")
    public DeferredResult<Object> asyncTask2(@RequestParam("seq") String seq) {
        //模拟启动线程
        asyncTask.start();
        
        DeferredResult<Object> deferredResult = new DeferredResult<Object>();
        System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
        // 异步回调方法
        LongTermTaskCallback LongTermTaskCallback = new LongTermTaskCallback() {
            public void callback(Object result) {
                System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
                deferredResult.setResult(result);
            }
        };
        // 回调需调用的方法
        AsyncCallService asyncCallService = new AsyncCallService(LongTermTaskCallback);
        // 将 seq  和  方法 添加到 内存中
        Container.callServiceMap.put(seq, asyncCallService);
        return deferredResult;
    }
}
